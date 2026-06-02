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

    public HttpTask build() {
        HttpTask httpTask = new HttpTask(method, url, handler);
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
