package k12.base.web.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Server 资源外部化配置
 */
@Configuration
@EnableConfigurationProperties({StartupProperties.class})
public class StartupConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupConfig.class);

    /**
     * 服务器域名
     */
    public static volatile String SERVER_DOMAIN = "";

    /**
     * 服务器程序上下文
     */
    public static volatile String SERVER_CONTEXT = "";

    /**
     * 外部静态资源定位地址
     */
    public static volatile String STATIC_RES_LOCATE = "";

    /**
     * 内部静态资源定位地址
     */
    public static volatile String INTERNAL_RES_LOCATE = "";

    /**
     * logo 内部相对路径
     */
    public static String PATH_TO_LOGO = "img/logo.png";

    @Autowired
    public StartupConfig(StartupProperties properties) {
        LOGGER.debug(properties.toString());

        SERVER_DOMAIN = properties.getServerDomain();
        SERVER_CONTEXT = properties.getServerContext();
        STATIC_RES_LOCATE = properties.getStaticResLocate();
        INTERNAL_RES_LOCATE = properties.getInternalResLocate();

    }

}
