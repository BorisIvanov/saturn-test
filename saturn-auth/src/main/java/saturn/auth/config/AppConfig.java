package saturn.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"saturn.common", "saturn.auth"})
@PropertySource("classpath:application.properties")
public class AppConfig {

}
