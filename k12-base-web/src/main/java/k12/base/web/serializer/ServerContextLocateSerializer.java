package k12.base.web.serializer;

import k12.base.web.startup.StartupConfig;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 服务器上下文序列化器：在前面补上服务器上下文地址定位。
 */
public class ServerContextLocateSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (null == value || "".equals(value)) {
            gen.writeString("");

        } else if (value.startsWith("http") || value.startsWith("www")) {
            gen.writeString(value);

        } else {
            gen.writeString(StartupConfig.SERVER_CONTEXT + value);
        }
    }
}
