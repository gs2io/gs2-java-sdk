package io.gs2.core.net;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class HttpTask {

    private static HttpClient client = HttpClientBuilder.create().build();

    protected HttpRequestBase httpRequest;
    private IResponseHandler handler;
    private boolean enableCompressRequest = true;
    private boolean enableDecompressResponse = true;

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
                        } catch (IOException ex) {
                        }
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
            BasicHttpEntity entity = new BasicHttpEntity();
            entity.setContent(new ByteArrayInputStream(bout.toByteArray()));
            if (this.httpRequest instanceof HttpPost) {
                ((HttpPost) this.httpRequest).setEntity(entity);
            }
            if (this.httpRequest instanceof HttpPut) {
                ((HttpPut) this.httpRequest).setEntity(entity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEnableCompressRequest(boolean enableCompressRequest) {
        this.enableCompressRequest = enableCompressRequest;
    }

    public void setEnableDecompressResponse(boolean enableDecompressResponse) {
        this.enableDecompressResponse = enableDecompressResponse;
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
