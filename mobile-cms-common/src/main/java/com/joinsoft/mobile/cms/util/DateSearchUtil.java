package com.joinsoft.mobile.cms.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangxulong on 14-9-11.
 */
public class DateSearchUtil {
    public static Date getBeginMonthDate(Integer year, Integer month) {
        Calendar calendar = getCalendar(year, month);
        return calendar.getTime();
    }

    public static Date getEndMonthDate(Integer year, Integer month) {
        Calendar calendar = getCalendar(year, month);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static Calendar getCalendar(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        if (null != year) {
            calendar.set(Calendar.YEAR, year.intValue());
        }
        if (null != month) {
            calendar.set(Calendar.MONTH, month - 1);
        }
        return calendar;
    }
}
