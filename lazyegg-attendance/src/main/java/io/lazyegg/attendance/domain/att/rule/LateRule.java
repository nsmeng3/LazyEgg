package io.lazyegg.attendance.domain.att.rule;

import com.whir.rd.attendance.domain.AttDate;
import com.whir.rd.attendance.domain.att.status.AttStatus;

/**
 * 迟到时间
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/19 12:11 上午
 */
public class LateRule extends AttendanceRule {
    public LateRule(FormatTime am, FormatTime pm) {
        super(am, pm);
    }

    @Override
    public AttStatus getAttStatus(AttDate attDate) {
        return null;
    }
}
