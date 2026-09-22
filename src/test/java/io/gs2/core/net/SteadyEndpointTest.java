package io.gs2.core.net;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.gs2.account.Gs2AccountRestClient;
import io.gs2.account.request.DescribeNamespacesRequest;
import io.gs2.account.result.DescribeNamespacesResult;
import io.gs2.core.exception.BadRequestException;
import io.gs2.core.exception.Gs2Exception;
import io.gs2.core.exception.InternalServerErrorException;
import io.gs2.core.model.AsyncAction;
import io.gs2.core.model.AsyncResult;
import io.gs2.core.model.BasicGs2Credential;
import io.gs2.core.model.IResult;
import io.gs2.core.model.Region;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.junit.After;
import org.junit.Test;

import javax.net.ssl.SSLHandshakeException;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Steady（専用フリート）の基点（golang/core/steady_test.go の写し）。
 * com.sun.net.httpserver でフリートの基点を装い、URL の差し替え・接続段階の上限・接続段階失敗の 1 回再送を見る。
 */
public class SteadyEndpointTest {

    private static final BasicGs2Credential CREDENTIAL = new BasicGs2Credential("cid", "secret");

    private final List<FakeServer> servers = new ArrayList<>();
    private final List<Gs2Session> sessions = new ArrayList<>();
    private final String savedEndpointHost = Gs2RestSession.EndpointHost;
    private final HttpClient savedClient = HttpTask.client;

    @After
    public void tearDown() {
        HttpTask.client = savedClient;
        Gs2RestSession.EndpointHost = savedEndpointHost;
        for (Gs2Session session : sessions) {
            session.close();
        }
        for (FakeServer server : servers) {
            server.stop();
        }
    }

    // ---------------------------------------------------------------- 偽サーバー

    private static class FakeServer {
        final HttpServer server;
        final String url;
        final List<String> paths = new ArrayList<>();
        final List<String> bodies = new ArrayList<>();

        FakeServer(HttpHandler handler) throws IOException {
            server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
            server.createContext("/", exchange -> {
                String body = drain(exchange.getRequestBody());
                synchronized (paths) {
                    paths.add(exchange.getRequestURI().getPath());
                    bodies.add(body);
                }
                handler.handle(exchange);
            });
            server.setExecutor(Executors.newCachedThreadPool());
            server.start();
            url = "http://127.0.0.1:" + server.getAddress().getPort();
        }

        int hits(String path) {
            synchronized (paths) {
                int n = 0;
                for (String p : paths) {
                    if (p.equals(path)) {
                        n++;
                    }
                }
                return n;
            }
        }

        List<String> paths() {
            synchronized (paths) {
                return new ArrayList<>(paths);
            }
        }

        String lastBody() {
            synchronized (paths) {
                return bodies.isEmpty() ? null : bodies.get(bodies.size() - 1);
            }
        }

        void stop() {
            server.stop(0);
        }
    }

    private static String drain(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[4096];
        int read;
        while ((read = in.read(buffer)) != -1) {
            sb.append(new String(buffer, 0, read, StandardCharsets.ISO_8859_1));
        }
        in.close();
        return sb.toString();
    }

