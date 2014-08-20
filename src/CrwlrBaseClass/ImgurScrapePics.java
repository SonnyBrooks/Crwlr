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
public class ImgurScrapePics extends Scraper {

    private static PrintWriter pwWriter = null;
    private static Document oWebPage = null;

    public ImgurScrapePics(PrintWriter pwWriter, String sURL){
        try {
            this.pwWriter = pwWriter;
            if(sURL.indexOf("imgur") == -1){
                throw new IllegalArgumentException(sURL + " is not an imgur link. Failing program.");
            }
            oWebPage = WebPage.getWebPage(sURL);
        }catch (Exception ex){
            Print.writeERRWithTimeStamp("ImgurScrapePics(Constructor) failed to initialize...", ex, pwWriter);
        }
    }

    public boolean ScrapeImages(){
        boolean bResult = false;
        int iNumImages = -1;
        int iNumElements = -1;
        String sImageURL = "";
        Elements leImageElements = null;


        try{
            leImageElements = oWebPage.getElementsByTag("img");
            iNumImages = 0;
            iNumElements = 0;

            //Get image URLS
            for(Element image: leImageElements){
                sImageURL = image.attr("src").replace("//", "").replace(".jpg", "");
                if(sImageURL.indexOf("pixel.quantserve.com") == -1 && sImageURL.indexOf("/images/loaders/ddddd1_181817/48.gif") == -1){
                    imagesScraped.add(sImageURL);
                    WebPage.getImage("http://" + sImageURL + ".jpg");
                    iNumImages++;
                    iNumElements++;
                    Print.writelnWithTimeStamp(iNumElements + ": Added " + sImageURL, pwWriter);
                } else{
                    iNumElements++;
                    Print.writelnWithTimeStamp(iNumElements + ": Skipped non-image " + sImageURL, pwWriter);
                }
            }

            //Ensure that all images were scraped
            if(iNumImages != imagesScraped.size()) {
                throw new IllegalStateException("The number of images found is not equal to the number of images scraped");
            }

        }catch (Exception ex){
            Print.writeERRWithTimeStamp("ImgurScrapePics(ScrapeImages) exception.", ex, pwWriter);
        }

        return bResult;
    }

}
