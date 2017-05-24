package com.biz.gbck.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.DateUtils;
import org.codelogger.utils.exceptions.DateException;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public final class DateTool {

    public static final long SECOND = 1000;
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;
    public static final long TWOHOUR = HOUR * 2;
    public static final long DAY = 24 * HOUR;
    public static final long DAY3 = DAY * 3;
    public static final long DAY7 = DAY * 7;
    public static final long DAY15 = DAY * 15;
    public static final long DAY30 = DAY * 30;

    public static final SimpleDateFormat SQL92_STANDARD_DATETIME_FORMAT =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    public static final SimpleDateFormat SQL92_STANDARD_DATE_FORMAT =
        new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat SQL92_STANDARD_YEAR_MONTH =
        new SimpleDateFormat("yyyy-MM");

    private DateTool() {
    }

    public static String durationFormat(long duration) {
        if (duration >= DAY)
            return duration / DAY + "天";
        else if (duration >= HOUR)
            return duration / HOUR + "小时";
        else if (duration >= MINUTE)
            return duration / MINUTE + "分钟";
        else
            return duration / SECOND + "秒";
    }

    public static java.sql.Date today() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    public static Timestamp nowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static int betweenDays(Date from, Date to) {
        long duration = to.getTime() - from.getTime();
        return (int) ((duration / DAY) + (duration % DAY == 0 ? 0 : 1));
    }

    public static java.sql.Date weekAgo() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -7);
        return new java.sql.Date(c.getTimeInMillis());
    }

    //多少天以前
    public static long beforeDays(int days) {
        days = days < 0 ? days : -days;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, days);
        return c.getTimeInMillis();
    }

    public static long afterDays(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, days);
        return c.getTimeInMillis();
    }

    /**
     * 按中国习惯获取周  周一 返回1； 周日 返回 7
     *
     * @param timeMillis
     * @return
     */
    public static int getWeek1to7(long timeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMillis);
        int week = c.get(Calendar.DAY_OF_WEEK);
        if (week == 1)
            return 7;
        else
            return week - 1;
    }

    /**
     * 取周名字
     *
     * @param week
     * @return
     */
    public static String getWeekName(int week) {
        switch (week) {
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            case 7:
                return "周日";
        }
        return "";
    }

    /**
     * 计算时间的分钟数
     *
     * @param timeMillis
     * @return
     */
    public static int getTimeInMinute(long timeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMillis);
        return c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
    }

    /**
     * 判断两个时间是否是同一天
     * @param dateA
     * @param dateB
     * @return
     * @author defei
     */
   /* public static boolean isSameDay(Date dateA,Date dateB) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);

        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);

        return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
                && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
                &&  calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
    }
    */

    /**
     * 获取时间戳年
     *
     * @param ts
     * @param calendarType Calendar.YEAR | Calendar.MONTH | Calendar.DAY_OF_MONTH
     * @return
     * @author defei
     * @date 下午5:46:28  2014年12月15日
     */
    public static int getTime(long ts, int calendarType) {
        Calendar calDate = Calendar.getInstance();
        calDate.setTime(new Date(ts));
        return calDate.get(calendarType);
    }


    public static String nowStr() {
        return (new Timestamp(System.currentTimeMillis()).toString()).substring(0, 19);
    }

    public static String formatTo24Hour(int minute) {
        if (minute <= 0) {
            return "00:00";
        }
        if (minute >= 24 * 60) {
            return "24:00";
        }
        return prefixDecade(minute / 60) + ":" + prefixDecade(minute % 60);
    }

    private static String prefixDecade(int num) {
        return num >= 10 ? "" + num : "0" + num;
    }

    /**
     * 获取alipay 的 it_b_pay<br>
     * 当前时间到limitTime的分钟数,单位(m)
     *
     * @param limitTime
     * @return
     * @author defei
     * @date 2015年7月6日
     */
    public static String getMinuteItBPay(Long limitTime) {
        Calendar calendar = Calendar.getInstance();
        Long nowTime = calendar.getTimeInMillis();
        Long betweenMinute = (limitTime - nowTime) / 1000 / 60;
        return betweenMinute + "m";
    }

    /**
     * 获取当前日期,返回数据格式:yyyyMMdd
     *
     * @return
     * @author defei
     * @date 2015年7月10日
     */
    public static String getNowDate() {
        return DateUtils.getDateFormat(new Date(), "yyyyMMdd");
    }

    /**
     * 获取当前日期
     * @author lei
     * @date 2015年9月29日
     */
    public static String getNowFmtDatetime(String fmt) {
        return DateUtils.getDateFormat(new Date(), fmt);
    }

    /**
     * 获取时间戳
     *
     * @param fmtDateStr 格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws DateException
     * @author defei
     * @date 2015年7月11日
     */
    public static Long getTime(String fmtDateStr) {
        return DateUtils.getDateFromString(fmtDateStr).getTime();
    }

    /**
     * @param fmtDateStr 格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws DateException
     * @author defei
     * @date 2015年7月11日
     */
    public static Timestamp newTimestamp(String fmtDateStr) {
        return new Timestamp(getTime(fmtDateStr));
    }


    /**
     * 格式化时间字符串为Timestamp
     *
     * @param dateStr 时间字符串
     * @return
     */
    public static Timestamp getTimestamp(String dateStr) {
        if (StringUtils.isNotBlank(dateStr) && dateStr.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
            return getTimestamp(dateStr, "yyyy-MM-dd");
        }
        return getTimestamp(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static Timestamp getTimestamp(String dateStr, String dateFmt) {
        Timestamp timestamp = null;
        SimpleDateFormat f = new SimpleDateFormat(dateFmt);
        try {
            timestamp = new Timestamp(f.parse(dateStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 判断当前时间是否在今天之内，如果是返回：true,否则返回false
     *
     * @param currentTime(Long)
     * @return
     * @author defei
     */
    public static boolean isToday(Long currentTime) {
        return StringUtils.equals(new java.sql.Date(System.currentTimeMillis()).toString(),
            new java.sql.Date(currentTime).toString());
    }

    /**
     * 判断当前时间是否在一个月内，如果是返回：true,否则返回false
     *
     * @param currentTime(Long)
     * @return
     * @author defei
     */
    public static boolean isMonth(Long currentTime) {
        Calendar min = new GregorianCalendar();
        min.add(Calendar.DATE, -30);
        return currentTime >= min.getTimeInMillis();
    }

    public static long getTodayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    public static Long getTodayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }


    public static Long getOneHourAgoTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        return calendar.getTime().getTime();
    }

    public static String longToDateStr(long time, String fmtDateStr) {
        SimpleDateFormat s = new SimpleDateFormat(fmtDateStr);
        String dateStr = s.format(new Date(time));
        return dateStr;
    }

    /**
     * 获取格式化后的时间字符串
     *
     * @param time
     * @return
     * @author defei
     * @date 2015年12月1日
     */
    public static String formatChars(long time) {
        return longToDateStr(time, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 距离futureTime 的秒数
     *
     * @param futureTime
     * @return
     * @author defei
     * @date 2015年11月27日
     */
    public static int getAfterSeconds(Long futureTime) {
        if (futureTime == null) {
            return 0;
        }
        int durationSeconds = (int) ((futureTime - System.currentTimeMillis()) / 1000);

        return durationSeconds >= 0 ? durationSeconds : 0;
    }

    /**
     * 获取00:00 到 hourOfDay的分钟数
     *
     * @param hourOfDay
     * @return
     * @author defei
     * @date 2015年12月1日
     */
    public static int hourToMinutes(String hourOfDay) {
        if (StringUtils.isBlank(hourOfDay)) {
            return 0;
        }
        int hour = Integer.valueOf(StringUtils.substringBefore(hourOfDay, ":"));
        int minute = Integer.valueOf(StringUtils.substringAfter(hourOfDay, ":"));
        return hour * 60 + minute;
    }

    /**
     * 获取基于给定日期的起始时间字符串和终止时间字符串
     * 传入: Date day = new Date(); // 时间为2016年5月1日
     * 返回: { "DayStartTime" : "2016-05-01 00:00:00.0", "DayEndTime" : "2016-05-01 23:59:59.999" }
     *
     * @param day
     * @return JSONObject
     */
    public static JSONObject buildDayStartTimeAndEndTime(Date day) {
        JSONObject json = new JSONObject();
        json.put("DayStartTime", SQL92_STANDARD_DATETIME_FORMAT
            .format(org.apache.commons.lang3.time.DateUtils.truncate(day, Calendar.DATE)));
        json.put("DayEndTime", SQL92_STANDARD_DATETIME_FORMAT.format(org.apache.commons.lang3.time.DateUtils
            .addMilliseconds(org.apache.commons.lang3.time.DateUtils.ceiling(day, Calendar.DATE),
                -1)));
        return json;
    }

    /**
     * 获取基于给定年月时间字符串的年月其实日期和终止日期
     * 传入: String monthDateString = "2016-05"
     * 返回: { "MonthStartDay" : "2016-05-01", "MonthEndDay" : "2016-05-31" }
     * @param monthDateString
     * @return
     */
    public static JSONObject buildMonthStartDayAndEndDay(String monthDateString) throws ParseException {
        JSONObject json = new JSONObject();
        Calendar calendar = Calendar.getInstance();
        Date day = SQL92_STANDARD_DATE_FORMAT.parse(monthDateString);
        calendar.setTime(day);
        calendar.set(Calendar.DATE, 1);
        String monthStartDay = SQL92_STANDARD_DATE_FORMAT.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String monthEndDay = SQL92_STANDARD_DATE_FORMAT.format(calendar.getTime());
        json.put("CurrentMonthStartDay", monthStartDay);
        json.put("CurrentMonthEndDay", monthEndDay);
        return json;
    }

    /**
     * 获取基于当前年月时间字符串的月名称
     * 传入: String monthDateString = "2016-05";
     * 返回: "五月"
     * @param monthDateString
     * @return
     * @throws ParseException
     */
    public static String getMonthNameByYearMonth(String monthDateString) throws ParseException {
        Date day = SQL92_STANDARD_YEAR_MONTH.parse(monthDateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.CHINA);
        return dfs.getMonths()[calendar.get(Calendar.MONTH)];
    }

    /**
     * 获取基于传入年月时间字符串的上月起始日和上月结束日
     * 传入: String monthDateString = "2016-05";
     * 返回: { "PreviousMonthStartDay" : "2016-04-01", "PreviousMonthEndDay" : "2016-04-30" }
     * @param monthDayString
     * @return
     */
    public static JSONObject buildPreviousMonthStartDayAndEndDay(String monthDayString)
        throws ParseException {
        JSONObject json = new JSONObject();
        Calendar calendar = Calendar.getInstance();
        Date day = SQL92_STANDARD_DATE_FORMAT.parse(monthDayString);
        calendar.setTime(day);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, 1);
        String monthStartDay = SQL92_STANDARD_DATE_FORMAT.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String monthEndDay = SQL92_STANDARD_DATE_FORMAT.format(calendar.getTime());
        System.out.println(monthStartDay);
        System.out.println(monthEndDay);
        json.put("PreviousMonthStartDay", monthStartDay);
        json.put("PreviousMonthEndDay", monthEndDay);
        return json;
    }

    public static Timestamp buildMonthEndDayTimestamp(String monthDayString) throws ParseException {
        Date day = SQL92_STANDARD_DATE_FORMAT.parse(monthDayString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 给指定时间Long 添加 Day 天 ftm 格式 返回 日期
     *
     * @param time
     * @param day
     * @param fmt
     * @return
     * @throws ParseException
     */
    public static String buildAddDayLongTimestamp(Long time, Integer day, String fmt) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return longToDateStr(calendar.getTime().getTime(), fmt);
    }

    public static Timestamp afterDays(Date date,Integer days){
    	 Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         calendar.add(Calendar.DAY_OF_MONTH, days);
         return new java.sql.Timestamp(calendar.getTimeInMillis());
    }

}
