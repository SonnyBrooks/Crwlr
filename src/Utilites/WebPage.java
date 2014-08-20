package Utilites;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import com.thoughtworks.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

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
        String[] commands = new String[]{};
        String AutoItScriptpath = "C:\\Users\\Andy\\Desktop\\DevTools\\ImgDownloadScript.exe";
        commands = new String[]{AutoItScriptpath};
        Runtime.getRuntime().exec(commands);

        DefaultSelenium selenium = new DefaultSelenium("localhost", 4444,
                "*firefox", "http://www.testingexcellence.com");
        selenium.start();
        selenium.open(sURL);
        selenium.windowMaximize();

        //native key strokes for CTRL and S keys
        Robot robot = new  Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F4);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F4);
        //once the dialog box is displayed, the autoIt script
        //will fire off and send an Enter command.
        return true;
    }
}

