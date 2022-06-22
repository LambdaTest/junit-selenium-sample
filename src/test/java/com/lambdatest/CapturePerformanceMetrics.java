package com.lambdatest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v97.performance.Performance;
import org.openqa.selenium.devtools.v97.performance.model.Metric;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class CapturePerformanceMetrics {
    public static String hubURL = "https://hub.lambdatest.com/wd/hub";

    static Boolean success = false;
    private WebDriver driver;

  
  @Before
  public void setup() throws MalformedURLException  {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "97");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("user", System.getenv("LT_USERNAME"));
        ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY"));
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.0.0");
        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL(hubURL), capabilities);
        System.out.println(driver);
    }

    @Test
    public void capturePerformanceMetrics() {
        Augmenter augmenter = new Augmenter();
        driver = augmenter.augment(driver);

        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();

        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = devTools.send(Performance.getMetrics());

        driver.get("https://lambdatest.com");

        for (Metric m : metricList) {
            System.out.println(m.getName() + " = " + m.getValue());
            success = true;
        }
        if (success) {
            markStatus("passed", "Performance metrics successfully fetched", driver);
        } else {
            markStatus("failed", "Unable to fetch Performance metrics", driver);
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