package com.ywy.mylibs.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by yangweiyi on 16/10/10.
 */

public class DateUtils {

    /**
     * 设置消息时间显示
     *
     * @param time 单位:秒
     * @return
     */
    public static String formatMsgDate(long time) {

        if (time < 1) {
            return "";
        }
        /*
            换算日期,并生成当前日期与发送日期
         */
        time = time * 1000L;// 换算到毫秒
        Date date = new Date(time);
        Date curDate = new Date();

        /*
            设置format类型
         */
        String allFormat = "yyyy年MM月dd日 HH:mm:ss";
        String yearFormat = "yyyy年";
        String monthFormat = "MM月dd日";
        String minuteFormat = "HH:mm";
        String mdhmFormat = "MM月dd日 HH:mm";
        String ymdFormat = "yyyy年MM月dd日";


        SimpleDateFormat sdf = new SimpleDateFormat(allFormat);
//        String allDate = sdf.format(date);
//        String curAllDate = sdf.format(curDate);
        sdf = new SimpleDateFormat(yearFormat);
        String yearDate = sdf.format(date);
        String curYearDate = sdf.format(curDate);
        sdf = new SimpleDateFormat(monthFormat);
        String mdDate = sdf.format(date);
        String curMdDate = sdf.format(curDate);
        sdf = new SimpleDateFormat(minuteFormat);
        String hmDate = sdf.format(date);
//        String curHmDate = sdf.format(curDate);
        sdf = new SimpleDateFormat(mdhmFormat);
        String mdhmDate = sdf.format(date);
//        String curMdhmDate = sdf.format(curDate);
        sdf = new SimpleDateFormat(ymdFormat);
        String ymd = sdf.format(date);
//        String curYmd = sdf.format(curDate);

        String sendDate = sdf.format(new Date(time));
        if (yearDate.equals(curYearDate)) {
            if (mdDate.equals(curMdDate)) {
                sendDate = hmDate;// 月日相同   返回HH:mm
            } else {
                sendDate = mdhmDate;// 月日不同 返回MM-dd HH:mm
            }
        } else {
            sendDate = ymd;// 年不同  返回yyyy-MM-dd格式
        }


        return sendDate;
    }

    /**
     * 设置消息时间显示
     * 返回HH:mm格式
     *
     * @param time 单位:秒
     * @return
     */
    public static String formatDateHHmm(long time) {

        if (time < 1) {
            return "";
        }
        /*
            换算日期,并生成当前日期与发送日期
         */
        time = time * 1000L;// 换算到毫秒
        Date date = new Date(time);
        Date curDate = new Date();

        /*
            设置format类型
         */
        String minuteFormat = "HH:mm";


        SimpleDateFormat sdf = new SimpleDateFormat(minuteFormat);
        String hmDate = sdf.format(date);

        return hmDate;
    }

    public static String getCurrentYMD(String format) {
        Date date = new Date();
        if (StringUtil.isEmpty(format))
            format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String formatTimeMillis(long ms, String format) {
        Date date = new Date(ms);
        if (StringUtil.isEmpty(format)) {
            format = "M月d日";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @param seconds
     * @return
     */
    public static String seconds2Hour(long seconds) {
        long hour = seconds / 3600;
        long minute = seconds % 3600 / 60;
        long second = seconds % 3600 % 60;
        String strHour = "" + hour;
        String strMin = "" + minute;
        String strSec = "" + second;
        if (hour < 10) {
            strHour = "0" + hour;
        }
        if (minute < 10) {
            strMin = "0" + minute;
        }
        if (second < 10) {
            strSec = "0" + second;
        }
        return strHour + ":" + strMin + ":" + strSec;
    }

    /**
     * 是否是今天
     *
     * @param timeMills
     * @return
     */
    public static boolean isToday(long timeMills) {
        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTimeInMillis(timeMills);

        GregorianCalendar c2 = new GregorianCalendar();
        c2.setTimeInMillis(System.currentTimeMillis());

        int y1 = c1.get(Calendar.YEAR);
        int m1 = c1.get(Calendar.MONTH) + 1;
        int d1 = c1.get(Calendar.DAY_OF_MONTH);

        int y2 = c2.get(Calendar.YEAR);
        int m2 = c2.get(Calendar.MONTH) + 1;
        int d2 = c2.get(Calendar.DAY_OF_MONTH);

        return (y1 == y2) && (m1 == m2) && (d1 == d2);
    }

    public static String getPublishTime(long l) {
        String msg = null;
        if (TimeUtils.isToday(l)) {
            long time = (System.currentTimeMillis() - l) / 1000;
            if (time / 60 == 0) {
                msg = "刚刚";
            } else if (time / 60 > 0 && time / 3600 == 0) {
                msg = time / 60 + "分钟前";
            } else if (time / 3600 > 0 && time / (3600 * 24) == 0) {
                msg = time / 3600 + "小时前";
            }
        } else if (TimeUtils.isYesterday(l)) {
            msg = "昨天";
        } else {
            msg = formatTimeMillis(l, "yyyy-MM-dd");
        }
        return msg;
    }

}
