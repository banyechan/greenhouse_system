package com.douzi.greenhouse_system.utils;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *  日期工具类
 */
public class DateUtil {

    /**
     * 获取当天的开始时间
     */
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天的结束时间
     *
     * @return
     */
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }


    /**
     * 获取前n个小时的时间
     * @param date
     * @param hour
     */
    public static Date getBeforHour(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - hour);
        return c.getTime();
    }


    public static Date cstStringToDate(String cst) throws ParseException {
        String dateStr = "Wed Sep 16 11:26:23 CST 2009";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

        //java.util.Date对象
        Date date = (Date) sdf.parse(dateStr);

        //2009-09-16
        String formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(formatStr);

        //2009-09-16 11:26:23
        String formatStr2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(formatStr2);

        return  date;
    }

    private static void formatDataTest() {
        /*
         * 日期转期望格式的字符串
         */
        //HH 和 hh 的差别：前者是24小时制，后者是12小时制。
        StringBuilder sb = new StringBuilder();
        sb.append("yyyy年MM月dd日 HH:mm:ss")
                .append(" 上下午标志 a")
                .append(" E")
                .append(" 一年中的第D天")
                .append(" 一月中的第F个星期")
                .append(" 一年中的第w个星期")
                .append(" 一月中的第W个星期")
                .append(" Z")
                .append(" z");
        SimpleDateFormat sdf = new SimpleDateFormat(sb.toString());
        String dateString = sdf.format(new Date());
        System.out.println(dateString);
        /*
         * 字符串转日期
         */
        Date date;
        try {
            date = sdf.parse(dateString);
            System.out.println(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

        SimpleDateFormat sdfF = new SimpleDateFormat("F");
        System.out.println(sdfF.format(new Date()));

        SimpleDateFormat sdW = new SimpleDateFormat("w");
        System.out.println(sdW.format(new Date()));

        SimpleDateFormat sdE = new SimpleDateFormat("E");
        System.out.println(sdE.format(new Date()));

        SimpleDateFormat zzz = new SimpleDateFormat("zzz");
        System.out.println(zzz.format(new Date()));

        SimpleDateFormat a = new SimpleDateFormat("a");
        System.out.println(a.format(new Date()));

        formatDataTest();





//        Date date = user.getTime();         //获得你要处理的时间 Date型
//        String strDate= sdf.format(date ); //格式化成yyyy-MM-dd格式的时间字符串
//        Date newDate =sdf.parse(strDate);
//        java.sql.Date resultDate = new java.sql.Date(newDate.getTime());//最后转换成 java.sql.Date类型数据就可以了 注意：最开始时间数据类型是 java.util.Date类型
    }

}
