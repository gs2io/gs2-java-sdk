package io.gs2.core.net;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpCoreContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class HttpTask {

    // ★テストから差し替えられるように package-private（本番では差し替えない）
    static HttpClient client = HttpClientBuilder.create().build();

    protected HttpRequestBase httpRequest;
    private final Method method;
    private final String url;
    private IResponseHandler handler;
    private boolean enableCompressRequest = true;
    private boolean enableDecompressResponse = true;
    // 送信するエンティティ（圧縮後）。Steady の再送で要求を作り直すために保持する
    private byte[] entityBytes;
    // Steady（専用フリート）の基点。null なら共有クラウド（接続段階の上限も再送も掛からない）
    private String steadyEndpoint;

    public enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    public HttpTask(Method method, String url, IResponseHandler handler) {
        this.method = method;
        this.url = url;
        this.httpRequest = createRequest(method, url);
        this.handler = handler;
    }

    private static HttpRequestBase createRequest(Method method, String url) {
        switch (method) {
            case GET: {
                return new HttpGet(url);
            }
            case POST: {
                return new HttpPost(url);
            }
            case PUT: {
                return new HttpPut(url);
            }
            case DELETE: {
                return new HttpDelete(url);
            }
        }
        return null;
    }

    // 最大1回までしか呼べません
    public void send() {
        new Thread(
                () -> {
                    HttpClientContext context = HttpClientContext.create();
                    try {
                        HttpResponse response = client.execute(httpRequest, context);
                        callback(httpRequest, response, true);
                        return;
                    } catch (IOException e) {
                        // ★Steady の再送: 基点への**接続段階**の失敗（DNS / TCP connect / TLS handshake。
                        // 1 バイトも送っていない）だけ、同じ要求をもう 1 回だけ送る。フリートが手放した公開 IP に
                        // 当たったとき、名前を引き直して別のノードへ着く機会を 1 回だけ作る。送信後の失敗は
                        // 届いたかもしれないので再送しない（非冪等要求の二重実行を作らない）。再送は 1 回だけ。
                        boolean requestSent = context.getAttribute(HttpCoreContext.HTTP_REQ_SENT) != null;
                        if (Steady.isSteadyUrl(steadyEndpoint, url) && Steady.isConnectFailure(e, requestSent)) {
                            HttpRequestBase retry = createRequest(method, url);
                            for (Header header : httpRequest.getAllHeaders()) {
                                retry.addHeader(header);
                            }
                            retry.setConfig(httpRequest.getConfig());
                            applyEntity(retry, entityBytes);
                            try {
                                HttpResponse response = client.execute(retry, HttpClientContext.create());
                                callback(retry, response, true);
                                return;
                            } catch (IOException retryError) {
                                // 2 回目も失敗: 従来どおり失敗として返す（3 回目は無い）
                            }
                        }
                    }
                    try {
                        callback(httpRequest, null, false);
                    } catch (IOException ex) {
                    }
                }
        ).start();
    }

    // ユーザデータは設定しても send 時に上書きされます
    public HttpRequestBase getHttpRequest() {
        return httpRequest;
    }

    void callback(HttpRequestBase pHttpRequest, HttpResponse pHttpResponse, boolean isSuccessful) throws IOException {
        if (pHttpResponse != null) {
            byte[] responseBody;
            try (InputStream in = pHttpResponse.getEntity().getContent()) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[4096];
                int nRead;
                while ((nRead = in.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                responseBody = buffer.toByteArray();
            }

            // gzip展開が有効で、Content-Encodingがgzipの場合は展開する
            if (enableDecompressResponse) {
                Header contentEncodingHeader = pHttpResponse.getFirstHeader("Content-Encoding");
                if (contentEncodingHeader != null && "gzip".equalsIgnoreCase(contentEncodingHeader.getValue())) {
                    responseBody = decompress(responseBody);
                }
            }

            Gs2RestResponse gs2RestResponse = new Gs2RestResponse(new String(responseBody), pHttpResponse.getStatusLine().getStatusCode());
            this.handler.callback(gs2RestResponse);
        } else {
            Gs2RestResponse gs2RestResponse = new Gs2RestResponse("", 400);
            this.handler.callback(gs2RestResponse);
        }
    }

    // ユーティリティ
    public void addHeaderEntry(String key, String value) {
        httpRequest.addHeader(key, value);
    }

    public void setBody(byte[] body) {
        try {
            byte[] bodyToSend = body;
            if (enableCompressRequest && body != null && body.length > 0) {
                bodyToSend = compress(body);
                httpRequest.addHeader("Content-Encoding", "gzip");
            }
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(bodyToSend);
            // ★本文は byte[] で持ち、要求ごとにストリームを作り直す（Steady の再送で同じ要求をもう一度組むため）
            this.entityBytes = bout.toByteArray();
            applyEntity(this.httpRequest, this.entityBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void applyEntity(HttpRequestBase request, byte[] bytes) {
        if (bytes == null) {
            return;
        }
        BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(bytes));
        if (request instanceof HttpPost) {
            ((HttpPost) request).setEntity(entity);
        }
        if (request instanceof HttpPut) {
            ((HttpPut) request).setEntity(entity);
        }
    }

    public void setEnableCompressRequest(boolean enableCompressRequest) {
        this.enableCompressRequest = enableCompressRequest;
    }

    public void setEnableDecompressResponse(boolean enableDecompressResponse) {
        this.enableDecompressResponse = enableDecompressResponse;
    }

    /**
     * Steady（専用フリート）の基点を関連づける。宛先が基点配下なら接続段階に上限
     * （{@link Steady#CONNECT_TIMEOUT_MILLIS}）を置き、接続段階の失敗だけ同じ要求をもう 1 回だけ送る。
     * null（共有クラウド）なら従来どおり。
     */
    void setSteadyEndpoint(String steadyEndpoint) {
        this.steadyEndpoint = Steady.normalize(steadyEndpoint);
        if (Steady.isSteadyUrl(this.steadyEndpoint, url)) {
            httpRequest.setConfig(
                    RequestConfig.custom()
                            .setConnectTimeout(Steady.CONNECT_TIMEOUT_MILLIS)
                            .build()
            );
        }
    }

    private static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(data);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] decompress(byte[] data) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
            byte[] buffer = new byte[4096];
            int len;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
