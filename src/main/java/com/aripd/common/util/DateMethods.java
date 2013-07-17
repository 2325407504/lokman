package com.aripd.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author cem
 */
public class DateMethods {

    /**
     * Ref
     * http://stackoverflow.com/questions/4600034/calculate-number-of-weekdays-between-two-dates-in-java
     *
     * @param start
     * @param end
     * @return
     */
    static public long nofWorkdays(Date start, Date end) {
        Calendar c1 = GregorianCalendar.getInstance();
        c1.setTime(start);
        int w1 = c1.get(Calendar.DAY_OF_WEEK);
        c1.add(Calendar.DAY_OF_WEEK, -w1 + 1);

        Calendar c2 = GregorianCalendar.getInstance();
        c2.setTime(end);
        int w2 = c2.get(Calendar.DAY_OF_WEEK);
        c2.add(Calendar.DAY_OF_WEEK, -w2 + 1);

        //end Saturday to start Saturday 
        long days = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
        long daysWithoutSunday = days - (days * 2 / 7);

        if (w1 == Calendar.SUNDAY) {
            w1 = Calendar.MONDAY;
        }
        if (w2 == Calendar.SUNDAY) {
            w2 = Calendar.MONDAY;
        }
        return daysWithoutSunday - w1 + w2;
    }
}
