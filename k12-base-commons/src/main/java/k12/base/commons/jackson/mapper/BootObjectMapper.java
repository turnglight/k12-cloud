package k12.base.commons.jackson.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;

/**
 * A custom ObjectMapper for general using
 */
public class BootObjectMapper extends ObjectMapper {

    public BootObjectMapper() {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);// 设置序列化时，null字段不序列化
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);// 设置序列化时，遇到空对象时不抛异常
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);// 设置反序列化时，遇到未知属性不抛异常
        this.registerModule(new StringTrimModule());
    }

    /**
     * 序列化/反序列化时，对 String 类型参数，执行 trim() 操作
     */
    public static class StringTrimModule extends SimpleModule {
        public StringTrimModule() {
            addSerializer(String.class, new StdScalarSerializer<String>(String.class) {
                @Override
                public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                    gen.writeString(value.trim());
                }
            });
            addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) {
                @Override
                public String deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException, JsonProcessingException {
                    return jsonParser.getValueAsString().trim();
                }
            });
        }
    }

}
