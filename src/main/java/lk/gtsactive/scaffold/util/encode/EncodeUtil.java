package lk.gtsactive.scaffold.util.encode;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodeUtil {

    public static String base64Encode(String value) {
        return Base64.getEncoder()
                .encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    public static String base64Decode(String value) {
        return new String(Base64.getDecoder()
                .decode(value), StandardCharsets.UTF_8);
    }

    public static String urlEncode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    public static String urlDecode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }
}
