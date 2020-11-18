package k12.base.web.swagger;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

/**
 * Swagger 默认的自动配置
 */
@Configuration
@ConditionalOnClass(EnableSwagger2.class)
@EnableSwagger2
public class BoatSwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfo(applicationName, "provider接口仅用于内部服务，客户端禁止调用。", "1.0", "urn:tos",
                DEFAULT_CONTACT, "", "", new ArrayList<>());
    }

}
