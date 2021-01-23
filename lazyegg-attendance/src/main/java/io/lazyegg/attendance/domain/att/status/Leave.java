package io.lazyegg.attendance.domain.att.status;

/**
 * 请假
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/18 11:43 下午
 */
public class Leave extends AttStatus{
    @Override
    public String statusName() {
        return "请假";
    }

    @Override
    public String statusCode() {
        return "2";
    }
}
