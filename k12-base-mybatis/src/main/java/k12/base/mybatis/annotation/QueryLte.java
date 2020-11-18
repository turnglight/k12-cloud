package k12.base.mybatis.annotation;

import java.lang.annotation.*;

/**
 * Created by cecil on 17-9-26.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryLte {

    // the column name
    String column();
}
