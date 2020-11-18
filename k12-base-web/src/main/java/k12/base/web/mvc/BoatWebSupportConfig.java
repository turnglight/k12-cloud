package k12.base.web.mvc;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

@Configuration
public class BoatWebSupportConfig {

    /**
     * provide Orika MapperFactory
     */
    @Bean
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    /**
     * provide Orika MapperFacade
     * @return
     */
    @Bean
    public MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }

    /**
     * supports Spring-Data projection
     */
    @Bean
    public SpelAwareProxyProjectionFactory projectionFactory() {
        return new SpelAwareProxyProjectionFactory();
    }

}
