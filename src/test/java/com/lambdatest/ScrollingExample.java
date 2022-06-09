package com.lambdatest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.net.URL;

public class ScrollingExample {
    String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    public String status = "failed";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("platform", "Windows 10"); // If this cap isn't specified, it will just get the any
                                                              // available one
        capabilities.setCapability("build", "Junit Testing Example");
        capabilities.setCapability("name", "ScrollingExample Test");
        capabilities.setCapability("plugin", "git-junit");

        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void ScrollingExampleTest() throws InterruptedException {
        System.out.println("Loading Url");

        driver.get("https://lambdatest.com");

        // Locating element by link text
        WebElement Element = driver.findElement(By.linkText("Book a Demo"));

        // Scrolling down the page till the element is found
        driver.executeScript("arguments[0].scrollIntoView();", Element);
        Thread.sleep(1500);

        // Scrolling down by pixels
        driver.executeScript("window.scrollBy(0,-500)", "");

        Thread.sleep(1500);

        // Scrolling up by pixels
        driver.executeScript("window.scrollBy(0,500)", "");

        Thread.sleep(1500);

        status = "passed";
        Thread.sleep(150);

        System.out.println("TestFinished");

    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
