package io.lazyegg.attendance.domain.att.rule;

import com.whir.rd.attendance.domain.AttDate;
import com.whir.rd.attendance.domain.att.status.AttStatus;
import com.whir.rd.attendance.domain.att.status.Normal;

import java.util.Date;

/**
 * 上班时间
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/19 12:04 上午
 */
public class GoWorkRule extends AttendanceRule {

    public GoWorkRule(FormatTime am, FormatTime pm) {
        super(am, pm);
    }

    @Override
    public AttStatus getAttStatus(AttDate attDate) {
        if (amGoWork(attDate) && pmGoWork(attDate)) {
            return new Normal();
        }
        return null;
    }

    /**
     * 上午上班
     *
     * @param attDate
     * @return
     */
    public boolean amGoWork(AttDate attDate) {
        return new FormatTime(attDate.getFirstAttTime()).早于等于(am);
    }

    /**
     * 下午上班
     *
     * @param attDate
     * @return
     */
    public boolean pmGoWork(AttDate attDate) {
        for (Date date : attDate.getDates()) {
            FormatTime time = new FormatTime(date);
            if (time.早于等于(am)) {
                continue;
            }
            if (time.早于等于(pm)) {
                return true;
            }
        }

        return false;
    }
}
