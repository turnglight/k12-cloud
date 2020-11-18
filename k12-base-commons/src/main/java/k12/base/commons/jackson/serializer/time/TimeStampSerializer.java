package k12.base.commons.jackson.serializer.time;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * 通用 Timestamp 序列化器：Timestamp 转时间戳毫秒
 */
public class TimeStampSerializer extends JsonSerializer<Timestamp> {

    @Override
    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (null != timestamp) {
            jsonGenerator.writeNumber(timestamp.getTime());
        }
    }
}
