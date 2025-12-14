package lk.gtsactive.scaffold.config.application;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppProperties {

    private String name;
    private String version;
    private String description;
    private String environment;

    private Security security = new Security();
    private Cors cors = new Cors();

    @Getter
    @Setter
    public static class Security {

        @NotBlank
        private String jwtSecret;
        @Min(60000)
        private long jwtExpirationMs;
        @Min(60000)
        private long refreshTokenExpirationMs;
    }

    @Getter
    @Setter
    public static class Cors {
        private String[] allowedOrigins;
        private String[] allowedMethods;
    }
}

