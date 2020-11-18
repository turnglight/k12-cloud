package k12.base.mybatis.annotation;

import java.lang.annotation.*;

/**
 * Created by kinginblue on 2017/2/8.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryBetween {

    // the column name
    String column();
}
