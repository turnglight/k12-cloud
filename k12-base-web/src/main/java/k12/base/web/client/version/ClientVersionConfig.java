package k12.base.web.client.version;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientVersionConfig {

    @Bean
    public FilterRegistrationBean clientVersionFilterBean() {
        return new FilterRegistrationBean(new ClientVersionFilter());
    }

}
