package io.gs2.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodingUtil {

    public static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }

}
