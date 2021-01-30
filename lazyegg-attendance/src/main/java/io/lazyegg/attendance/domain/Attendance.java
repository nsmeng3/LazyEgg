package io.lazyegg.attendance.domain;


import io.lazyegg.attendance.domain.att.rule.AttendanceRule;
import io.lazyegg.attendance.domain.att.rule.RuleFactoryBean;
import io.lazyegg.attendance.domain.att.status.AttStatus;

import java.util.List;
import java.util.Map;

/**
 * 考勤
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/18 10:50 下午
 */

public class Attendance {

    /**
     * 考勤对象
     */
    private AttObj attObj;
    /**
     * 考勤时间
     */
    private AttDate attDate;

    /**
     * 定位
     */
    private AttLocation location;

    /**
     * 考勤状态
     */
    private AttStatus attStatus;

    /**
     * 工作时间
     */
    private List<AttendanceRule> attendanceRule;


    /**
     * 考勤
     *
     * @param attObj 考勤对象
     * @param attDate 考勤时间
     * @param location 考勤地点
     */
    public Attendance(AttObj attObj, AttDate attDate, AttLocation location) {
        this.attObj = attObj;
        this.attDate = attDate;
        this.location = location;
    }

    /**
     * 判断考勤状态
     */
    public void judgeAttStatus() {
        Map<Class<? extends AttendanceRule>, AttendanceRule> attendanceRuleMap = RuleFactoryBean.getAttendanceRuleMap();
        for (AttendanceRule rule : attendanceRuleMap.values()) {
            attStatus =  rule.getAttStatus(attDate);
        }

    }

    public AttObj getAttObj() {
        return attObj;
    }

    public AttDate getAttDate() {
        return attDate;
    }

    public AttLocation getLocation() {
        return location;
    }

    public AttStatus getAttStatus() {
        return attStatus;
    }

    public List<AttendanceRule> getAttendanceRule() {
        return attendanceRule;
    }
}
