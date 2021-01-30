package io.lazyegg.attendance.domain.att.rule;


import io.lazyegg.attendance.exception.AttException;

import java.util.Calendar;
import java.util.Date;

/**
 * 工作时间 时分秒对象
 *
 * @author DifferentW  nsmeng3@163.com 2021/1/19 12:32 上午
 */

public class FormatTime {

    private Date attDate;

    private Integer hour;

    private Integer minute;

    private Integer second;

    public FormatTime(Date attDate) {
        if (attDate == null) {
            throw new AttException("时间对象不能为空");
        }
        this.attDate = attDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(attDate);                    //放入Date类型数据
        int hour = calendar.get(Calendar.HOUR_OF_DAY);//时（24小时制）
        int minute = calendar.get(Calendar.MINUTE);//分
        int second = calendar.get(Calendar.SECOND);//秒
        new FormatTime(hour, minute, second);
    }

    public FormatTime(int hour) {
        this(hour, 0, 0);
    }

    public FormatTime(int hour, int minute) {
        this(hour, minute, 0);
    }

    public FormatTime(int hour, int minute, int second) {
        if (hour > 24 || minute > 59 || second > 59) {
            throw new RuntimeException("非法时间");
        }
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public boolean 晚于(FormatTime formatTime) {
        if (hour <= formatTime.hour) {
            return false;
        } else if (minute <= formatTime.minute) {
            return false;
        } else {
            return second > formatTime.second;
        }
    }


    public boolean 晚于等于(FormatTime ruleTime) throws AttException {
        if (ruleTime == null) {
            throw new AttException("考勤规则时间不能为空");
        }
        return !this.toDate(ruleTime).before(attDate);
    }

    public boolean 早于等于(FormatTime ruleTime) {
        if (ruleTime == null) {
            throw new AttException("考勤时间不能为空");
        }
        return !this.toDate(ruleTime).after(attDate);
    }

    public boolean 时间晚于(FormatTime ruleTime) {
        if (ruleTime == null) {
            throw new AttException("考勤时间不能为空");
        }
        return this.toDate(ruleTime).after(attDate);
    }

    public boolean 时间早于(FormatTime ruleTime) {
        if (ruleTime == null) {
            throw new AttException("考勤时间不能为空");
        }
        return this.toDate(ruleTime).before(attDate);
    }

    /**
     * 转Date
     *
     * @return
     */
    @Deprecated
    public Date toDate(FormatTime formatTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.attDate);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, formatTime.hour);
        // 分
        calendar.set(Calendar.MINUTE, formatTime.minute);
        // 秒
        calendar.set(Calendar.SECOND, formatTime.second);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }


    public Integer getHour() {
        return hour;
    }

    public FormatTime setHour(Integer hour) {
        this.hour = hour;
        return this;
    }

    public Integer getMinute() {
        return minute;
    }

    public FormatTime setMinute(Integer minute) {
        this.minute = minute;
        return this;
    }

    public Integer getSecond() {
        return second;
    }

    public FormatTime setSecond(Integer second) {
        this.second = second;
        return this;
    }
}
