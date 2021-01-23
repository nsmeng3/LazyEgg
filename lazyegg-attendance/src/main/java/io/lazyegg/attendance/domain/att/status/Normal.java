package io.lazyegg.attendance.domain.att.status;

/**
 * 考勤状态正常
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/18 11:40 下午
 */

public class Normal extends AttStatus {


    @Override
    public String statusName() {
        return "正常";
    }

    @Override
    public String statusCode() {
        return "0";
    }


}
