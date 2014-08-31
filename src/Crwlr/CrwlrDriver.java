package Crwlr;
import Utilites.*;
import CrwlrBaseClass.*;
import org.jsoup.nodes.Document;

import java.io.PrintWriter;


/**
 * Created by Andy on 8/18/2014.
 */
public class CrwlrDriver {
    public static PrintWriter pwStdPrint = null;
    public static final String sFileName = "TEST";
    public static String sURL = "";
    public static enum RunTypes{
        Imgur, Reddit
    }
    public static RunTypes pRunType = RunTypes.Reddit;
    public static Scraper scraper = null;

    public static void main(String args[]){
        try {
            //Initialize Print Writer for Log
            pwStdPrint = Print.getPrintWriter(sFileName);
            Print.writeln(sFileName + ": LOG STARTED AT " + DateTime.getDateTime(), pwStdPrint);
            Server _server = null;
            boolean bResult = false;

            //Boot server
            Print.writelnWithTimeStamp("Booting Selenium server...", pwStdPrint);
            _server = new Server();
            _server.startSeleniumServer();
            Print.writelnWithTimeStamp("Selenium server booted!...", pwStdPrint);

            //Set URL
            switch (pRunType){
                case Imgur:
                    sURL = "http://imgur.com";
                    break;
                case Reddit:
                    sURL = "http://www.reddit.com";
                    break;
            }

            //Fetch web page
            Print.writelnWithTimeStamp("Fetching " + sURL + "...", pwStdPrint);
            Document oWebpage = WebPage.getWebPage(sURL);

            if(oWebpage == null){
                throw new IllegalStateException("Failed to fetch " + sURL + ".");
            }
            Print.writelnWithTimeStamp(sURL + " loaded...", pwStdPrint);
            switch (pRunType) {
                case Imgur:
                    scraper = new ImgurScrapePics(pwStdPrint, sURL);
                    bResult = scraper.ScrapeImages();
                    Print.writelnWithTimeStamp("Number of scraped images: " + scraper.getNumImages(), pwStdPrint);
                    break;
                case Reddit:
                    scraper = new RedditScrapePics(pwStdPrint, sURL);
                    bResult = scraper.ScrapeImages();
                    Print.writelnWithTimeStamp("Number of scraped images: " + scraper.getNumImages(), pwStdPrint);
                    break;
            }
            WebPage.selenium.shutDownSeleniumServer();
            pwStdPrint.close();
            if(!bResult){
                Print.writelnWithTimeStamp("Program failed...", pwStdPrint);
            }else{
                Print.writelnWithTimeStamp("Program succeeded...", pwStdPrint);
            }
            Print.writelnWithTimeStamp("Program ended...", pwStdPrint);

        }catch (Exception ex){
            Print.writeERRWithTimeStamp("CrwlrDriver(main) exception", ex, pwStdPrint);
        }
    }
}
