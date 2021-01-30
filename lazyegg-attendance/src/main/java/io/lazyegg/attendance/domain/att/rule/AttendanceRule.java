package io.lazyegg.attendance.domain.att.rule;

import io.lazyegg.attendance.domain.AttDate;
import io.lazyegg.attendance.domain.att.status.AttStatus;

/**
 * 工作时间规则
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/18 11:56 下午
 */
public abstract class AttendanceRule implements AM, PM {

    protected FormatTime am;

    protected FormatTime pm;

    public AttendanceRule(FormatTime am, FormatTime pm) {
        if (am.晚于(pm)) {
            throw new RuntimeException("工作时间段设置非法");
        }
        this.am = am;
        this.pm = pm;
    }


    /**
     * 获取考勤状态
     *
     * @param attDate 考勤时间
     * @return
     */
    public abstract AttStatus getAttStatus(AttDate attDate);


    @Override
    public FormatTime getAm() {
        return am;
    }

    @Override
    public FormatTime getPm() {
        return pm;
    }




}
