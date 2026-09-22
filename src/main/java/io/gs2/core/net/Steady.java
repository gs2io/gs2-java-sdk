package io.gs2.core.net;

import org.apache.http.conn.ConnectTimeoutException;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

/**
 * Steady（専用フリート）の基点。
 *
 * <p>フリートは 1 つの名前（steadyEndpoint、例 https://bs-dev.ap-northeast-1.dev.gen2.gs2io.com）で受け、
 * REST は &lt;steady&gt;/&lt;service&gt;/...、WebSocket は wss://&lt;host&gt;/ を使う。名前はフリートのノードへ
 * 直接解決される（間に ALB は無い）ので、フリートが手放した公開 IP に当たると SYN が落ちる。
 * そのため Steady のときだけ接続段階に上限（{@link #CONNECT_TIMEOUT_MILLIS}）を置き、接続段階の失敗
 * （1 バイトも送っていない）だけは同じ要求をもう 1 回だけ送る。送信後の失敗は届いたかもしれないので
 * 再送しない（非冪等要求の二重実行を作らない）。
 *
 * <p>steady が未設定なら URL もクライアントの挙動も従来と byte 単位で同じになる。
 */
public final class Steady {

    /**
     * Steady の基点への接続段階の上限（ミリ秒）。
     * フリートが手放した公開 IP は SYN を落とすので、OS 既定（数十秒〜数分）に任せない。
     *
     * <p>★Apache HttpClient の connectTimeout なので覆うのは TCP connect まで。TLS handshake の待ちは
     * socketTimeout 側で、そちらを縮めると GS2 の長い API の読み取りを殺すので触らない（近似できない）。
     */
    public static final int CONNECT_TIMEOUT_MILLIS = 5000;

    private static final String SERVICE_PLACEHOLDER = "{service}";
    private static final String REGION_PLACEHOLDER = "{region}";

    private Steady() {
    }

    /** 末尾の / と空白を落とす。空なら null（＝共有クラウド）。 */
    public static String normalize(String value) {
        if (value == null) {
            return null;
        }
        String v = value.trim();
        while (v.endsWith("/")) {
            v = v.substring(0, v.length() - 1);
        }
        return v.isEmpty() ? null : v;
    }

    /**
     * REST の接続先（https://&lt;host&gt;/&lt;service&gt; 相当。末尾 / 無し）。
     * steady 未設定なら sharedTemplate の {service} / {region} を置換した従来の文字列をそのまま返す。
     *
     * <p>優先順: サービスごと（呼び手）の override ＞ steady ＞ 共有クラウドの sharedTemplate。
     */
    public static String restEndpoint(String steady, String sharedTemplate, String service, String regionName) {
        String base = normalize(steady);
        if (base == null) {
            return resolveTemplate(sharedTemplate, regionName).replace(SERVICE_PLACEHOLDER, service);
        }
        return base + "/" + service;
    }

    /**
     * WebSocket の接続先。steady があれば wss://&lt;host&gt;/（http:// の基点 ―― ローカルの試験・開発 ―― は ws://）、
     * 無ければ従来の sharedTemplate（{region} を置換）。基点が URL として読めないときも従来の template。
     */
    public static String webSocketUrl(String steady, String sharedTemplate, String regionName) {
        String base = normalize(steady);
        if (base == null) {
            return resolveTemplate(sharedTemplate, regionName);
        }
        try {
            URI uri = new URI(base);
            if (uri.getHost() == null) {
                return resolveTemplate(sharedTemplate, regionName);
            }
            String scheme = "http".equalsIgnoreCase(uri.getScheme()) ? "ws" : "wss";
            String host = uri.getPort() >= 0 ? uri.getHost() + ":" + uri.getPort() : uri.getHost();
            return scheme + "://" + host + "/";
        } catch (URISyntaxException e) {
            return resolveTemplate(sharedTemplate, regionName);
        }
    }

    /**
     * ★生成クライアントが共有クラウドの template から組んだ URL を &lt;steady&gt;/&lt;service&gt;/... に差し替える。
     *
     * <p>生成物（各サービスのクライアント）は {@code Gs2RestSession.EndpointHost} を直に使って URL を組むので、
     * 生成物を一切触らずに Steady へ向けるには、組み上がった URL を template の形に照らしてサービス名を
     * 読み取るしかない。template の形に合わない URL（呼び手が独自に指定した宛先）はそのまま返す
     * ―― override は Steady より強い。steady 未設定なら URL は当然そのまま（従来と byte 単位で同じ）。
     */
    static String rewriteUrl(String steady, String sharedTemplate, String regionName, String url) {
        String base = normalize(steady);
        if (base == null || url == null || sharedTemplate == null) {
            return url;
        }
        if (isSteadyUrl(base, url)) {
            // 既に Steady 宛（プロジェクトトークンのログインなど）
            return url;
        }
        String resolved = resolveTemplate(sharedTemplate, regionName);
        int serviceAt = resolved.indexOf(SERVICE_PLACEHOLDER);
        if (serviceAt < 0) {
            // {service} の無い template からはサービス名が読めない
            return url;
        }
        String prefix = resolved.substring(0, serviceAt);
        String suffix = resolved.substring(serviceAt + SERVICE_PLACEHOLDER.length());
        if (!url.startsWith(prefix)) {
            return url;
        }
        String rest = url.substring(prefix.length());
        int serviceEnd;
        if (suffix.isEmpty()) {
            serviceEnd = rest.indexOf('/');
            if (serviceEnd < 0) {
                serviceEnd = rest.length();
            }
        } else {
            serviceEnd = rest.indexOf(suffix);
        }
        if (serviceEnd <= 0) {
            return url;
        }
        String service = rest.substring(0, serviceEnd);
        if (service.indexOf('/') >= 0) {
            return url;
        }
        return base + "/" + service + rest.substring(serviceEnd + suffix.length());
    }

    /** 要求 URL が Steady の基点宛か。接続段階の上限と再送はここが真のときだけ効く。 */
    static boolean isSteadyUrl(String steady, String url) {
        String base = normalize(steady);
        if (base == null || url == null) {
            return false;
        }
        return url.equals(base) || url.startsWith(base + "/");
    }

    /**
     * 「1 バイトも送っていない」接続段階の失敗か（DNS / TCP connect の拒否・タイムアウト / TLS handshake）。
     * これだけが再送の対象。要求を送り始めた後の失敗（読み取りタイムアウト・途中切断・HTTP の誤り）は
     * 届いたかもしれないので再送しない。
     *
     * @param e           送信で起きた例外
     * @param requestSent HTTP クライアントが要求の送信を始めていたか（Apache HttpClient の http.request_sent）
     */
    static boolean isConnectFailure(IOException e, boolean requestSent) {
        if (e == null || requestSent) {
            return false;
        }
        return e instanceof UnknownHostException
                || e instanceof SocketException            // ConnectException / HttpHostConnectException / NoRouteToHostException
                || e instanceof ConnectTimeoutException    // TCP connect のタイムアウト（Apache が包み直す）
                || e instanceof SocketTimeoutException     // 送信前なので TLS handshake の待ちで切れた
                || e instanceof SSLException;              // SSLHandshakeException / SSLPeerUnverifiedException
    }

    private static String resolveTemplate(String template, String regionName) {
        if (template == null) {
            return null;
        }
        return template.replace(REGION_PLACEHOLDER, regionName == null ? "" : regionName);
    }
}
