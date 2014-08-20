package Crwlr;
import Utilites.*;
import CrwlrBaseClass.*;
import com.thoughtworks.selenium.Selenium;
import org.jsoup.nodes.Document;
import org.openqa.selenium.server.SeleniumServer;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Andy on 8/18/2014.
 */
public class CrwlrDriver {
    public static PrintWriter pwStdPrint = null;
    public static final String sFileName = "TEST";
    public static final String sURL = "http://www.imgur.com";
    public static final int iRunType = 0; //0 = Imgur.com
    public static Scraper scraper = null;

    public static void main(String args[]){
        try {
            //Initialize Print Writer for Log
            pwStdPrint = Print.getPrintWriter(sFileName);
            Print.writeln(sFileName + ": LOG STARTED AT " + DateTime.getDateTime(), pwStdPrint);
            Server _server = null;

            //Boot server
            Print.writelnWithTimeStamp("Booting Selenium server...", pwStdPrint);
            _server = new Server();
            _server.startSeleniumServer();
            Print.writelnWithTimeStamp("Selenium server booted!...", pwStdPrint);

            //Fetch URL
            Print.writelnWithTimeStamp("Fetching " + sURL + "...", pwStdPrint);
            Document oWebpage = WebPage.getWebPage(sURL);

            if(oWebpage == null){
                throw new IllegalStateException("oWebPage returned null.");
            }
            Print.writelnWithTimeStamp(sURL + " loaded...", pwStdPrint);
            switch (iRunType) {
                case 0:
                    scraper = new ImgurScrapePics(pwStdPrint, "http://imgur.com/");
                    scraper.ScrapeImages();
                    Print.writelnWithTimeStamp("Number of scraped images: " + scraper.getNumImages(), pwStdPrint);
            }
            Print.writelnWithTimeStamp("Program ended...", pwStdPrint);

        }catch (Exception ex){
            Print.writeERRWithTimeStamp("CrwlrDriver(main) exception", ex, pwStdPrint);
        }
    }
}
