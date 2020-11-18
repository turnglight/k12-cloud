package k12.base.web.client.user;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientUserConfig {

    @Bean
    public FilterRegistrationBean clientUserFilterBean() {
        return new FilterRegistrationBean(new ClientUserFilter());
    }

}
