package k12.base.commons.jackson;

import k12.base.commons.jackson.mapper.BootObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Jackson 常用方法静态化
 */
public final class Jackson {

    private static final ObjectMapper mapper = new BootObjectMapper();

    /**
     * object to json
     *
     * @param object to be wrote as json
     * @return
     */
    public static String asJson(final Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JacksonException("Jackson asJson cause Exception", e);
        }
    }

    /**
     * json to object
     *
     * @param json to be read as object
     * @param clz  target object Class
     * @param <T>
     * @return
     */
    public static <T> T asObject(final String json, Class<T> clz) {
        try {
            return mapper.readValue(json, clz);
        } catch (IOException e) {
            throw new JacksonException("Jackson asObject cause Exception", e);
        }
    }

    /**
     * json to object
     *
     * @param json         to be read as object
     * @param valueTypeRef target object TypeReference
     * @param <T>
     * @return
     */
    public static <T> T asObject(final String json, TypeReference valueTypeRef) {
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (IOException e) {
            throw new JacksonException("Jackson asObject cause Exception", e);
        }
    }

    /**
     * JacksonException
     */
    public static final class JacksonException extends RuntimeException {
        public JacksonException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
