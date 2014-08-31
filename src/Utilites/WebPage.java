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
    public static DefaultSelenium selenium;

    //Jsoup
    public static Document getWebPage(String sURL)throws IOException{
        return Jsoup.connect(sURL).timeout(0).get();
    }

    //Selenium
    public static boolean getImage(String sURL)throws Exception{
        String[] commands = new String[]{};
        String AutoItScriptpath = "src\\ImgDownloadScript.exe";
        commands = new String[]{AutoItScriptpath};
        Runtime.getRuntime().exec(commands);

        selenium = new DefaultSelenium("localhost", 4444,
                "*googlechrome", sURL);
        selenium.start();
        selenium.open(sURL);

        //native key strokes for CTRL and S keys
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        //once the dialog box is displayed, the autoIt script
        //will fire off and send an Enter command.
        Thread.sleep(1500);
        selenium.stop();
        Thread.sleep(500);
        return true;
    }

    public static Document redditNSFWGetWebPage(String sURL) throws IOException{
        selenium = new DefaultSelenium("localhost", 4444, "*googlechrome", sURL);
        selenium.start();
        selenium.open(sURL);

        selenium.click("yes");

        return Jsoup.connect(selenium.getLocation()).timeout(0).get();

    }
}

