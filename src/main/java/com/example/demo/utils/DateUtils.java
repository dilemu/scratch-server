package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author
 * @Description
 * @date 2020/10/26 11:00
 */
public class DateUtils {
    private DateUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 日期字符串的加减计算
     *
     * @param dateStr     日期字符串
     * @param format      日期字符串格式
     * @param computeUnit 运算的单位
     * @param count       运算值
     * @return
     */
    public static String DateStrCompute(String dateStr, String format, Integer computeUnit, Integer count) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dt = sdf.parse(dateStr);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(computeUnit, count);
        return sdf.format(rightNow.getTime());
    }

    /**
     * UTC时间转换成北京时间
     *
     * @param UTCStr
     * @throws ParseException
     */
    public static String UTCToCST(String UTCStr) throws ParseException {
        String formatPattern = "yyyy-MM-dd HH:mm:ss";
        ZonedDateTime zdt = ZonedDateTime.parse(UTCStr);
        LocalDateTime localDateTime = zdt.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        return formatter.format(localDateTime.plusHours(8));
    }

    /**
     * 判断输入的日期格式是否正确
     *
     * @param sDate
     * @return
     */
    public static boolean isLegalDate(String sDate) {
        int legalLen = 10;
        if ((sDate == null) || (sDate.length() != legalLen)) {
            return false;
        }

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(sDate);
            return sDate.equals(formatter.format(date));
        } catch (ParseException e) {
            logger.error("isLegalDate error:{}", e);
        }
        return true;
    }

    /**
     * 判断输入的生效时间是否有效
     */

    public static boolean compareDate(String sDate, String eDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = formatter.parse(sDate);
            Date endDate = formatter.parse(eDate);
            if (beginDate.getTime() > endDate.getTime()) {
                return false;
            }
        } catch (ParseException e) {
            logger.error("compareDate error:{}", e);
        }


        return true;
    }


    /**
     * 获取指定时间格式的时间字符串，包含时间加减
     *
     * @param format
     * @param delay
     * @return
     */
    public static String getDateStr(String format, Long delay) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(System.currentTimeMillis() - delay);
        return sdf.format(date);
    }

}