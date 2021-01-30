package io.lazyegg.attendance.domain.att.status;


/**
 * 旷工
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
