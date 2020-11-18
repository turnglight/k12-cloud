package k12.base.web.client.version;

import org.springframework.util.Assert;

public class ClientVersionContext {

    private static final ThreadLocal<ClientVersion> clientVersionHolder = new ThreadLocal<>();

    static void clear() {
        clientVersionHolder.remove();
    }

    public static void set(ClientVersion version) {
        Assert.notNull(version, "Only non-null ClientVersion instances are permitted");
        clientVersionHolder.set(version);
    }

    public static ClientVersion get() {
        ClientVersion version = clientVersionHolder.get();
        if (version == null) {
            throw new IllegalStateException("can not trace the ClientVersion!");
        }
        return version;
    }

}
