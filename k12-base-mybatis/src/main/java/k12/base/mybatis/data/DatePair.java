package k12.base.mybatis.data;

import java.util.Date;

/**
 * SQL between: [start, end] on type Date
 */
public class DatePair extends BetweenPair<Date> {
    public DatePair(Date start, Date end) {
        super(start, end);
    }
}
