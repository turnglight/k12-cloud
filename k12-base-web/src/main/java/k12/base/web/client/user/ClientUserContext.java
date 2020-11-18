package k12.base.web.client.user;

import k12.base.web.exception.TokenException;
import org.springframework.util.Assert;

public class ClientUserContext {

    private static final ThreadLocal<ClientUser> clientUserHolder = new ThreadLocal<>();

    static void clear() {
        clientUserHolder.remove();
    }

    public static void set(ClientUser user) {
        Assert.notNull(user, "Only non-null ClientUser instances are permitted");
        clientUserHolder.set(user);
    }

    public static ClientUser get() {
        ClientUser user = clientUserHolder.get();
        if (user == null) {
            throw new TokenException("can not trace the ClientUser!");
        }
        return user;
    }

    public static ClientUser orElse(ClientUser other) {
        ClientUser user = clientUserHolder.get();
        if (user != null) {
            return user;
        }
        return other;
    }

}
