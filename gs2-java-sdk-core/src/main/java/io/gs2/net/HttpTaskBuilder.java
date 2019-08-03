package io.gs2.net;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpTaskBuilder {

    private HttpTask.Method method;
    private String url;
    private Map<String, String> headers = new HashMap<>();
    private byte[] body;
    private IResponseHandler handler;

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

    public HttpTask build() {
        HttpTask httpTask = new HttpTask(method, url, handler);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpTask.addHeaderEntry(entry.getKey(), entry.getValue());
        }
        httpTask.setBody(body);
        return httpTask;
    }

}
