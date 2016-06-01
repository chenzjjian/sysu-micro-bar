package com.hyj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class DateTimeUtil {
    private static final long MINUTE = 60 * 1000L;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 将日期时间转换成文字
     */
    public static String getDateTimeString(Date date) {
        if (date == null)
            throw  new NullPointerException();
        Date currentDate = new Date();
        long diff = Math.abs(date.getTime() - currentDate.getTime());
        long hours = diff / HOUR;
        if (hours < 1) {
            long minutes = diff / MINUTE;
            if (minutes <= 0) {
                return "刚刚";
            }
            return minutes + "分钟前";
        }
        if (hours < 24) {
            return diff / HOUR + "小时前";
        }
        if (hours <= 72) {
            int days = Integer.valueOf(diff / DAY + "");
            if (diff % DAY > 0)
                ++days;
            return days + "天前";
        }
        return simpleDateFormat.format(date);
    }
    public static boolean compareDateTime(Date date1, Date date2) {
        return date1.getTime() > date2.getTime();
    }
}
