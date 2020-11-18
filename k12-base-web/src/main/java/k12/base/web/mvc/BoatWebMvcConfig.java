package k12.base.web.mvc;

import k12.base.commons.jackson.mapper.BootObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class BoatWebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 定制 SpringContext 中的 ObjectMapper，使其行为与 {@link BootObjectMapper} 一致
     */
    @Bean
    Jackson2ObjectMapperBuilderCustomizer boatJackson2ObjectMapperBuilderCustomizer () {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder
                        .serializationInclusion(JsonInclude.Include.NON_NULL)
                        .failOnEmptyBeans(false)
                        .failOnUnknownProperties(false)
                        .modules(new BootObjectMapper.StringTrimModule());
            }
        };
    }

    /**
     * provide messageSource
     */
    @Bean(name = "messageSource")
    MessageSource messageSource(){
        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        bean.setBasename("classpath:ValidationMessages");
        bean.setDefaultEncoding("UTF-8");
        return bean;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messageSource());
        return factory;
    }

}
