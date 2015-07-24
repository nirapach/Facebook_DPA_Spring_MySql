package com.gravity4.facebook.dpareports.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by niranjan on 6/24/15.
 */
@SuppressWarnings("unchecked")
public class StatisticsDate {

    public static Date getYesterday() {
        GregorianCalendar yesterday = new GregorianCalendar();
        Date date;
        yesterday.add(Calendar.DATE, -1);
        date = yesterday.getTime();
        return date;
    }
}
