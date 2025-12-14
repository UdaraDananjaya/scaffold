package lk.gtsactive.scaffold.config.security;

import lk.gtsactive.scaffold.config.application.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {

    private final AppProperties appProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(appProperties.getCors().getAllowedOrigins())
                .allowedMethods(appProperties.getCors().getAllowedMethods())
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
