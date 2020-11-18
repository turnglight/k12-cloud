package k12.base.mybatis.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * supports to exec SQL between: [start, end]
 */
@Data
@AllArgsConstructor
public class BetweenPair<T extends Comparable> {

    private T start;

    private T end;

}
