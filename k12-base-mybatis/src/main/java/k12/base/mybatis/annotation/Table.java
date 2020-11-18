package k12.base.mybatis.annotation;

import java.lang.annotation.*;

/**
 * Created by kinginblue on 2017/2/6.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    // the table name
    String name();
}
