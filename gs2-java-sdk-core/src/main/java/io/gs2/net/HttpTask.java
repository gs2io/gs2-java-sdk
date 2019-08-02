package io.gs2.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpTask {

    private static HttpClient client = HttpClientBuilder.create().build();

    private HttpRequestBase httpRequest;
    private IResponseHandler handler;

    public enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    public HttpTask(Method method, String url, IResponseHandler handler) {
        switch (method) {
            case GET: {
                this.httpRequest = new HttpGet(url);
                break;
            }
            case POST: {
                this.httpRequest = new HttpPost(url);
                break;
            }
            case PUT: {
                this.httpRequest = new HttpPut(url);
                break;
            }
            case DELETE: {
                this.httpRequest = new HttpDelete(url);
            }
        }
        this.handler = handler;
    }

    // 最大1回までしか呼べません
    public void send() {
        new Thread(
                () -> {
                    try {
                        HttpResponse response = client.execute(httpRequest);
                        callback(httpRequest, response, true);
                    } catch (IOException e) {
                        try {
                            callback(httpRequest, null, false);
                        } catch (IOException ignored) {
                        }
                    }
                }
        ).start();
    }

    // ユーザデータは設定しても send 時に上書きされます
    public HttpRequestBase getHttpRequest() {
        return httpRequest;
    }

    private void callback(HttpRequestBase pHttpRequest, HttpResponse pHttpResponse, boolean isSuccessful) throws IOException {
        if (pHttpResponse != null) {
            int responseLength = (int) pHttpResponse.getEntity().getContentLength();
            byte[] responseBody = new byte[responseLength];

            try (InputStream in = pHttpResponse.getEntity().getContent()) {
                int readSize = 0;
                while (readSize < responseLength) {
                    readSize += in.read(responseBody);
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
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(body);
            BasicHttpEntity entity = new BasicHttpEntity();
            entity.setContent(new ByteArrayInputStream(bout.toByteArray()));
            ((HttpEntityEnclosingRequestBase) this.httpRequest).setEntity(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
