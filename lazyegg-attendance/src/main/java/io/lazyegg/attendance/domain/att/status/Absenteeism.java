package io.lazyegg.attendance.domain.att.status;

import com.whir.rd.attendance.domain.AttDate;
import com.whir.rd.attendance.domain.att.rule.AttendanceRule;

/**
 * 请假
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/18 11:43 下午
 */
public class Absenteeism extends AttStatus{
    @Override
    public String statusName() {
        return "旷工";
    }

    @Override
    public String statusCode() {
        return "1";
    }



}
