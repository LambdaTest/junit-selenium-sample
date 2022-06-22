package com.lambdatest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;




public class JavaScriptExceptions {
    public static String hubURL = "https://hub.lambdatest.com/wd/hub";
    static Boolean success = false;

    private WebDriver driver;

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

        driver = new RemoteWebDriver(new URL(hubURL), capabilities);
        System.out.println(driver);
    }

    @Test
    public void javaScriptExceptions() throws InterruptedException {
        Augmenter augmenter = new Augmenter();
        driver = augmenter.augment(driver);

        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();

        List<JavascriptException> jsExceptionsList = new ArrayList<>();
        Consumer<JavascriptException> addEntry = jsExceptionsList::add;
        devTools.getDomains().events().addJavascriptExceptionListener(addEntry);

        driver.get("https://facebook.com");

        WebElement link2click = driver.findElement(By.name("login"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                link2click, "onclick", "throw new Error('My JS Error');");
        link2click.click();

        Thread.sleep(1000);
        for (JavascriptException jsException : jsExceptionsList) {
            System.out.println("My JS exception message: " + jsException.getMessage());
            System.out.println("My JS exception system: " + jsException.getSystemInformation());
            jsException.printStackTrace();
            success = true;
        }
        Thread.sleep(1000);
        if (success) {
            markStatus("passed", "Got JS exception", driver);
        } else {
            markStatus("failed", "Didn't got JS exception", driver);
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