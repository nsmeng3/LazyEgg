package io.lazyegg.attendance.domain.att.rule;


import io.lazyegg.attendance.domain.AttDate;
import io.lazyegg.attendance.domain.att.status.AttStatus;

import java.util.Date;

/**
 * 下班时间
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/19 12:10 上午
 */

public class GoOffWorkRule extends AttendanceRule {
    public GoOffWorkRule(FormatTime am, FormatTime pm) {
        super(am, pm);
    }

    @Override
    public AttStatus getAttStatus(AttDate attDate) {

        return null;
    }

    public boolean amOffWork(AttDate attDate) {
        for (Date date : attDate.getDates()) {
            FormatTime time = new FormatTime(date);

        }
        return false;
    }
}
