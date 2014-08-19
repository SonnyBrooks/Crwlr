package Utilites;

import java.io.PrintWriter;
import java.util.Calendar;

/**
 * Created by Andy on 8/18/2014.
 */
public class Print {

    //Console Prints
    public static void println(String sMsg){
        System.out.println(sMsg);
    }

    public static void println(int iMsg){
        System.out.println(iMsg);
    }

    public static void println(double dMsg){
        System.out.println(dMsg);
    }

    public static void println(long lMsg){
        System.out.println(lMsg);
    }

    public static void println(float fMsg){
        System.out.println(fMsg);
    }

    //Print Writer prints
    public static void writeln(String sMsg, PrintWriter pwWriter){
        pwWriter.write(sMsg);
        pwWriter.flush();
    }

    public static void writeln(int iMsg, PrintWriter pwWriter){
        pwWriter.write(iMsg);
        pwWriter.flush();
    }

    public static void writelnWithDate(String sMsg, PrintWriter pwWriter){
        pwWriter.write(Calendar.DATE + " " + Calendar.getInstance().getTime() + ": " + sMsg);
        pwWriter.flush();
    }
}
