package io.lazyegg.attendance.domain;

import com.whir.rd.attendance.exception.AttException;

import java.util.*;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/18 11:09 下午
 */


public class AttDate {


    /**
     * 考勤时间
     * 每个人一天可以打卡签到多次
     */
    private List<Date> dates = new ArrayList<Date>();

    /**
     * 首次考勤时间
     */
    private Date firstAttTime;

    /**
     * 最后考勤时间
     */
    private Date lastAttTime;

    public AttDate(List<Date> dates) throws AttException {
        this.dates = dates;
        setStartEndTime(dates);
    }

    /**
     * 设置开始结束时间
     *
     * @param dates
     * @return
     * @throws AttException
     */
    private AttDate setStartEndTime(List<Date> dates) throws AttException {
        Collections.sort(this.dates, new Comparator<Date>() {
            @Override
            public int compare(Date date, Date t1) {
                return date.compareTo(t1);
            }
        });
        if (this.dates.isEmpty()) {
           return this;
        }
        int attendanceTimes = this.dates.size();
        firstAttTime = this.dates.get(0);
        if (attendanceTimes > 1) {
            lastAttTime = this.dates.get(attendanceTimes - 1);
        }
        return this;
    }

    /**
     * 第一次打卡时间
     *
     * @return
     */
    public Date getFirstAttTime() {
        return firstAttTime;
    }

    /**
     * 最后一次打开时间
     *
     * @return
     */
    public Date getLastAttTime() {
        return lastAttTime;
    }


    public List<Date> getDates() {
        return dates;
    }
}
