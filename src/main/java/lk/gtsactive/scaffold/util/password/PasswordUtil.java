package lk.gtsactive.scaffold.util.password;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class PasswordUtil {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static String encode(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }


    public static boolean matches(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    public static boolean isEncoded(String password) {
        if (password != null && password.startsWith("$2a$")) return true;
        assert password != null;
        return password.startsWith("$2b$")
                || password.startsWith("$2y$");
    }
}
