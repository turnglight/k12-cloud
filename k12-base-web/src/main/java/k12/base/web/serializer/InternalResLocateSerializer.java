package k12.base.web.serializer;

import k12.base.web.startup.StartupConfig;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 服务器内部静态资源序列化器：在资源前面补上服务器内部资源地址定位。
 */
public class InternalResLocateSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (null == value || "".equals(value)) {
            gen.writeString("");

        } else if (value.startsWith("http") || value.startsWith("www")) {
            gen.writeString(value);

        } else {
            gen.writeString(StartupConfig.INTERNAL_RES_LOCATE + value);
        }
    }
}
