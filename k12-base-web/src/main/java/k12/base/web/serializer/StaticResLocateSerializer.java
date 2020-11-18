package k12.base.web.serializer;

import k12.base.web.startup.StartupConfig;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 外部静态序列化器：如果是网络图片则不处理，否则在资源前面补上外部资源服务器地址定位。
 */
public class StaticResLocateSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (null == value || "".equals(value)) {
            gen.writeString("");

        } else if (value.startsWith("http") || value.startsWith("www")) {
            gen.writeString(value);

        } else {
            gen.writeString(StartupConfig.STATIC_RES_LOCATE + value);
        }
    }
}
