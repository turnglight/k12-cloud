package k12.base.mybatis.handler.type;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Integer List
 */
public class IntegerList extends ArrayList<Integer> {

    public IntegerList(int initialCapacity) {
        super(initialCapacity);
    }

    public IntegerList() {
        super();
    }

    public IntegerList(Collection<? extends Integer> c) {
        super(c);
    }
}
