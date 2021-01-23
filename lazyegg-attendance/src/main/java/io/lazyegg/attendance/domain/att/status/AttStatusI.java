package io.lazyegg.attendance.domain.att.status;

/**
 * 考勤状态能力
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/18 11:40 下午
 */
public interface AttStatusI {

    /**
     * 状态名称
     *
     * @return
     */
    String statusName();

    /**
     * 状态码
     *
     * @return
     */
    String statusCode();

}
