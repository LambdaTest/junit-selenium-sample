# JUnit-Tutorial
![LambdaTest Logo](https://www.lambdatest.com/static/images/logo.svg)

![Junit Logo](https://www.lambdatest.com/blog/wp-content/uploads/2020/08/junit.png)

This post will help you in getting started with configuring and running your JUnit automation testing scripts on LambdaTest cloud Selenium grid platform.

## Prerequisite To Perform JUnit Automation Testing
To run your test script using JUnit with Selenium, first you need to setup the environment.

1)	Install JDK 1.6 or higher version
2)	Latest Selenium client and it’s WebDriver Bindings.
3)	It would be very beneficial if you use code project management options like Maven or Ant. Maven supports JUnit out of the box. You would just have to define Selenium dependencies in it’s project object model file or pom.xml file.
4)	To test your locally or privately hosted files, you need LambdaTest Tunnel binary file.

## Maven Dependency
```
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>

```
## Your First Test With JUnit Framework

Now let’s start with a simple Selenium WebDriver test. Below is a Selenium script for JUnit automation testing that will open a sample to-do application which will do following task:

* Mark first two items as mark done.
* Add a new item in the list.
* Return the added item.

To run your first test using JUnit with Selenium, follow the simple example below. Same code can be downloaded from our repository: Java-Junit-Selenium

**JUnit Todo : Sample App**

```
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
public class JUnitTodo {
     public String username = "YOUR_USERNAME";
    public String accesskey = "YOUR_ACCESS_KEY";
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    @Before
    public void setUp() throws Exception {
       DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "70.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
  
    @Test
    public void testSimple() throws Exception {
       try {
              //Change it to production page
            driver.get("https://lambdatest.github.io/sample-todo-app/");
             
              //Let's mark done first two items in the list.
              driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();
             
             // Let's add an item in the list.
              driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            driver.findElement(By.id("addbutton")).click();
             
              // Let's check that the item we added is added in the list.
            String enteredText =       driver.findElementByXPath("/html/body/div/div/div/ul/li[6]/span").getText();
            if (enteredText.equals("Yey, Let's add it to list")) {
                status = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @After
    public void tearDown() throws Exception {
       if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}

```

## Executing The Test

You would need to execute the below command in your terminal/cmd.

## Running First Test

<code>mvn test -P single</code>

## Configuring Desired Capabilities

First step is to configure your test scripts to connect with LambdaTest Selenium automation gird. You would have to invoke of remote webdriver instead of the native browser webdrivers.

**Local Webdriver:**

<code>FirefoxDriver driver = new FirefoxDriver():</code>

You would have to change it remote webdriver and at the same time pass capabilities related to the browser, browser versions etc. In simple terms, it would look something like this:

**Remote WebDriver:**
```
WebDriver driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub"),
DesiredCapabilities.firefox());
```

## Capabilities Generator At LambdaTest Will Provide You With The Below Program:

```
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability("build", "your build name");
capabilities.setCapability("name", "your test name");
capabilities.setCapability("platform", "macOS High Sierra");
capabilities.setCapability("browserName", "Firefox");
capabilities.setCapability("version","64.0");
capabilities.setCapability("selenium_version","3.13.0");
capabilities.setCapability("visual",true);
capabilities.setCapability("firefox.driver",v0.23.0);
```

## Testing Locally Hosted Projects Using Junit With Selenium

To help you perform cross browser testing of locally stored web pages, LambdaTest provides an SSH(Secure Shell) tunnel connection with the name Lambda Tunnel. With Lambda Tunnel, you can test your locally hosted files before you make them live over the internet. You could even perform cross browser testing from different IP addresses belonging to various geographic locations. You can also use LambdaTest Tunnel to test web-apps and websites that are permissible inside your corporate firewall. The SSH tunnel provided by LambdaTest acts as a proxy server for hosting your web pages from your local machine to Virtual machines running on LambdaTest cloud servers.

Download the binary file of:

