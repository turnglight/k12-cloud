package k12.base.commons.jackson.serializer.time;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;

/**
 * 通用 Joda DateTime 序列化器：DateTime 转时间戳毫秒
 */
public class JodaDateTimeSerializer extends JsonSerializer<DateTime> {

    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (null != dateTime) {
            jsonGenerator.writeNumber(dateTime.getMillis());
        }
    }
}
