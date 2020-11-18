package k12.base.mybatis.util;

import k12.base.data.model.Paging;
import k12.base.mybatis.annotation.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by kinginblue on 2017/5/17.
 */
public class ModelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelUtil.class);

    public static <MODEL extends Paging, DTO> DTO map(MODEL model, Class<DTO> dtoClass) {
        if (model == null) return null;

        try {
            DTO dto = dtoClass.newInstance();

            for (Field modelFiled : model.getClass().getDeclaredFields()) {

                if (modelFiled.isAnnotationPresent(Column.class)) {

                    Field dotFiled;
                    try {
                        dotFiled = dtoClass.getDeclaredField(modelFiled.getName());
                    } catch (NoSuchFieldException e) {
                        continue;
                    }

                    modelFiled.setAccessible(true);
                    dotFiled.setAccessible(true);

                    dotFiled.set(dto, modelFiled.get(model));
                }
            }
            return dto;

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

}
