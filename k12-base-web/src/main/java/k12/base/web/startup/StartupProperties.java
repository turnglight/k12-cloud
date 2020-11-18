package k12.base.web.startup;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Server 资源外部化配置
 */
@Data
@ConfigurationProperties(prefix = "startup")
public class StartupProperties {

    /**
     * 服务器域名
     */
    private String serverDomain;

    /**
     * 服务器程序上下文
     */
    private String serverContext;

    /**
     * 外部静态资源定位地址
     */
    private String staticResLocate;

    /**
     * 内部静态资源定位地址
     */
    private String internalResLocate;

}
