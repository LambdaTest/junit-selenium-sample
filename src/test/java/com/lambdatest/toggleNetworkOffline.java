package com.lambdatest;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;
import org.openqa.selenium.chromium.HasNetworkConditions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v97.browser.Browser;
import org.openqa.selenium.devtools.v97.browser.model.Bounds;
import org.openqa.selenium.devtools.v97.browser.model.WindowID;
import org.openqa.selenium.devtools.v97.browser.model.WindowState;
import org.openqa.selenium.devtools.v97.log.Log;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class toggleNetworkOffline {

    private WebDriver driver;
    private String Status = "failed";

  
  @Before
  public void setup() throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "97");
        caps.setCapability("build", "Selenium 4");
        caps.setCapability("name", this.getClass().getName());
        caps.setCapability("plugin", "git-junit");
        caps.setCapability("selenium_version", "4.0.0");

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        caps.setCapability("tags", Tags);
        driver =  new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

    }

    @Test
    public void toggleOffline() throws InterruptedException {

        driver = new Augmenter().augment(driver);

        driver.get("https://www.duckduckgo.com");
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();

        devTools.send(Log.enable());
        devTools.send(Browser.setWindowBounds(new WindowID(1), new Bounds(Optional.of(20), Optional.of(20),
                Optional.of(20), Optional.of(20), Optional.of(WindowState.NORMAL))));

        Thread.sleep(30000);

        WebDriver augmentedDriver = new Augmenter().augment(driver);
        ChromiumNetworkConditions networkConditions = new ChromiumNetworkConditions();
        networkConditions.setOffline(true);
        ((HasNetworkConditions) augmentedDriver).setNetworkConditions(networkConditions);

        try {
            driver.get("https://www.lambdatest.com");
            // "If Network is set to be offline, the previous line should throw an
            // exception";
        } catch (WebDriverException ex) {
            ((HasNetworkConditions) augmentedDriver).setNetworkConditions(new ChromiumNetworkConditions());
        }
        driver.get("https://www.lambdatest.com");
    }

    @After
    public void tearDown() {
        ((RemoteWebDriver) driver).executeScript("lambda-status=" + Status);
        driver.quit();
    }

}