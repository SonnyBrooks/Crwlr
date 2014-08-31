package CrwlrBaseClass;

import Utilites.Print;
import Utilites.WebPage;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Andy on 8/18/2014.
 */
public class RedditScrapePics extends Scraper {

    private static PrintWriter pwWriter = null;
    private static Document oWebPage = null;

    public RedditScrapePics(PrintWriter pwWriter, String sURL){
        try {
            this.pwWriter = pwWriter;
            if(sURL.indexOf("reddit") == -1){
                throw new IllegalArgumentException(sURL + " is not a reddit link. Failing program.");
            }
            if(sURL.contains("nsfw")){
                oWebPage = WebPage.redditNSFWGetWebPage(sURL);
            }else {
                oWebPage = WebPage.getWebPage(sURL);
            }
        }catch (Exception ex){
            Print.writeERRWithTimeStamp("RedditScrapePics(Constructor) failed to initialize...", ex, pwWriter);
        }
    }

    public boolean ScrapeImages(){
        boolean bResult = false;
        int iNumImages = -1;
        int iPage = -1;
        String sImageURL = "";

        try{
            iNumImages = 0;
            iPage = 1;

            //Get past Over 18 prompt
            if(oWebPage.body().text().indexOf("you must be at least eighteen years old to view this") > -1){
                WebPage.selenium.click("yes");
                WebPage.selenium.waitForPageToLoad("2000");
            }

            //Get image URLS
            //Print.writelnWithTimeStamp("Scraping page " + iNumImages, pwWriter);


            bResult = true;

            //Ensure that all images were scraped
            if(iNumImages != imagesScraped.size()) {
                bResult = false;
                throw new IllegalStateException("The number of images found is not equal to the number of images scraped");
            }

        }catch (Exception ex){
            Print.writeERRWithTimeStamp("RedditScrapePics(ScrapeImages) exception.", ex, pwWriter);
        }

        return bResult;
    }

}
