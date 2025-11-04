package com.lambdatest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.MalformedURLException;
import org.openqa.selenium.JavascriptExecutor;
import java.net.URL;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class SmartUI {
     String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
     String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
     public static RemoteWebDriver driver = null;

    public String gridURL = "@hub.lambdatest.com/wd/hub";
    public String status = "failed";

    @Before
    public void setUp() throws Exception {
        ChromeOptions browserOptions = new ChromeOptions();

        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "JUnitSampleTestApp");
        ltOptions.put("name", "JUnitSampleTest");
        ltOptions.put("project", "SmartUI-Junit-Selenium");  //Enter Project name here
        ltOptions.put("smartUI.project", "Junit-Selenium");  //Enter smartUI Project name here
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "junit-junit");
        browserOptions.setCapability("LT:Options", ltOptions);

        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), browserOptions);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSimple() throws Exception {
        try {
           driver.get("https://lambdatest.github.io/sample-todo-app/");
            
           //Let's mark done first two items in the list.
           driver.findElement(By.name("li1")).click();
           driver.findElement(By.name("li2")).click();

           // Let's add an item in the list.
           driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
           driver.findElement(By.id("addbutton")).click();

           // Let's check that the item we added is added in the list.
            String enteredText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
            if (enteredText.equals("Yey, Let's add it to list")) {
               status = "passed";
           }

           // Webhook for Screenshot
           Map<String,Object> config = new HashMap<>();
           config.put("screenshotName","todo-list"); //Add your snapshot name here for SmartUI
           ((JavascriptExecutor)driver).executeScript("smartui.takeScreenshot", config); //Hook for capturing snapshot on SmartUI

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}

