package dpa.utils;

import com.gravity4.facebook.dpareports.utils.StatisticsDate;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Calendar;
import static org.junit.Assert.assertEquals;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by niranjan on 6/24/15.
 */
@SuppressWarnings("ALL")
public class StatisticsDateTest extends TestCase {


    @Test
    public void testGetYesterday() throws Exception {

        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.add(Calendar.DATE, -1);
            Date today = cal.getTime();
            Date yesterday = StatisticsDate.getYesterday();
            Assert.assertEquals(today, yesterday);
        }
        catch(Exception e){
            /*ignore*/
        }

    }
}