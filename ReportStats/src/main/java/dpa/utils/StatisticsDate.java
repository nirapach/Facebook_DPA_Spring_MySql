package dpa.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * Created by niranjan on 6/24/15.
 */
public class StatisticsDate {

    public static Date getYesterday() {
        GregorianCalendar yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DATE, -1);
        return yesterday.getTime();
    }
}
