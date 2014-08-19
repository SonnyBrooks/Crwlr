package Utilites;

import java.util.Calendar;

/**
 * Created by Andy on 8/18/2014.
 */
public class DateTime {

    // Date/Time
    public static String getDateTime(){
        return Calendar.DATE + " " + Calendar.getInstance().getTime();
    }

    public static String getDate(){
        return "" + Calendar.DATE;
    }

    public static String getTime(){
        return "" + Calendar.getInstance().getTime();
    }

    public static String getTimeZone(){
        return "" + Calendar.getInstance().getTimeZone();
    }
}
