package k12.base.data.model;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Data
public class Paging implements Modelling {

    Integer page = 0;
    Integer size = 20;
    Integer start = 0;
    String sort = "";
    public Integer getStart() {
        start = page * size;
        return start;
    }

    public void pageable(Pageable pageable) {
        this.setPage(pageable.getPageNumber());
        this.setSize(pageable.getPageSize());

        Sort sort = pageable.getSort();
        if (sort == null || !sort.iterator().hasNext()) return;

        /**
         * toString for example: create_time: DESC, count: ASC
         * allow characters: a-z, A-Z, 0-9, '-' , ',' , and white space.
         * other characters will be remove in which defense sql injection attack.
         */
        this.setSort("ORDER BY " + sort.toString().replaceAll("[^\\w-, ]", ""));
    }

}
