package CrwlrBaseClass;

import java.util.ArrayList;

/**
 * Created by Andy on 8/18/2014.
 */
public abstract class Scraper {
    public ArrayList<String> imagesScraped = new ArrayList<String>();
    public abstract boolean ScrapeImages();

    public int getNumImages(){
        return imagesScraped.size();
    }

    public String toString(){
        String sReturn = "\n";

        for(String sURL: imagesScraped){
            sReturn += sURL + "\n";
        }

        return sReturn;
    }
}
