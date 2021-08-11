package com.example.server.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author
 * @Description 当前时间转换成utc时间
 * @date 2020/9/29 11:48
 */
public class UtcTimeConvertUtils {
    public static String convert(Date date, Integer delay) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
            int dstOffset = calendar.get(Calendar.DST_OFFSET);
            calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
            long timeInMillis = calendar.getTimeInMillis() - delay * 60 * 1000;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            return df.format(timeInMillis);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 日期转String
     *
     * @param date
     * @param formatter
     * @return
     */
    public static String getLocalDateStr(Date date, String formatter) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return dateTimeFormatter.format(localDateTime);
    }

}
