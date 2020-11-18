package k12.base.web.client.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientUser {

    public static final String CLIENT_USER_ID = "client-user-id";

    public static final String CLIENT_USER_NAME = "client-user-name";

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

}
