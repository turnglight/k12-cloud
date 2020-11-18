package k12.base.mybatis.annotation;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface QueryLt {
    String column();
}
