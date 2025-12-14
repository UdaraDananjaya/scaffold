package lk.gtsactive.scaffold.util.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "1234567890123456"; // 16 bytes

    private static SecretKeySpec key() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
    }

    public static String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key());
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(plainText.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public static String decrypt(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key());
            return new String(cipher.doFinal(
                    Base64.getDecoder().decode(encryptedText)));
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}
