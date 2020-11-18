package k12.base.mybatis.annotation;

import java.lang.annotation.*;

/**
 * Created by kinginblue on 2017/2/7.
 * <br>
 * behaviors like JPA @UpdateTimestamp
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateTimestamp {
}
