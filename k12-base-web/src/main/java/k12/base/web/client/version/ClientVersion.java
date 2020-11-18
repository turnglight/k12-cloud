package k12.base.web.client.version;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 客户端版本信息
 */
@Data
@AllArgsConstructor
public class ClientVersion {

    public static final String CLIENT_VERSION_TYPE = "client-version-type";

    public static final String CLIENT_VERSION_CHANNEL = "client-version-channel";

    public static final String CLIENT_VERSION_NAME = "client-version-name";

    public static final String CLIENT_VERSION_NO = "vest-no";

    /**
     * 客户端类型
     */
    private String versionType;

    /**
     * 客户端渠道
     */
    private String versionChannel;

    /**
     * 客户端版本名
     */
    private String versionName;

    /**
     * 马甲号
     */
    private String vestNo;
}
