package com.ywy.mylibs.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by yangweiyi on 16/6/29.
 */
public class TimeUtils {

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

    public static boolean isYesterday(long timeMills) {
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

        return (y1 == y2) && (m1 == m2) && (d2 - d1 == 1);
    }
}
