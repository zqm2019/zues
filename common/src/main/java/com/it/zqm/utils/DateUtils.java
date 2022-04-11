package com.it.zqm.utils;

import com.it.zqm.constants.Constants;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * @Describe: 日期工具类
 * @Author：zhaqianming
 * @Date: 2021/8/6
 */
public final class DateUtils {
    private DateUtils() {
    }

    public static String getDayFormat(Date date) {
        return getDateFormat(date, Constants.DEFAULT_DAY_FORMAT_PATTERN);
    }

    public static String getDateFormat(Date date) {
        return getDateFormat(date, Constants.DEFAULT_DATE_FORMAT_PATTERN);
    }


    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, Constants.DEFAULT_DATE_FORMAT_PATTERN);
    }


    public static String getDateFormat(Date date, String format) {
        return new DateTime(date).toString(format);
    }


    public static Date parseDate(String dateStr, String format) {
        return DateTimeFormat.forPattern(format).parseDateTime(dateStr).toDate();
    }


}
