package k12.base.mybatis.handler.type;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Long List
 */
public class LongList extends ArrayList<Long> {

    public LongList(int initialCapacity) {
        super(initialCapacity);
    }

    public LongList() {
        super();
    }

    public LongList(Collection<? extends Long> c) {
        super(c);
    }
}
