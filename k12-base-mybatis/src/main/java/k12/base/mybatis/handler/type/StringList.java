package k12.base.mybatis.handler.type;

import java.util.ArrayList;
import java.util.Collection;

/**
 * String List
 */
public class StringList extends ArrayList<String> {

    public StringList(int initialCapacity) {
        super(initialCapacity);
    }

    public StringList() {
        super();
    }

    public StringList(Collection<? extends String> c) {
        super(c);
    }
}
