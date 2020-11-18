package k12.base.commons.jackson.serializer.mask;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 序列化器：手机号码打码
 */
public class MobileMaskSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String result = "";
        if (null != value && value.length() == 11) {
            result = value.substring(0, 3) + "****" + value.substring(7);
        }
        jsonGenerator.writeString(result);
    }
}
