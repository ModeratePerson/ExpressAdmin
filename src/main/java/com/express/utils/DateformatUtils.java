package com.express.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateformatUtils {
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    // 将字符串解析为 Date 对象
    public static Date parseDate(String dateString, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateString);
    }
    public static String formatToStandardDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }
    public static String formatToStandardDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
}
