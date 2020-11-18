package k12.base.mybatis.data;

import java.math.BigDecimal;

/**
 * SQL between: [start, end] on type BigDecimal
 */
public class BigDecimalPair extends BetweenPair<BigDecimal> {
    public BigDecimalPair(BigDecimal start, BigDecimal end) {
        super(start, end);
    }
}
