package com.lambdatest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class JUnitTodo {
     String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
     String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
     public static RemoteWebDriver driver = null;

    /*
    Steps to run Smart UI project (https://beta-smartui.lambdatest.com/)
    Step - 1 : Change the hub URL to @beta-smartui-hub.lambdatest.com/wd/hub
    Step - 2 : Add "smartUI.project": "<Project Name>" as a capability above
    Step - 3 : Add "driver.executeScript("smartui.takeScreenshot");" code wherever you need to take a screenshot
    Note: for additional capabilities navigate to https://www.lambdatest.com/support/docs/test-settings-options/
     */

    public String gridURL = "@hub.lambdatest.com/wd/hub";
    public String status = "failed";

    @Before
    public void setUp() throws Exception {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setCapability("platformName", "Windows 10");
        browserOptions.setCapability("browserVersion", "latest");

        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "JUnitSampleTestApp");
        ltOptions.put("name", "JUnitSampleTest");
        ltOptions.put("selenium_version", "4.0.0");
        // ltOptions.put("project", "");  //Enter Project name here
        // ltOptions.put("smartUI.project", "");  //Enter smartUI Project name here
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "junit-junit");
        // ltOptions.put("visual", true);  // To enable step by step screenshot
        // ltOptions.put("network", true);  // To enable network logs
        // ltOptions.put("video", true);  // To enable video recording
        // ltOptions.put("console", true);  // To capture console logs
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

           // Add Webhook here for Screenshot
            
           //Let's mark done first two items in the list.
           driver.findElement(By.name("li1")).click();
           driver.findElement(By.name("li2")).click();

           // Let's add an item in the list.
           driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
           driver.findElement(By.id("addbutton")).click();

           // Let's check that the item we added is added in the list.
           String enteredText =  driver.findElementByXPath("/html/body/div/div/div/ul/li[6]/span").getText();
           if (enteredText.equals("Yey, Let's add it to list")) {
               status = "passed";
           }
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

