package io.gs2.core.net;

import java.util.HashMap;
import java.util.Map;

public class HttpTaskBuilder {

    private HttpTask.Method method;
    private String url;
    private Map<String, String> headers = new HashMap<>();
    private byte[] body;
    private IResponseHandler handler;
    private boolean enableCompressRequest = true;
    private boolean enableDecompressResponse = true;
    // Steady（専用フリート）の基点。null なら共有クラウド
    private String steadyEndpoint;
    // 生成クライアントが URL を組むのに使う共有クラウドの template（{service} / {region} 入り）と region。
    // 組み上がった URL からサービス名を読み取って <steady>/<service> に差し替えるために持つ
    private String sharedEndpointTemplate;
    private String regionName;

    private HttpTaskBuilder() {}

    public static  HttpTaskBuilder create() {
        return new HttpTaskBuilder();
    }

    public HttpTaskBuilder setMethod(HttpTask.Method method) {
        this.method = method;
        return this;
    }

    public HttpTaskBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpTaskBuilder setHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public HttpTaskBuilder setBody(byte[] body) {
        this.body = body;
        return this;
    }

    public HttpTaskBuilder setHttpResponseHandler(IResponseHandler handler) {
        this.handler = handler;
        return this;
    }

    public HttpTaskBuilder setEnableCompressRequest(boolean enableCompressRequest) {
        this.enableCompressRequest = enableCompressRequest;
        return this;
    }

    public HttpTaskBuilder setEnableDecompressResponse(boolean enableDecompressResponse) {
        this.enableDecompressResponse = enableDecompressResponse;
        return this;
    }

    /**
     * Steady（専用フリート）の基点を関連づける。{@link #build()} で宛先を &lt;steady&gt;/&lt;service&gt; に差し替え、
     * 接続段階に上限（{@link Steady#CONNECT_TIMEOUT_MILLIS}）と接続段階失敗の 1 回再送を付ける。
     * steadyEndpoint が null / 空なら従来どおり（URL も挙動も byte 単位で同じ）。
     *
     * @param steadyEndpoint         基点 https://&lt;host&gt;
     * @param sharedEndpointTemplate 生成クライアントが使う共有クラウドの template（{service} / {region} 入り）
     * @param regionName             リージョン名
     */
    public HttpTaskBuilder setSteadyEndpoint(String steadyEndpoint, String sharedEndpointTemplate, String regionName) {
        this.steadyEndpoint = steadyEndpoint;
        this.sharedEndpointTemplate = sharedEndpointTemplate;
        this.regionName = regionName;
        return this;
    }

    public HttpTask build() {
        // ★生成物（各サービスのクライアント）は template から URL を組むので、差し替えはここで行う
        String targetUrl = Steady.rewriteUrl(steadyEndpoint, sharedEndpointTemplate, regionName, url);
        HttpTask httpTask = new HttpTask(method, targetUrl, handler);
        httpTask.setSteadyEndpoint(steadyEndpoint);
        httpTask.setEnableCompressRequest(enableCompressRequest);
        httpTask.setEnableDecompressResponse(enableDecompressResponse);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpTask.addHeaderEntry(entry.getKey(), entry.getValue());
        }
        // レスポンスの圧縮を受け入れることをサーバーに伝える
        if (enableDecompressResponse) {
            httpTask.addHeaderEntry("Accept-Encoding", "gzip");
        }
        if (method == HttpTask.Method.POST || method == HttpTask.Method.PUT) {
            httpTask.setBody(body);
        }
        return httpTask;
    }

}