* [Lambda Tunnel for Windows](http://downloads.lambdatest.com/tunnel/windows/64bit/LT_Windows.zip)
* [Lambda Tunnel for Mac](http://downloads.lambdatest.com/tunnel/mac/64bit/LT_Mac.zip)
* [Lambda Tunnel for Linux](http://downloads.lambdatest.com/tunnel/linux/64bit/LT_Linux.zip)

**Syntax To Start Lambda Tunnel**

<code>LT -user [user's login email] -key [user's access key]</code>

So for example, if your user login email is [example@lambdatest.com](mailto:example@lambdatest.com) and your user key is 123asd123, then the command would be:

<code>LT -user example@lambdatest.com -key 123asd123</code>

Once, the tunnel is successfully set up. You can add the below code to your capabilities for testing internal servers on your network.

**Tunnel Capability**

```
DesiredCapabilities capabilities = new DesiredCapabilities();
  capabilities.setCapability("tunnel", true);
```

## Parallel Testing using JUnit with Selenium

Parallel Testing using JUnit with Selenium is one of the most demanding features of LambdaTest Selenium Grid. By parallel testing, you can run more than one test case, simultaneously. This means that Parallel testing would allow you to execute numerous automation test cases altogether. So you execute a single test scenario across different browsers or could run different test scenarios across the same browser but with different browser versions. Wondering how many parallel test cases can you run? That depends entirely on the number of concurrent session under your opted plan.

For instance, if you have a bucket of 100 automated test cases, and the average time for execution of a single test is around 6 minutes. The time taken normally to completely execute all your test cases sequentially would be 600 minutes i.e. 10 hours. However, if you are opting for a plan which offers 2 concurrent sessions then you can perform 2 parallel test cases. This would divide your time in half as the total time taken through parallel testing with 2 concurrent sessions for the above scenario would be 300 minutes i.e. 5 hours. Similarly, if you have got 4 concurrent sessions with you then the time taken would be quarter with respect to sequential testing. This way you could fasten your release cycles as much as you want.

**JUnit: Parallelized Class**

```
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
 
import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerScheduler;
 
public class Parallelized extends Parameterized {
    private static class ThreadPoolScheduler implements RunnerScheduler {
        private ExecutorService executor;
        public ThreadPoolScheduler() {
            String threads = System.getProperty("junit.parallel.threads", "15");
            int numThreads = Integer.parseInt(threads);
            executor = Executors.newFixedThreadPool(numThreads);
        }
        @Override
        public void finished() {
            executor.shutdown();
            try {
                executor.awaitTermination(10, TimeUnit.MINUTES);
            } catch (InterruptedException exc) {
                throw new RuntimeException(exc);
            }
        }
        @Override
        public void schedule(Runnable childStatement) {
            executor.submit(childStatement);
        }
    }
 
    public Parallelized(Class<?> klass) throws Throwable {
        super(klass);
        setScheduler(new ThreadPoolScheduler());
    }
}

Let us take a look at a reference of the above helper class for executing parallel test using JUnit automation framework.
JUnit Concurrent Todo : Sample App
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
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
     public String username = "YOUR_USERNAME";
    public String accesskey = "YOUR_ACCESS_KEY";
    public String gridURL = "@hub.lambdatest.com/wd/hub";
 
     public String platform;
     public String browserName;
     public String browserVersion;
    public RemoteWebDriver driver = null;
     boolean status = false;
        @Parameterized.Parameters
     public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"WIN10", "chrome", "70.0"});
        env.add(new String[]{"macos 10.12","firefox","62.0"});
        env.add(new String[]{"WIN8","internet explorer","10"});
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
        capabilities.setCapability("platform", platform); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "JUnitParallelSample");
        capabilities.setCapability("name", "JUnitParallelSampleTest");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
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
              //Let's mark done first two items in the list.
              driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();
             
             // Let's add an item in the list.
              driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            driver.findElement(By.id("addbutton")).click();
             
              // Let's check that the item we added is added in the list.
            String enteredText = driver.findElementByXPath("/html/body/div/div/div/ul/li[6]/span").getText();
            if (enteredText.equals("Yey, Let's add it to list")) {
                status = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }  
    @After
    public void tearDown() throws Exception {
       if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
```

## About LambdaTest

[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your [selenium automation testing](https://www.lambdatest.com/selenium-automation) to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.

## Resources:

**[JUnit with Selenium](https://www.lambdatest.com/support/docs/junit-with-selenium-running-junit-automation-scripts-on-lambdatest-selenium-grid/)**
