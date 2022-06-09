package com.lambdatest;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

@RunWith(Parallelized.class)
public class JUnitConcurrentTodo {
    String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
    public String gridURL = "@hub.lambdatest.com/wd/hub";

    public String platform;
    public String browserName;
    public String browserVersion;
    public RemoteWebDriver driver = null;
    public String status = "failed";

    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[] { "Windows 10", "chrome", "latest" });
        env.add(new String[] { "Windows 10", "firefox", "latest" });
        env.add(new String[] { "Windows 10", "internet explorer", "latest" });
        return env;
    }

    public JUnitConcurrentTodo(String platform, String browserName, String browserVersion) {
        this.platform = platform;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", platform); // If this cap isn't specified, it will just get the any
                                                          // available one
        capabilities.setCapability("build", "JUnitParallelSample");
        capabilities.setCapability("name", "JUnitParallelSampleTest");
        // capabilities.setCapability("network", true); // To enable network logs
        // capabilities.setCapability("visual", true); // To enable step by step
        // screenshot
        // capabilities.setCapability("video", true); // To enable video recording
        // capabilities.setCapability("console", true); // To capture console logs
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testParallel() throws Exception {
        try {
            // Change it to production page
            driver.get("https://lambdatest.github.io/sample-todo-app/");
            // Let's mark done first two items in the list.
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();

            // Let's add an item in the list.
            driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            driver.findElement(By.id("addbutton")).click();

            // Let's check that the item we added is added in the list.
            String enteredText = driver.findElement(By
                    .xpath(
                            "/html/body/div/div/div/ul/li[6]/span"))
                    .getText();
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
