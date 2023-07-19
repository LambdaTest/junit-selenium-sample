package com.lambdatest;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AutoHealDemoTest {

    private RemoteWebDriver driver;
    private String LT_USERNAME = System.getenv("LT_USERNAME");
    private String LT_ACCESS_KEY = System.getenv("LT_ACCESS_KEY");
    private String AUTO_HEAL = System.getenv("AUTOHEAL");

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities desiredCaps = new DesiredCapabilities();
        desiredCaps.setCapability("LT:Options", new DesiredCapabilities() {
            {
                setCapability("build", "Auto Heal Demo");
                setCapability("name", "Auto Heal" +AUTO_HEAL.toUpperCase() );
                setCapability("autoHeal", AUTO_HEAL);
                setCapability("geoLocation", "IN");
            }
        });
        desiredCaps.setCapability("browserName", "Chrome");

        driver = new RemoteWebDriver(
                new URL("http://" + LT_USERNAME + ":" + LT_ACCESS_KEY + "@hub.lambdatest.com/wd/hub"), desiredCaps);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDemoSite() throws InterruptedException {
        loadAmazonWebsite();
        updateLocators();
        searchBookAndGoToCart();
    }

    public void updateLocators() throws InterruptedException {
        System.out.println("Updating Page Locators");
        driver.executeScript("document.getElementById('nav-search-submit-button').id = 'changed-search-submit-btn';");
        TimeUnit.SECONDS.sleep(1);
        driver.executeScript("document.getElementById('twotabsearchtextbox').id = 'newsearchtextbtn';");
        TimeUnit.SECONDS.sleep(1);
    }

    public void searchBookAndGoToCart() {
        System.out.println("Executing Test Script");
        try {
            WebElement dropDownBtn = driver.findElement(By.id("searchDropdownBox"));
            dropDownBtn.click();
            TimeUnit.SECONDS.sleep(1);

            WebElement booksBtn = driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]/option[11]"));
            if (booksBtn.isDisplayed()) {
                booksBtn.click();

                WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
                if (searchBox.isDisplayed()) {
                    TimeUnit.SECONDS.sleep(1);
                    searchBox.sendKeys("Python Programming");
                    TimeUnit.SECONDS.sleep(1);

                    WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
                    if (searchButton.isDisplayed()) {
                        TimeUnit.SECONDS.sleep(1);
                        searchButton.click();
                        driver.executeScript("lambda-status=passed");
                        return;
                    }
                }
            }
            driver.executeScript("lambda-status=failed");
        } catch (Exception e) {
            driver.executeScript("lambda-status=failed");
        }
    }

    public void loadAmazonWebsite() throws InterruptedException {
        System.out.println("Loading URL");
        driver.get("https://www.amazon.com");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(10);
        System.out.println("Page Loaded Successfully.");
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("AutoHealDemoTest");
    }
}
