package com.lambdatest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Authentication {
    String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    public String status = "failed";

    @Before
    public void setup() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("user", System.getenv("LT_USERNAME"));
        ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY"));
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.0.0");
        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL(gridURL), capabilities);
        System.out.println(driver);
    }

    @Test
    public void authentication() {
        Augmenter augmenter = new Augmenter();
        driver =  (RemoteWebDriver) augmenter.augment(driver);

        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();

        driver = (RemoteWebDriver) augmenter.addDriverAugmentation("chrome", HasAuthentication.class,
                (caps, exec) -> (whenThisMatches, useTheseCredentials) -> devTools.getDomains().network()
                        .addAuthHandler(whenThisMatches, useTheseCredentials))
                .augment(driver);

        ((HasAuthentication) driver).register(UsernameAndPassword.of("foo", "bar"));

        driver.get("http://httpbin.org/basic-auth/foo/bar");

        String text = driver.findElement(By.tagName("body")).getText();
        System.out.println(text);
        if (text.contains("authenticated")) {
            markStatus("passed", "Authentication Successful", driver);
        } else {
            markStatus("failed", "Authentication Failure", driver);
        }

    }

    @After
    public void tearDown() {
        try {
            driver.quit();
        } catch (

        Exception e) {
            markStatus("failed", "Got exception!", driver);
            e.printStackTrace();
            driver.quit();
        }
    }

    public static void markStatus(String status, String reason, WebDriver driver) {
        JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
        jsExecute.executeScript("lambda-status=" + status);
        System.out.println(reason);
    }
}