package k12.base.mybatis.annotation;

import java.lang.annotation.*;

/**
 * Created by kinginblue on 2017/2/6.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    // the column name
    String name() default "";

    // is id column ?
    boolean id() default false;

    // query like ?
    boolean queryLike() default false;

}
