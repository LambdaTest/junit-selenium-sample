package com.lambdatest;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v97.log.Log;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class CDPTestConsole {
  public static String hubURL = "https://hub.lambdatest.com/wd/hub";
  static Boolean status = false;

  private WebDriver driver;

  @Before
  public void setup() throws MalformedURLException {

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
  public void cdpTestConsole() throws InterruptedException {
    Augmenter augmenter = new Augmenter();
    driver = augmenter.augment(driver);

    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();

    devTools.send(Log.enable());
    devTools.addListener(Log.entryAdded(), logEntry -> {
      System.out.println("text: " + logEntry.getText());
      System.out.println("level: " + logEntry.getLevel());
      status = true;
    });
    driver.get("http://the-internet.herokuapp.com/broken_images");
    Thread.sleep(1000 * 10);
    if (status) {
      markStatus("passed", "Console logs enteries", driver);
    } else {
      markStatus("failed", "Did not found Console logs enteries", driver);
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