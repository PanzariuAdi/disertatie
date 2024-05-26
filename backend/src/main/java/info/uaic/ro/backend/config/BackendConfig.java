package info.uaic.ro.backend.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@Configuration
public class BackendConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
