package io.lazyegg.attendance.domain.att.rule;

/**
 * 默认规则
 * 加载目前已知的 考勤规则
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/23 3:22 下午
 */
public enum DefaultRule {
    goWork(GoWorkRule.class),
    goOffWork(GoOffWorkRule.class),
    lateRule(LateRule.class);

    private Class<? extends AttendanceRule> ruleClass;

    DefaultRule(Class<? extends AttendanceRule> ruleClass) {
        this.ruleClass = ruleClass;
    }

    public AttendanceRule newInstance() {
        try {
            return newInstanceClass(this.ruleClass);
        } catch (Exception var2) {
            throw new RuntimeException("Unable to instantiate class [" + this.ruleClass.getName() + "]");
        }
    }

    public static <T> T newInstanceClass(Class<T> clazz) {
        if (clazz == null) {
            String msg = "Class method parameter cannot be null.";
            throw new IllegalArgumentException(msg);
        } else {
            try {
                return clazz.newInstance();
            } catch (Exception var2) {
                throw new RuntimeException("Unable to instantiate class [" + clazz.getName() + "]", var2);
            }
        }
    }
}
