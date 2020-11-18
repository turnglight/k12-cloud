package k12.base.web.autoconfigure;

import k12.base.web.client.user.ClientUserConfig;
import k12.base.web.client.version.ClientVersionConfig;
import k12.base.web.kafka.BoatKafkaConfig;
import k12.base.web.mvc.BoatWebMvcConfig;
import k12.base.web.mvc.BoatWebSupportConfig;
import k12.base.web.startup.StartupConfig;
import k12.base.web.swagger.BoatSwaggerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration for Boat Web.
 */
@Configuration
@Import({
        StartupConfig.class,
        BoatWebMvcConfig.class,
        BoatWebSupportConfig.class,
        BoatSwaggerConfig.class,
        BoatKafkaConfig.class
//        ClientVersionConfig.class,
//        ClientUserConfig.class
})
@ComponentScan(basePackages = {
        "k12.base.web.handler"
})
public class BoatWebAutoConfiguration {

}
