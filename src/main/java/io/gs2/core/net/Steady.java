package io.gs2.core.net;

import org.apache.http.conn.ConnectTimeoutException;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public final class Steady {

    public static final int CONNECT_TIMEOUT_MILLIS = 5000;

    private static final String SERVICE_PLACEHOLDER = "{service}";
    private static final String REGION_PLACEHOLDER = "{region}";

    private Steady() {
    }

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

    public static String restEndpoint(String steady, String sharedTemplate, String service, String regionName) {
        String base = normalize(steady);
        if (base == null) {
            return resolveTemplate(sharedTemplate, regionName).replace(SERVICE_PLACEHOLDER, service);
        }
        return base + "/" + service;
    }

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

    static String rewriteUrl(String steady, String sharedTemplate, String regionName, String url) {
        String base = normalize(steady);
        if (base == null || url == null || sharedTemplate == null) {
            return url;
        }
        if (isSteadyUrl(base, url)) {
            return url;
        }
        String resolved = resolveTemplate(sharedTemplate, regionName);
        int serviceAt = resolved.indexOf(SERVICE_PLACEHOLDER);
        if (serviceAt < 0) {
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

    static boolean isSteadyUrl(String steady, String url) {
        String base = normalize(steady);
        if (base == null || url == null) {
            return false;
        }
        return url.equals(base) || url.startsWith(base + "/");
    }

    static boolean isConnectFailure(IOException e, boolean requestSent) {
        if (e == null || requestSent) {
            return false;
        }
        return e instanceof UnknownHostException
                || e instanceof SocketException            // ConnectException / HttpHostConnectException / NoRouteToHostException
                || e instanceof ConnectTimeoutException
                || e instanceof SocketTimeoutException
                || e instanceof SSLException;              // SSLHandshakeException / SSLPeerUnverifiedException
    }

    private static String resolveTemplate(String template, String regionName) {
        if (template == null) {
            return null;
        }
        return template.replace(REGION_PLACEHOLDER, regionName == null ? "" : regionName);
    }
}
