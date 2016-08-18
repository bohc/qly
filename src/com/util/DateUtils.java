package com.util;

/**
 * <p>Description:时间适用类 </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 7csky.cn</p>
 * @author jamin
 * @version 1.0
 */

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtils {
    /**
     * 
     */
    public DateUtils() {
    }

    /**
     * 指定格式返回当前时间
     * @param fmt
     * @return
     */
    public static String getNow(String fmt){
    	Calendar cal  = Calendar.getInstance();
    	return new SimpleDateFormat(fmt).format(cal.getTime());
    }
    
    public static String getNextMonthDate(String fmt,Integer m){
    	Calendar cal  = Calendar.getInstance();
    	cal.add(Calendar.MONTH, m);
    	return new SimpleDateFormat(fmt).format(cal.getTime());
    }
    
    /**
     * getYear : 得到给定时间所在年份
     * @return getYear
     */
    public static int getYear() throws Exception {
        return getYear(new Date());
    }

    /**
     * getYear : 得到给定时间所在年份
     * @param dateStr String
     * @return int
     */
    public static int getYear(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getYear(date);
    }

    /**
     * getYear : 得到给定时间所在年份
     * @param date Date
     * @return int
     */
    public static int getYear(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.YEAR);
    }

    /**
     * getSeason : 得到当前时间所在季度数
     * @return int
     */
    public static int getSeason() throws Exception {
        return getSeason(new Date());
    }

    /**
     * getSeason : 得到给定时间所在季度数
     * @param dateStr String
     * @return int
     */
    public static int getSeason(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getSeason(date);
    }

    /**
     * getSeason : 得到给定时间所在季度数
     * @param date Date
     * @return int
     */
    public static int getSeason(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int month = calendar.get(Calendar.MONTH);
        return month / 3 + 1;
    }

    /**
     * getMonth : 得到当前时间所在月数
     * @return int
     */
    public static int getMonth() throws Exception {
        return getMonth(new Date());
    }

    /**
     * getMonth : 得到给定时间所在月数
     * @param dateStr String
     * @return int
     */
    public static int getMonth(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getMonth(date);
    }

    /**
     * getMonth : 得到给定时间所在月数
     * @param date Date
     * @return int
     */
    public static int getMonth(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * getWeek : 得到当前时间所在处于本月的第几周
     * @param date Date
     * @return int
     */
    public static int getWeek() throws Exception {
        return getWeek(new Date());
    }

    /**
     * getWeek : 得到给定时间所在处于本月的第几周
     * @param dateStr String
     * @return int
     */
    public static int getWeek(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getWeek(date);
    }

    /**
     * getWeek : 得到给定时间所在处于本月的第几周
     * @param date Date
     * @return int
     */
    public static int getWeek(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * getWeek : 得到当前时间所在处于本月的第几天
     * @param date Date
     * @return int
     */
    public static int getDate() throws Exception {
        return getDate(new Date());
    }

    /**
     * getWeek : 得到给定时间所在处于本月的第几天
     * @param dateStr String
     * @return int
     */
    public static int getDate(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getDate(date);
    }

    /**
     * getWeek : 得到给定时间所在处于本月的第几天
     * @param date Date
     * @return int
     */
    public static int getDate(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.DATE);
    }

    /**
     * 当前小时
     * @return
     * @throws Exception
     */
    public static int getHours() throws Exception {
        return getHours(new Date());
    }

    /**
     * 当前小时
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static int getHours(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getHours(date);
    }

    /**
     * 当前小时
     * @param date
     * @return
     * @throws Exception
     */
    public static int getHours(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 当前分钟
     * @return
     * @throws Exception
     */
    public static int getMinutes() throws Exception {
        return getMinutes(new Date());
    }

    /**
     * 当前分钟
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static int getMinutes(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getMinutes(date);
    }

    /**
     * 当前分钟
     * @param date
     * @return
     * @throws Exception
     */
    public static int getMinutes(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.MINUTE);
    }

    public static int getSeconds() throws Exception {
        return getSeconds(new Date());
    }

    public static int getSeconds(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getSeconds(date);
    }

    public static int getSeconds(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 季度的天
     * @return
     * @throws Exception
     */
    public static String getFirstDayOfSeason() throws Exception {
        return getFirstDayOfSeason(new Date());
    }

    /**
     * 季度的天
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static String getFirstDayOfSeason(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getFirstDayOfSeason(date);
    }

    /**
     * 季度的天
     * @param date
     * @return
     * @throws Exception
     */
    public static String getFirstDayOfSeason(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);

        }
        int season = getSeason(calendar.getTime());
        String month = (season * 3 - 2) > 9 ? "" + (season * 3 - 2) :
            "0" + (season * 3 - 2);
        return (calendar.get(Calendar.YEAR)) + "-" + month + "-01";
    }

    public static String getLastDayOfSeason() throws Exception {
        return getLastDayOfSeason(new Date());
    }

    public static String getLastDayOfSeason(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getLastDayOfSeason(date);
    }

    public static String getLastDayOfSeason(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);

        }
        int season = getSeason(calendar.getTime());
        String month = season * 3 > 9 ? "" + season * 3 : "0" + season * 3;
        String day = "31";

        switch (season) {
            case 1:
                day = "31";
                break;
            case 2:
                day = "30";
                break;
            case 3:
                day = "30";
                break;
            case 4:
                day = "31";
                break;
        }
        return (calendar.get(Calendar.YEAR)) + "-" + month + "-" + day;
    }

    public static String getLastDayOfMonth() throws Exception {
        return getLastDayOfMonth(new Date());
    }

    public static String getLastDayOfMonth(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getLastDayOfMonth(date);
    }

    public static String getLastDayOfMonth(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);

        }
        String month = (calendar.get(Calendar.MONTH) + 1) > 9 ?
            "" + (calendar.get(Calendar.MONTH) + 1) :
            "0" + (calendar.get(Calendar.MONTH) + 1);

        int day = 31;

        switch (calendar.get(Calendar.MONTH) + 1) {
            case 2:
                day = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
        }
        if (isLeapYear(calendar.get(Calendar.YEAR)) &&
            (calendar.get(Calendar.MONTH) + 1) == 2) {
            day = 29;
        }
        return calendar.get(Calendar.YEAR) + "-" + month + "-" + day;
    }

    public static String getFirstDayOfWeek() throws Exception {
        return getFirstDayOfWeek(new Date());
    }

    public static String getFirstDayOfWeek(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getFirstDayOfWeek(date);
    }

    public static String getFirstDayOfWeek(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);

        }
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day + 2);
        return (new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime());
    }

    public static String getFirstDayOfMonth() throws Exception {
        return getFirstDayOfMonth(new Date());
    }

    public static String getFirstDayOfMonth(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getFirstDayOfMonth(date);
    }

    public static String getFirstDayOfMonth(Date date) throws Exception {
        if (date == null) {
            date = new Date();
        }
        return (new SimpleDateFormat("yyyy-MM")).format(date) + "-01";
    }

    public static String getLastDayOfYear() throws Exception {
        return getLastDayOfYear(new Date());
    }

    public static String getLastDayOfYear(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getLastDayOfYear(date);
    }

    public static String getLastDayOfYear(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return (calendar.get(Calendar.YEAR)) + "-12-31";
    }

    public static String getFirstDayOfYear() throws Exception {
        return getFirstDayOfYear(new Date());
    }

    public static String getFirstDayOfYear(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getFirstDayOfYear(date);
    }

    /**
     * getFirstDayOfYear : 得到当前时间所在年的最后一天（格式为：yyyy-MM-dd）
     * @return String
     */
    public static String getFirstDayOfYear(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return (calendar.get(Calendar.YEAR)) + "-01-01";
    }

    /**
     * getLastDayOfWeek : 得到当前时间所在周的最后一天（格式为：yyyy-MM-dd）
     * @return String
     */
    public static String getLastDayOfWeek() throws Exception {
        return getLastDayOfWeek(new Date());
    }

    /**
     * getLastDayOfWeek : 得到给定时间所在周的最后一天（格式为：yyyy-MM-dd）
     * @param dateStr String
     * @return String
     */
    public static String getLastDayOfWeek(String dateStr) throws Exception {
        Date date = strToDate(dateStr);
        return getLastDayOfWeek(date);
    }

    /**
     * getLastDayOfWeek : 得到给定时间所在周的最后一天（格式为：yyyy-MM-dd）
     * @param date Date
     * @return String
     */
    public static String getLastDayOfWeek(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);

        }
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day + 8);
        return (new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime());
    }

    /**
     * strToDate : 字符串转换为时间
     * @param dateStr String
     * @return Date
     */
    public static Date strToDate(String dateStr) throws Exception {
        if (dateStr == null) {
            return new Date();
        }
        else {
            String fullDateStr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).
                format(new Date());
            try {
                dateStr = dateStr +
                    fullDateStr.substring(dateStr.length(), fullDateStr.length());
                return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(
                    dateStr);
            }
            catch (Exception e) {
                throw new Exception(
                    "时间格式不正确，请确认时间格式为：yyyy-MM[-dd[ HH[:mm[:ss]]]]");
            }
        }
    }

    /**
     * strToDate : 字符串转换为时间
     * @param dateStr String
     * @param formatStr String
     * @return Date
     */
    public static Date strToDate(String dateStr, String formatStr) throws
        Exception {
        if (dateStr == null) {
            return new Date();
        }
        else {
            try {
                return (new SimpleDateFormat(formatStr)).parse(dateStr);
            }
            catch (Exception e) {
                throw new Exception("时间格式不正确，请确认时间格式");
            }
        }
    }

    /**
     * isLeapYear : 判断当前年是否闰年
     * @param year int
     * @return boolean
     */
    public static boolean isLeapYear(int year) {
        if ( (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * isLeapYear : 判断当前年是否闰年
     * @param date Date
     * @return boolean
     */
    public static boolean isLeapYear(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return isLeapYear(calendar.get(Calendar.YEAR));
    }
    
    /**
     * 格式化字符串
     */
    
    public static String formatDateToString(Date date,String format){
    	if(date==null)return "";
    	SimpleDateFormat sdf=new SimpleDateFormat(format);
    	return sdf.format(date);
    }
}
