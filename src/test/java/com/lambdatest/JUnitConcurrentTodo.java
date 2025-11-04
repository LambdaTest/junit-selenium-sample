package com.lambdatest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
        env.add(new String[]{"Windows 10", "chrome", "latest"});
        env.add(new String[]{"Windows 10","firefox","latest"});
        env.add(new String[]{"Windows 10","edge","latest"});
        return env;
    }
   
    public JUnitConcurrentTodo(String platform, String browserName, String browserVersion) {
        this.platform = platform;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
     }
    @Before
    public void setUp() throws Exception {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setCapability("platformName", platform);
        browserOptions.setCapability("browserName", browserName);
        browserOptions.setCapability("browserVersion", browserVersion);

        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "JUnitParallelSampleApp");
        ltOptions.put("name", "JUnitParallelSampleTest");
        ltOptions.put("selenium_version", "4.0.0");
        // ltOptions.put("project", "");  //Enter Project name here
        ltOptions.put("smartUI.project", ""); //Enter smartUI Project name here
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "junit-junit");
        // ltOptions.put("visual", true); // To enable step by step screenshot
        // ltOptions.put("network", true); // To enable network logs
        // ltOptions.put("video", true); // To enable video recording
        // ltOptions.put("console", true); // To capture console logs
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
    public void testParallel() throws Exception {
       try {
              //Change it to production page
            driver.get("https://lambdatest.github.io/sample-todo-app/");

              // Add Webhook here for Screenshot         

              //Let's mark done first two items in the list.
              driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();
             
             // Let's add an item in the list.
              driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            driver.findElement(By.id("addbutton")).click();
             
              // Let's check that the item we added is added in the list.
           String enteredText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
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
