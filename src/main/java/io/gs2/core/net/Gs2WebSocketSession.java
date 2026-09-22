package io.gs2.core.net;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.exception.Gs2Exception;
import io.gs2.core.exception.NoInternetConnectionException;
import io.gs2.core.exception.UnknownException;
import io.gs2.core.model.BasicGs2Credential;
import io.gs2.core.model.Region;
import org.json.JSONObject;

import java.io.IOException;

public class Gs2WebSocketSession extends Gs2Session {

    public static String EndpointHost = "https://{service}.{region}.gen2.gs2io.com";

    /** 共有クラウドの WebSocket の接続先（{@link #webSocketUrl()} の Steady 未設定時の template）。 */
    public static String WebSocketEndpointHost = "wss://gateway-ws.{region}.gen2.gs2io.com";

    private boolean m_IsOpenCancelled;

    // Steady（専用フリート）の基点。null なら共有クラウドで、URL も挙動も従来と byte 単位で同じ
    private String m_SteadyEndpoint;

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class LoginResult {
        /** プロジェクトトークン */
        public String access_token;
        /** Bearer */
        public String token_type;
        /** 有効期間(秒) */
        public Integer expires_in;
    }

    private class Gs2LoginTask
    {
        private Gs2WebSocketSession gs2RestSession;

        Gs2LoginTask(Gs2WebSocketSession gs2RestSession) {
            this.gs2RestSession = gs2RestSession;
        }

        void execute() throws IOException {
            JSONObject json = new JSONObject();
            json.put("client_id", gs2RestSession.getGs2Credential().getClientId());
            json.put("client_secret", gs2RestSession.getGs2Credential().getClientSecret());
            byte[] body = json.toString().getBytes();

            HttpTaskBuilder
                    .create()
                    .setMethod(HttpTask.Method.POST)
                    // ★プロジェクトトークンのログインも Steady の基点へ向ける
                    //（放置すると Steady のアプリの identifier だけ共有クラウドへ行く）
                    .setSteadyEndpoint(gs2RestSession.getSteadyEndpoint(), EndpointHost, gs2RestSession.getRegion().getName())
                    .setUrl(gs2RestSession.endpointHost("identifier") + "/projectToken/login")
                    .setHeader("Content-Type", "application/json")
                    .setHttpResponseHandler((response) -> {
                        Gs2Exception error = response.getGs2Exception();
                        String accessToken = null;

                        if (error == null) {
                            try {
                                ObjectMapper mapper = new ObjectMapper();
                                accessToken = mapper.readValue(response.getMessage(), LoginResult.class).access_token;
                            } catch (Exception e) {
                                error = new UnknownException("JSON parsing error: \n" + response.getMessage());
                            }
                        }

                        this.gs2RestSession.openCallback(accessToken, error);
                    })
                    .setBody(body)
                    .build()
                    .send();
        }
    }

    public Gs2WebSocketSession(BasicGs2Credential basicGs2Credential) {
        super(basicGs2Credential);
    }

    public Gs2WebSocketSession(BasicGs2Credential basicGs2Credential, Region region) {
        super(basicGs2Credential, region);
    }

    public Gs2WebSocketSession(BasicGs2Credential basicGs2Credential, String region) {
        super(basicGs2Credential, region);
    }

    /**
     * @param steadyEndpoint Steady（専用フリート）の基点 https://&lt;host&gt;。null / 空なら共有クラウド
     */
    public Gs2WebSocketSession(BasicGs2Credential basicGs2Credential, Region region, String steadyEndpoint) {
        super(basicGs2Credential, region);
        setSteadyEndpoint(steadyEndpoint);
    }

    /**
     * @param steadyEndpoint Steady（専用フリート）の基点 https://&lt;host&gt;。null / 空なら共有クラウド
     */
    public Gs2WebSocketSession(BasicGs2Credential basicGs2Credential, String region, String steadyEndpoint) {
        super(basicGs2Credential, region);
        setSteadyEndpoint(steadyEndpoint);
    }

    public void execute(Gs2RestSessionTask gs2RestSessionTask) throws IOException {
        super.execute(gs2RestSessionTask);
    }

    // ------------------------------------------------------------ Steady（専用フリート）

    /**
     * Steady（専用フリート）の基点 https://&lt;host&gt;（placeholder 無し。末尾の / と空白は落とす）。
     * null なら共有クラウド。セッションを開く前に設定する（{@link Steady} の説明）。
     */
    public String getSteadyEndpoint() {
        return m_SteadyEndpoint;
    }

    public void setSteadyEndpoint(String steadyEndpoint) {
        m_SteadyEndpoint = Steady.normalize(steadyEndpoint);
    }

    /**
     * API の接続先（https://&lt;host&gt;/&lt;service&gt; 相当。末尾 / 無し）。
     * 優先順: 呼び手の override ＞ Steady の基点 ＞ 共有クラウドの {@link #EndpointHost}。
     */
    public String endpointHost(String service) {
        return Steady.restEndpoint(m_SteadyEndpoint, EndpointHost, service, getRegion().getName());
    }

    /**
     * WebSocket の接続先。Steady があれば wss://&lt;host&gt;/（http:// の基点は ws://）、
     * 無ければ従来の {@link #WebSocketEndpointHost}（{region} を置換）。
     *
     * <p>★この Java セッションは常設のソケットを持たない（各タスクは HTTP 要求として送られる）ので、
     * この URL は自前でソケットを開く呼び手のためのもの。繋ぎ直しは入れていない。
     */
    public String webSocketUrl() {
        return Steady.webSocketUrl(m_SteadyEndpoint, WebSocketEndpointHost, getRegion().getName());
    }

    /**
     * WebSocket の handshake の上限（ミリ秒）。Steady のときだけ {@link Steady#CONNECT_TIMEOUT_MILLIS}、
     * 未設定なら 0（＝上限無し。従来どおり）。自前でソケットを開く呼び手がこの値を使う
     * （{@link java.net.Socket#connect(java.net.SocketAddress, int)} など）。
     */
    public int webSocketConnectTimeoutMillis() {
        return m_SteadyEndpoint == null ? 0 : Steady.CONNECT_TIMEOUT_MILLIS;
    }

    @Override
    void openImpl() {
        m_IsOpenCancelled = false;

        try {
            (new Gs2LoginTask(this)).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void cancelOpenImpl()
    {
        m_IsOpenCancelled = true;
    }

    @Override
    boolean closeImpl() {
        Gs2Exception gs2ClientException = new NoInternetConnectionException("");  // TODO
        closeCallback(gs2ClientException, true);

        return true;
    }
}
