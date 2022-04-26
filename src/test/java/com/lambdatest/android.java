package com.lambdatest;

import io.appium.java_client.MobileBy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class android {
    String username = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" //Enter the Username here
            : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY"  //Enter the Access key here
            : System.getenv("LT_ACCESS_KEY");
    public static RemoteWebDriver driver = null;
    public String gridURL = "@beta-hub.lambdatest.com/wd/hub";
    public String status = "passed";
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("build", "JUNIT Native App automation");
        capabilities.setCapability("name", "Java JUnit Android Pixel 6");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "Pixel 6");
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("platformVersion","12");
        capabilities.setCapability("app","lt://APP100202491650550026383902");
        capabilities.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("console",true);
        capabilities.setCapability("network",true);
        capabilities.setCapability("visual",true);
        try
        {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Invalid grid URL");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSimple() throws Exception
    {
        try
        {
            WebDriverWait color = new WebDriverWait(driver, 30);
            color.until(ExpectedConditions.elementToBeClickable(MobileBy.id("color")));
            driver.findElementById("color").click();


            WebDriverWait geo =  new WebDriverWait(driver, 30);
            geo.until(ExpectedConditions.elementToBeClickable(MobileBy.id("geoLocation")));
            driver.findElementById("geoLocation").click();
            Thread.sleep(5000);
            driver.navigate().back();


            WebDriverWait text = new WebDriverWait(driver, 30);
            text.until(ExpectedConditions.elementToBeClickable(MobileBy.id("Text")));
            driver.findElementById("Text").click();


            WebDriverWait nf =  new WebDriverWait(driver, 30);
            nf.until(ExpectedConditions.elementToBeClickable(MobileBy.id("notification")));
            driver.findElementById("notification").click();


            WebDriverWait toast = new WebDriverWait(driver, 30);
            toast.until(ExpectedConditions.elementToBeClickable(MobileBy.id("toast")));
            driver.findElementById("toast").click();



            WebDriverWait browser = new WebDriverWait(driver, 30);
            browser.until(ExpectedConditions.elementToBeClickable(By.id("Browser")));
            driver.findElementById("Browser").click();
            Thread.sleep(10000);


            WebDriverWait el7 =  new WebDriverWait(driver, 30);
            el7.until(ExpectedConditions.elementToBeClickable(MobileBy.id("url")));
            driver.findElementById("url").sendKeys("https://www.lambdatest.com/");

            WebDriverWait el8 =  new WebDriverWait(driver, 30);
            el8.until(ExpectedConditions.elementToBeClickable(MobileBy.id("find")));
            driver.findElementById("find").click();
            Thread.sleep(5000);
            driver.navigate().back();

            status="passed"; 
        }
            catch (Exception e)
             {
                System.out.println(e.getMessage());
                status="failed";
             }
    }
    @After
    public void tearDown() throws Exception
    {
        if (driver != null)
        {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}

