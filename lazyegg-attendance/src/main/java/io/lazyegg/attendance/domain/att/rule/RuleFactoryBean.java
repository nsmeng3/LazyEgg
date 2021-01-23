package io.lazyegg.attendance.domain.att.rule;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始化考勤规则
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/23 3:48 下午
 */
public class RuleFactoryBean<T extends AttendanceRule> {
    /**
     * 考勤规则集合
     */
    private static final Map<Class<? extends AttendanceRule>, AttendanceRule> attendanceRuleMap = new HashMap<Class<? extends AttendanceRule>, AttendanceRule>();

    public RuleFactoryBean<T> addAttendanceRule(T attendanceRule) {
        Class<? extends AttendanceRule> aClass = attendanceRule.getClass();
        attendanceRuleMap.put(aClass, attendanceRule);
        return this;
    }

    public static Map<Class<? extends AttendanceRule>, AttendanceRule> getAttendanceRuleMap() {
        return attendanceRuleMap;
    }
}
