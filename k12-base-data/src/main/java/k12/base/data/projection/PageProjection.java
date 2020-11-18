package k12.base.data.projection;

import java.util.List;

/**
 * Page Projection
 */
public interface PageProjection<T> {

    List<T> getContent();

    Boolean getFirst();

    Boolean getLast();

    Integer getNumber();

    Integer getNumberOfElements();

    Integer getSize();

    Long getTotalElements();

    Integer getTotalPages();

}
