package k12.base.mybatis.util;

import k12.base.mybatis.annotation.Column;
import k12.base.mybatis.annotation.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by kinginblue on 2017/2/7.
 */
public class MybatisUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisUtil.class);

    public static String requireTableName(Class<?> parameterType) {
        Table table = parameterType.getAnnotation(Table.class);
        if (null == table) {
            throw new IllegalArgumentException("@Table annotation required!");
        }
        return table.name();
    }

    public static String getColumnNameFromField(Field field) {
        Column column = field.getAnnotation(Column.class);
        if (column != null && column.name() != null && !"".equals(column.name())) {
            return column.name();
        }
        return field.getName();
    }

    public static boolean isIdColumn(Field field) {
        Column column = field.getAnnotation(Column.class);
        return column != null && column.id();
    }

}
