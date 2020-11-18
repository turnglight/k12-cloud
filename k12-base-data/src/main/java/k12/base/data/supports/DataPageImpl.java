package k12.base.data.supports;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

/**
 * 扩展 spring-data PageImpl<T> ，提供默认无参构造函数，使其成为一个满足 Spring 框架要求的 POJO
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPageImpl<T> extends PageImpl<T> {

    public DataPageImpl() {
        super(Collections.emptyList());
    }

    List<T> content;

    boolean first;

    boolean last;

    int number;

    int numberOfElements;

    int size;

    Sort sort;

    long totalElements;

    int totalPages;

}
