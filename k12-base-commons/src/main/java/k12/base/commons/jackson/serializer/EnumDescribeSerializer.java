package k12.base.commons.jackson.serializer;

import k12.base.commons.itf.Describable;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 通用 枚举 序列化器：只要实现了 Describable 接口的枚举类，都可以用此序列化器返回自身的描述。
 */
public class EnumDescribeSerializer extends JsonSerializer<Describable> {

    @Override
    public void serialize(Describable value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(value.describe());
    }
}
