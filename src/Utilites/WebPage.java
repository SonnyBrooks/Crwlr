package Utilites;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Andy on 8/18/2014.
 */
public class WebPage {

    //Jsoup
    public static Document getWebPage(String sURL)throws IOException{
        return Jsoup.connect(sURL).timeout(0).get();
    }
}
