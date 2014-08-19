package Utilites;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import com.thoughtworks.selenium.*;

/**
 * Created by Andy on 8/18/2014.
 */
public class WebPage {
    //Jsoup
    public static Document getWebPage(String sURL)throws IOException{
        return Jsoup.connect(sURL).timeout(0).get();
    }

    //Selenium
    public static boolean getImage(String sURL)throws Exception{
        DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "*googlechrome", sURL);
        selenium.start();
        selenium.open(sURL);
        selenium.windowMaximize();

        //native key strokes for CTRL and S keys
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        return true;
    }
}

