package k12.base.mybatis.annotation;

import java.lang.annotation.*;

/**
 * behaviors like Spring Data @Version. note: annotate on a @Column, recommend for type Integer/Long. indicate the column can be versioning when update db, to implement optimistic locking on entities.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Versioning {
}
