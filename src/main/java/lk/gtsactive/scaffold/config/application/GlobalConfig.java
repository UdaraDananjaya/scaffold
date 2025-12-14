package lk.gtsactive.scaffold.config.application;
import lk.gtsactive.common.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {
    @Bean
    public Logger logger() {return new Logger();}
}
