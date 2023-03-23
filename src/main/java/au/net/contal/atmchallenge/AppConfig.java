package au.net.contal.atmchallenge;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Data
@EnableAspectJAutoProxy
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private List<String> allowedOrigins = new ArrayList<>();
}
