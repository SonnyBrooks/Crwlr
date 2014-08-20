package Utilites;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * Created by Andy on 8/18/2014.
 */
public class Print {

    //Console Prints
    public static void println(Object oMsg){
        System.out.println(oMsg.toString());
    }
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
    public static PrintWriter getPrintWriter(String sFileName)throws IOException{
        return new PrintWriter(sFileName + ("_" + Calendar.DATE + "_" + Calendar.getInstance().getTime()).replace(":", "_").replace(" ", "-"));
    }
    public static void writeln(String sMsg, PrintWriter pwWriter){
        pwWriter.write(sMsg);
        System.out.println(sMsg);
        pwWriter.flush();
    }

    public static void writeln(int iMsg, PrintWriter pwWriter){
        pwWriter.write(iMsg);
        System.out.println(iMsg);
        pwWriter.flush();
    }

    public static void writelnWithTimeStamp(String sMsg, PrintWriter pwWriter){
        pwWriter.write(Calendar.DATE + " " + Calendar.getInstance().getTime() + ": " + sMsg);
        pwWriter.flush();
        System.out.println(Calendar.DATE + " " + Calendar.getInstance().getTime() + ": " + sMsg);
    }

    public static void writeERRWithTimeStamp(String sMsg, Exception ex, PrintWriter pwWriter){
        pwWriter.write("ERROR: " + Calendar.DATE + " " + Calendar.getInstance().getTime() + ": " + sMsg + "\n" + ex);
        System.out.println("ERROR: " + Calendar.DATE + " " + Calendar.getInstance().getTime() + ": " + sMsg + "\n" + ex);
    }
}
