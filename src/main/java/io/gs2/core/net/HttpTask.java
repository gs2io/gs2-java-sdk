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

    static HttpClient client = HttpClientBuilder.create().build();

    protected HttpRequestBase httpRequest;
    private final Method method;
    private final String url;
    private IResponseHandler handler;
    private boolean enableCompressRequest = true;
    private boolean enableDecompressResponse = true;
    private byte[] entityBytes;
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

    public void send() {
        new Thread(
                () -> {
                    HttpClientContext context = HttpClientContext.create();
                    try {
                        HttpResponse response = client.execute(httpRequest, context);
                        callback(httpRequest, response, true);
                        return;
                    } catch (IOException e) {
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
