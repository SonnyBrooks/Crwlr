package CrwlrBaseClass;

import Utilites.Print;
import Utilites.WebPage;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Andy on 8/18/2014.
 */
public class ImgurScrapePics extends Scraper {

    private static PrintWriter pwWriter = null;
    private static Document oWebPage = null;

    public ImgurScrapePics(PrintWriter pwWriter, String sURL){
        try {
            ImgurScrapePics.pwWriter = pwWriter;
            if(!sURL.contains("imgur")){
                throw new IllegalArgumentException(sURL + " is not an imgur link. Failing program.");
            }
            init(sURL);
        }catch (Exception ex){
            Print.writeERRWithTimeStamp("ImgurScrapePics(Constructor) failed to initialize...", ex, pwWriter);
        }
    }

    public void init(String sURL) throws IOException{
        oWebPage = WebPage.getWebPage(sURL);
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
                sImageURL = image.attr("src").replace("//", "");
                sImageURL = "http://" + sImageURL.replace("b.jpg", ".jpg");
                if(!sImageURL.contains("pixel.quantserve.com") && !sImageURL.contains("/images/loaders/ddddd1_181817/48.gif")) {
                    imagesScraped.add(sImageURL);
                    WebPage.getImage(sImageURL);
                    iNumImages++;
                    iNumElements++;
                    Print.writelnWithTimeStamp(iNumElements + "/" + leImageElements.size() + ": Added " + sImageURL, pwWriter);
                } else{
                    iNumElements++;
                    Print.writelnWithTimeStamp(iNumElements + ": Skipped non-image " + sImageURL, pwWriter);
                }
            }
            bResult = true;

            //Ensure that all images were scraped
            if(iNumImages != imagesScraped.size()) {
                bResult = false;
                throw new IllegalStateException("The number of images found is not equal to the number of images scraped");
            }

        }catch (Exception ex){
            Print.writeERRWithTimeStamp("ImgurScrapePics(ScrapeImages) exception.", ex, pwWriter);
        }

        return bResult;
    }

}