    private static void respond(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream out = exchange.getResponseBody()) {
            out.write(bytes);
        }
    }

    /** フリートの基点を装う: プロジェクトトークンのログインは 200、その他は status で {}。 */
    private FakeServer newSteadyServer(int status) throws IOException {
        FakeServer server = new FakeServer(exchange -> {
            if (exchange.getRequestURI().getPath().equals("/identifier/projectToken/login")) {
                respond(exchange, 200, "{\"access_token\":\"pt\",\"token_type\":\"Bearer\",\"expires_in\":3600}");
            } else {
                respond(exchange, status, status == 200 ? "{}" : "{\"message\":\"[]\"}");
            }
        });
        servers.add(server);
        return server;
    }

    /** 「listen していたが今は誰も居ない」宛先（接続拒否）。 */
    private static String closedPortUrl() throws IOException {
        try (ServerSocket socket = new ServerSocket(0, 1, InetAddress.getByName("127.0.0.1"))) {
            return "http://127.0.0.1:" + socket.getLocalPort();
        }
    }

    private Gs2RestSession newRestSession(String steadyEndpoint) {
        Gs2RestSession session = new Gs2RestSession(CREDENTIAL, Region.AP_NORTHEAST_1, steadyEndpoint);
        sessions.add(session);
        return session;
    }

    private Gs2RestSession openRestSession(String steadyEndpoint) {
        Gs2RestSession session = newRestSession(steadyEndpoint);
        session.open();
        return session;
    }

    // ---------------------------------------------------------------- 生成クライアントの形の要求

    private static class StatusResult implements IResult {
    }

    /** 生成クライアントのタスクと同じ形（URL は Gs2RestSession.EndpointHost から組む）。 */
    private static class StatusTask extends Gs2RestSessionTask<StatusResult> {
        private final Gs2RestSession session;
        private final HttpTask.Method method;

        StatusTask(Gs2RestSession session, HttpTask.Method method, AsyncAction<AsyncResult<StatusResult>> callback) {
            super(session, callback);
            this.session = session;
            this.method = method;
        }

        @Override
        public StatusResult parse(JsonNode data) {
            return new StatusResult();
        }

        @Override
        protected void executeImpl() {
            String url = Gs2RestSession.EndpointHost
                    .replace("{service}", "account")
                    .replace("{region}", session.getRegion().getName())
                    + "/status";

            builder
                    .setMethod(method)
                    .setUrl(url)
                    .setHeader("Content-Type", "application/json")
                    .setHttpResponseHandler(this);
            if (method == HttpTask.Method.POST) {
                builder.setBody("{\"k\":\"v\"}".getBytes(StandardCharsets.UTF_8));
            }
            builder.build().send();
        }
    }

    private Gs2Exception send(Gs2RestSession session, HttpTask.Method method) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<AsyncResult<StatusResult>> result = new AtomicReference<>();
        session.execute(new StatusTask(session, method, r -> {
            result.set(r);
            latch.countDown();
        }));
        assertTrue("応答が来ません", latch.await(60, TimeUnit.SECONDS));
        return result.get().getError();
    }

    /** HttpTaskBuilder が組み上げる実際の宛先（生成クライアントが通る経路）。 */
    private static String builtUrl(String steadyEndpoint, String url, String regionName) {
        HttpTask task = HttpTaskBuilder
                .create()
                .setMethod(HttpTask.Method.GET)
                .setSteadyEndpoint(steadyEndpoint, Gs2RestSession.EndpointHost, regionName)
                .setUrl(url)
                .build();
        return task.getHttpRequest().getURI().toString();
    }

    // ---------------------------------------------------------------- client の差し替え

    /** client.execute を数え、最初の n 回を指定の誤りで落とす（本物には通さない）。 */
    private static class CountingClient implements InvocationHandler {
        private final HttpClient real;
        final AtomicInteger attempts = new AtomicInteger();
        private final int failFirst;
        private final IOException error;
        private final boolean markRequestSent;

        CountingClient(HttpClient real, int failFirst, IOException error, boolean markRequestSent) {
            this.real = real;
            this.failFirst = failFirst;
            this.error = error;
            this.markRequestSent = markRequestSent;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("execute")) {
                if (attempts.incrementAndGet() <= failFirst) {
                    if (markRequestSent) {
                        for (Object arg : args) {
                            if (arg instanceof HttpContext) {
                                ((HttpContext) arg).setAttribute(HttpCoreContext.HTTP_REQ_SENT, Boolean.TRUE);
                            }
                        }
                    }
                    throw error;
                }
            }
            try {
                return method.invoke(real, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
    }

    private CountingClient failFirstConnects(int n, IOException error, boolean markRequestSent) {
        CountingClient counting = new CountingClient(HttpTask.client, n, error, markRequestSent);
        HttpTask.client = (HttpClient) Proxy.newProxyInstance(
                HttpClient.class.getClassLoader(),
                new Class<?>[]{HttpClient.class},
                counting
        );
        return counting;
    }

    // ---------------------------------------------------------------- URL

    /** ★Steady 未設定なら、従来の EndpointHost と byte 単位で同じ URL（実行時の template 上書きも従来どおり）。 */
    @Test
    public void endpointHostWithoutSteadyIsUnchanged() {
        Gs2RestSession session = new Gs2RestSession(CREDENTIAL, Region.AP_NORTHEAST_1);
        assertNull(session.getSteadyEndpoint());
        assertEquals("https://account.ap-northeast-1.gen2.gs2io.com", session.endpointHost("account"));

        String generated = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", "ap-northeast-1") + "/status";
        assertEquals("Steady 未設定で URL が変わりました", generated, builtUrl(null, generated, "ap-northeast-1"));
        assertEquals(generated, builtUrl("   ", generated, "ap-northeast-1"));

        Gs2RestSession.EndpointHost = "https://{service}.{region}.dev.gen2.gs2io.com";
        assertEquals("https://account.ap-northeast-1.dev.gen2.gs2io.com", session.endpointHost("account"));
    }

    /** ★Steady 設定時は <steady>/<service>（末尾の / と空白は落とす）。呼び手が独自に指定した宛先は不変。 */
    @Test
    public void endpointHostWithSteady() {
        Gs2RestSession session = new Gs2RestSession(CREDENTIAL, Region.AP_NORTHEAST_1, " https://bs-dev.example.test/ ");
        assertEquals("https://bs-dev.example.test", session.getSteadyEndpoint());
        assertEquals("https://bs-dev.example.test/account", session.endpointHost("account"));

        // 生成クライアントが組んだ URL（共有クラウドの template 由来）は Steady 配下へ差し替わる
        String generated = "https://account.ap-northeast-1.gen2.gs2io.com/status?limit=10";
        assertEquals("https://bs-dev.example.test/account/status?limit=10",
                builtUrl("https://bs-dev.example.test/", generated, "ap-northeast-1"));

        // 実行時に template を書き換えていても（dev 向け運用）Steady が勝つ
        Gs2RestSession.EndpointHost = "https://{service}.{region}.dev.gen2.gs2io.com";
        assertEquals("https://bs-dev.example.test/account/status",
                builtUrl("https://bs-dev.example.test", "https://account.ap-northeast-1.dev.gen2.gs2io.com/status", "ap-northeast-1"));

        // template の形に合わない宛先（呼び手の override）は Steady より強い
        assertEquals("https://custom.example.test/account/status",
                builtUrl("https://bs-dev.example.test", "https://custom.example.test/account/status", "ap-northeast-1"));

        session.setSteadyEndpoint("   ");
        assertNull(session.getSteadyEndpoint());
        assertEquals("https://account.ap-northeast-1.dev.gen2.gs2io.com", session.endpointHost("account"));
    }

    /** ★接続段階の上限は Steady のときだけ。共有クラウドは RequestConfig を触らない。 */
    @Test
    public void connectTimeoutOnlyForSteady() {
        String generated = "https://account.ap-northeast-1.gen2.gs2io.com/status";
        HttpTask shared = HttpTaskBuilder
                .create()
                .setMethod(HttpTask.Method.GET)
                .setSteadyEndpoint(null, Gs2RestSession.EndpointHost, "ap-northeast-1")
                .setUrl(generated)
                .build();
        assertNull("共有クラウドは従来どおり", shared.getHttpRequest().getConfig());

        HttpTask steady = HttpTaskBuilder
                .create()
                .setMethod(HttpTask.Method.GET)
                .setSteadyEndpoint("https://bs-dev.example.test", Gs2RestSession.EndpointHost, "ap-northeast-1")
                .setUrl(generated)
                .build();
        assertNotNull(steady.getHttpRequest().getConfig());
        assertEquals(5000, Steady.CONNECT_TIMEOUT_MILLIS);
        assertEquals(Steady.CONNECT_TIMEOUT_MILLIS, steady.getHttpRequest().getConfig().getConnectTimeout());
    }

    /** ★ログインも生成クライアント（無改造）も Steady の基点へ向く。 */
    @Test
    public void loginAndGeneratedClientGoToSteady() throws Exception {
        FakeServer steady = newSteadyServer(200);
        Gs2RestSession session = openRestSession(steady.url);
        assertEquals("ログインが基点へ行っていません: " + steady.paths(), 1, steady.hits("/identifier/projectToken/login"));

        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<AsyncResult<DescribeNamespacesResult>> result = new AtomicReference<>();
        new Gs2AccountRestClient(session).describeNamespacesAsync(new DescribeNamespacesRequest(), r -> {
            result.set(r);
            latch.countDown();
        });
        assertTrue("応答が来ません", latch.await(60, TimeUnit.SECONDS));
        assertNull("生成クライアントの送信に失敗: " + result.get().getError(), result.get().getError());
        assertEquals("生成クライアントが基点へ行っていません: " + steady.paths(), 1, steady.hits("/account/"));
    }

    // ---------------------------------------------------------------- 再送

    /** ★接続段階の失敗 → 同じ要求をもう 1 回だけ（POST の本文も保たれる）。 */
    @Test
    public void connectFailureIsRetriedOnce() throws Exception {
        FakeServer steady = newSteadyServer(200);
        Gs2RestSession session = openRestSession(steady.url);
        CountingClient counting = failFirstConnects(1, new ConnectException("connection refused"), false);

        assertNull("再送で成功するはず", send(session, HttpTask.Method.POST));
        assertEquals("server hits", 1, steady.hits("/account/status"));
        assertEquals("1 回落ちて 1 回通る", 2, counting.attempts.get());
        // 本文は圧縮されて届くので中身は見ず、空でないことだけ見る。
        assertNotNull(steady.lastBody());
        assertFalse("再送の本文が空", steady.lastBody().isEmpty());
    }

    /** ★2 回続けて接続段階で落ちたら諦める（3 回目は無い）。 */
    @Test
    public void connectFailureTwiceGivesUp() throws Exception {
        FakeServer steady = newSteadyServer(200);
        Gs2RestSession session = openRestSession(steady.url);
        CountingClient counting = failFirstConnects(100, new ConnectException("connection refused"), false);

        assertNotNull("失敗が返るはず", send(session, HttpTask.Method.GET));
        assertEquals("再送は 1 回だけ", 2, counting.attempts.get());
        assertEquals("server hits", 0, steady.hits("/account/status"));
    }

    /** ★送信を始めた後の失敗は再送しない（届いたかもしれない）。 */
    @Test
    public void failureAfterRequestSentIsNotRetried() throws Exception {
        FakeServer steady = newSteadyServer(200);
        Gs2RestSession session = openRestSession(steady.url);
        CountingClient counting = failFirstConnects(1, new SocketTimeoutException("read timed out"), true);

        Gs2Exception error = send(session, HttpTask.Method.POST);
        assertTrue("失敗が返るはず: " + error, error instanceof BadRequestException);
        assertEquals("送信後の失敗を再送しました", 1, counting.attempts.get());
        assertEquals(0, steady.hits("/account/status"));
    }

    /** ★送信後の失敗（5xx）は再送しない。 */
    @Test
    public void serverErrorIsNotRetried() throws Exception {
        FakeServer steady = newSteadyServer(500);
        Gs2RestSession session = openRestSession(steady.url);
        CountingClient counting = failFirstConnects(0, new ConnectException("unused"), false);

        Gs2Exception error = send(session, HttpTask.Method.POST);
        assertTrue("5xx が伝わっていません: " + error, error instanceof InternalServerErrorException);
        assertEquals("再送してはいけない: " + steady.paths(), 1, steady.hits("/account/status"));
        assertEquals(1, counting.attempts.get());
    }

    /** ★Steady 未設定なら接続失敗でも再送しない（共有クラウドの挙動は不変）。 */
    @Test
    public void noRetryWithoutSteady() throws Exception {
        FakeServer server = newSteadyServer(200);
        // ログイン先だけ偽サーバーへ（Steady は使わない）。
        Gs2RestSession.EndpointHost = server.url + "/{service}";
        Gs2RestSession session = openRestSession(null);
        assertEquals(1, server.hits("/identifier/projectToken/login"));

        CountingClient counting = failFirstConnects(1, new ConnectException("connection refused"), false);
        assertNotNull("再送しないので失敗が返るはず", send(session, HttpTask.Method.GET));
        assertEquals(1, counting.attempts.get());
    }

    // ---------------------------------------------------------------- 判定

    @Test
    public void isConnectFailureTable() {
        assertTrue("dns", Steady.isConnectFailure(new UnknownHostException("x"), false));
        assertTrue("connect refused", Steady.isConnectFailure(new ConnectException("refused"), false));
        assertTrue("HttpHostConnectException", Steady.isConnectFailure(
                new HttpHostConnectException(new ConnectException("refused"), new HttpHost("127.0.0.1", 1)), false));
        assertTrue("connect timeout", Steady.isConnectFailure(new ConnectTimeoutException("timeout"), false));
        assertTrue("tls handshake", Steady.isConnectFailure(new SSLHandshakeException("bad cert"), false));
        assertTrue("handshake timeout (未送信)", Steady.isConnectFailure(new SocketTimeoutException("read timed out"), false));
        assertTrue("network unreachable", Steady.isConnectFailure(new SocketException("Network is unreachable"), false));

        // 送信を始めた後は、どの型でも再送しない（届いたかもしれない）
        assertFalse("read timeout (送信済み)", Steady.isConnectFailure(new SocketTimeoutException("read timed out"), true));
        assertFalse("reset (送信済み)", Steady.isConnectFailure(new SocketException("Connection reset"), true));
        assertFalse("tls (送信済み)", Steady.isConnectFailure(new SSLHandshakeException("x"), true));
        // 接続段階ではない失敗
        assertFalse("eof", Steady.isConnectFailure(new EOFException(), false));
        assertFalse("generic io", Steady.isConnectFailure(new IOException("x"), false));
        assertFalse("null", Steady.isConnectFailure(null, false));
    }

    @Test
    public void isSteadyUrlTable() {
        assertTrue("配下は真", Steady.isSteadyUrl("https://bs.example.test/", "https://bs.example.test/account/status"));
        assertTrue("基点そのもの", Steady.isSteadyUrl("https://bs.example.test", "https://bs.example.test"));
        assertFalse("前方一致だけでは駄目", Steady.isSteadyUrl("https://bs.example.test", "https://bs.example.test.evil/x"));
        assertFalse("Steady 未設定は偽", Steady.isSteadyUrl(null, "https://bs.example.test/account"));
        assertFalse(Steady.isSteadyUrl("   ", "https://bs.example.test/account"));
    }

    // ---------------------------------------------------------------- 接続段階の上限（到達しない IP）

    /**
     * ★到達しない IP（SYN が落ちる）への接続が 5 秒前後で諦めること。
     * 環境が即座に「到達不能」を返すこともあるので、上限（OS 既定の数十秒に落ちていない）だけを見る。
     */
    @Test(timeout = 60000)
    public void connectStageIsBoundedForSteady() throws Exception {
        FakeServer steady = newSteadyServer(200);
        Gs2RestSession session = openRestSession(steady.url);
        // 基点だけ到達しない IP に差し替える（ログインは済んでいる）。
        session.setSteadyEndpoint("http://10.255.255.1:18080");

        long began = System.currentTimeMillis();
        assertNotNull("失敗が返るはず", send(session, HttpTask.Method.GET));
        long elapsed = System.currentTimeMillis() - began;
        System.out.println("connectStageIsBoundedForSteady: elapsed=" + elapsed + "ms (接続 2 回分)");
        // 接続段階 2 回（再送 1 回）なので上限は 5 秒 × 2 ＋ 余裕。
        assertTrue("接続段階に上限が効いていません: " + elapsed + "ms", elapsed < 20000);
    }

    // ---------------------------------------------------------------- WebSocket

    /** ★WebSocket の接続先は wss://<host>/（http:// の基点は ws://）。handshake の上限も Steady のときだけ。 */
    @Test
    public void webSocketUrlAndConnectTimeout() {
        Gs2WebSocketSession shared = new Gs2WebSocketSession(CREDENTIAL, Region.AP_NORTHEAST_1);
        assertEquals("wss://gateway-ws.ap-northeast-1.gen2.gs2io.com", shared.webSocketUrl());
        assertEquals(Gs2WebSocketSession.WebSocketEndpointHost.replace("{region}", "ap-northeast-1"), shared.webSocketUrl());
        assertEquals("未設定は上限無し", 0, shared.webSocketConnectTimeoutMillis());

        Gs2WebSocketSession https = new Gs2WebSocketSession(CREDENTIAL, "ap-northeast-1", "https://bs-dev.example.test/");
        assertEquals("https://bs-dev.example.test", https.getSteadyEndpoint());
        assertEquals("wss://bs-dev.example.test/", https.webSocketUrl());
        assertEquals("https://bs-dev.example.test/identifier", https.endpointHost("identifier"));
        assertEquals(Steady.CONNECT_TIMEOUT_MILLIS, https.webSocketConnectTimeoutMillis());

        Gs2WebSocketSession http = new Gs2WebSocketSession(CREDENTIAL, Region.AP_NORTHEAST_1, "http://127.0.0.1:8080");
        assertEquals("ws://127.0.0.1:8080/", http.webSocketUrl());

        // 壊れた基点は従来の template に落ちる
        Gs2WebSocketSession broken = new Gs2WebSocketSession(CREDENTIAL, Region.AP_NORTHEAST_1, "not a url");
        assertEquals("wss://gateway-ws.ap-northeast-1.gen2.gs2io.com", broken.webSocketUrl());
    }

    /** ★WebSocket セッションのログインも Steady の基点へ向く。 */
    @Test
    public void webSocketSessionLogsInAtSteady() throws Exception {
        FakeServer steady = newSteadyServer(200);
        Gs2WebSocketSession session = new Gs2WebSocketSession(CREDENTIAL, Region.AP_NORTHEAST_1, steady.url);
        sessions.add(session);
        session.open();
        assertEquals("ログインが基点へ行っていません: " + steady.paths(), 1, steady.hits("/identifier/projectToken/login"));
        assertEquals("ws://" + steady.url.substring("http://".length()) + "/", session.webSocketUrl());
    }

    /** ★閉じた港（接続拒否）でも 2 回で諦め、従来どおり失敗として返る。 */
    @Test
    public void connectRefusedGivesUpAfterRetry() throws Exception {
        String dead = closedPortUrl();
        FakeServer steady = newSteadyServer(200);
        Gs2RestSession session = openRestSession(steady.url);
        session.setSteadyEndpoint(dead);
        Gs2Exception error = send(session, HttpTask.Method.GET);
        if (!(error instanceof BadRequestException)) {
            fail("接続拒否が失敗として返っていません: " + error);
        }
        assertEquals("偽サーバーには届かない", 0, steady.hits("/account/status"));
    }
}
