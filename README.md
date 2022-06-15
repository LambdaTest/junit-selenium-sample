# Run Selenium Tests With JUnit On LambdaTest

![image](https://user-images.githubusercontent.com/70570645/171432631-dcc31b10-6590-4877-98c0-4ac702fbd441.png)

<p align="center">
  <a href="https://www.lambdatest.com/blog/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample" target="_bank">Blog</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.lambdatest.com/support/docs/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample" target="_bank">Docs</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.lambdatest.com/learning-hub/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample" target="_bank">Learning Hub</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.lambdatest.com/newsletter/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample" target="_bank">Newsletter</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.lambdatest.com/certifications/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample" target="_bank">Certifications</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.youtube.com/c/LambdaTest" target="_bank">YouTube</a>
</p>
&emsp;
&emsp;
&emsp;

*Learn how to use JUnit framework to configure and run your Java automation testing scripts on the LambdaTest platform*

[<img height="58" width="200" src="https://user-images.githubusercontent.com/70570645/171866795-52c11b49-0728-4229-b073-4b704209ddde.png">](https://accounts.lambdatest.com/register?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)


## Table Of Contents

* [Pre-requisites](#pre-requisites)
* [Run Your First Test](#run-your-first-test)
* [Parallel Testing With JUnit](#run-parallel-tests-using-junit)
* [Local Testing With JUnit](#testing-locally-hosted-or-privately-hosted-projects)

## Pre-requisites

Before you can start performing Java automation testing with Selenium, you would need to:

- Install the latest **Java development environment** i.e. **JDK 1.6** or higher. We recommend using the latest version.

- Download the latest **Selenium Client** and its **WebDriver bindings** from the [official website](https://www.selenium.dev/downloads/). Latest versions of Selenium Client and WebDriver are ideal for running your automation script on LambdaTest Selenium cloud grid.

- Install **Maven** which supports **JUnit** framework out of the box. **Maven** can be downloaded and installed following the steps from [the official website](https://maven.apache.org/). Maven can also be installed easily on **Linux/MacOS** using [Homebrew](https://brew.sh/) package manager.

- You would have to add the following maven dependency to your `pom.xml` file if working on your local project.
  ```xml
  <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
  </dependency>
  ```

### Cloning Repo And Installing Dependencies

**Step 1:** Clone the LambdaTest’s JUnit-Selenium-Sample repository and navigate to the code directory as shown below:

```bash
git clone https://github.com/LambdaTest/junit-selenium-sample
cd junit-selenium-sample
```

You may also want to run the command below to check for outdated dependencies.

```bash
mvn versions:display-dependency-updates
```

### Setting Up Your Authentication

Make sure you have your LambdaTest credentials with you to run test automation scripts. You can get these credentials from the [LambdaTest Automation Dashboard](https://automation.lambdatest.com/build?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample) or by your [LambdaTest Profile](https://accounts.lambdatest.com/login?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample).

**Step 2:** Set LambdaTest **Username** and **Access Key** in environment variables.

* For **Linux/macOS**:
  
  ```bash
  export LT_USERNAME="YOUR_USERNAME" 
  export LT_ACCESS_KEY="YOUR ACCESS KEY"
  ```
  * For **Windows**:
  ```bash
  set LT_USERNAME="YOUR_USERNAME" 
  set LT_ACCESS_KEY="YOUR ACCESS KEY"
  ```

## Run Your First Test

>**Test Scenario**: Checkout sample [JUnitTodo.java](https://github.com/LambdaTest/junit-selenium-sample/blob/master/src/test/java/com/lambdatest/JUnitTodo.java) file. This JUnit Selenium script tests a sample to-do list app by marking couple items as done, adding a new item to the list and finally displaying the count of pending items as output.

### Configuring your Test Capabilities

**Step 3:** In the test script, you need to update your test capabilities. In this code, we are passing browser, browser version, and operating system information, along with LambdaTest Selenium grid capabilities via capabilities object. The capabilities object in the above code are defined as:

```java
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
```

You can generate capabilities for your test requirements with the help of our inbuilt [Desired Capability Generator](https://www.lambdatest.com/capabilities-generator/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample).

### Executing the Test

**Step 4:** The tests can be executed in the terminal using the following command.

```bash
mvn test -P single
```

Your test results would be displayed on the test console (or command-line interface if you are using terminal/cmd) and on [LambdaTest automation dashboard](https://automation.lambdatest.com/build). 

## Run Parallel Tests Using JUnit

Check out the [Parallelized.java](https://github.com/LambdaTest/junit-selenium-sample/blob/master/src/test/java/com/lambdatest/Parallelized.java) class we have used for running our Parallel Tests using JUnit.


Check out the [JUnitConcurrentTodo.java](https://github.com/LambdaTest/junit-selenium-sample/blob/master/src/test/java/com/lambdatest/JUnitConcurrentTodo.java) file for executing parallel test using JUnit automation framework.

### Executing Parallel Tests Using JUnit

To run parallel tests using **JUnit**, we would have to execute the below command in the terminal:

```bash
mvn test -P parallel
```

## Testing Locally Hosted Or Privately Hosted Projects

You can test your locally hosted or privately hosted projects with LambdaTest Selenium grid using LambdaTest Tunnel. All you would have to do is set up an SSH tunnel using tunnel and pass toggle `tunnel = True` via desired capabilities. LambdaTest Tunnel establishes a secure SSH protocol based tunnel that allows you in testing your locally hosted or privately hosted pages, even before they are live.

Refer our [LambdaTest Tunnel documentation](https://www.lambdatest.com/support/docs/testing-locally-hosted-pages/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample) for more information.

Here’s how you can establish LambdaTest Tunnel.

Download the binary file of:
* [LambdaTest Tunnel for Windows](https://downloads.lambdatest.com/tunnel/v3/windows/64bit/LT_Windows.zip)
* [LambdaTest Tunnel for macOS](https://downloads.lambdatest.com/tunnel/v3/mac/64bit/LT_Mac.zip)
* [LambdaTest Tunnel for Linux](https://downloads.lambdatest.com/tunnel/v3/linux/64bit/LT_Linux.zip)

Open command prompt and navigate to the binary folder.

Run the following command:

```bash
LT -user {user’s login email} -key {user’s access key}
```
So if your user name is lambdatest@example.com and key is 123456, the command would be:

```bash
LT -user lambdatest@example.com -key 123456
```
Once you are able to connect **LambdaTest Tunnel** successfully, you would just have to pass on tunnel capabilities in the code shown below :

**Tunnel Capability**

```java
DesiredCapabilities capabilities = new DesiredCapabilities();        
        capabilities.setCapability("tunnel", true);
```

## Tutorials 📙

Check out our latest tutorials on JUnit automation testing 👇

* [JUnit 5 vs TestNG: Choosing the Right Frameworks for Selenium Automation Testing](https://www.lambdatest.com/blog/junit-5-vs-testng/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [TestNG vs JUnit: Which Testing Framework Should You Choose?](https://www.lambdatest.com/blog/testng-vs-junit-which-testing-framework-should-you-choose/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [JUnit With Selenium](https://www.lambdatest.com/blog/automated-testing-with-junit-and-selenium-for-browser-compatibility/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [JUnit Environment Setup](https://www.lambdatest.com/blog/setup-junit-environment/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to Run JUnit Selenium Tests Using TestNG?](https://www.lambdatest.com/blog/test-example-junit-and-testng-in-selenium/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [[Complete JUnit 5 Mockito Tutorial for Unit Testing]](https://www.lambdatest.com/blog/junit5-mockito-tutorial/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [Parallel Testing with JUnit 5 and Selenium [Tutorial]](https://www.lambdatest.com/blog/parallel-testing-with-junit5-and-selenium/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to Execute JUnit 4 Tests with JUnit 5 [Tutorial]](https://www.lambdatest.com/blog/execute-junit4-tests-with-junit5/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to Run JUnit Tests from the Command Line?](https://www.lambdatest.com/blog/run-junit-from-command-line/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to Minimize Browsers in Selenium WebDriver Using JUnit?](https://www.lambdatest.com/blog/minimize-browsers-in-selenium-webdriver/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to use @RepeatedTest Annotations in JUnit 5?](https://www.lambdatest.com/blog/repeatedtest-annotation-in-junit-5/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [A Comprehensive Guide on JUnit 5 Extensions](https://www.lambdatest.com/blog/junit5-extensions/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to Run JUnit Selenium Tests Using TestNG?](https://www.lambdatest.com/blog/test-example-junit-and-testng-in-selenium/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [JUnit Parameterized Test for Selenium Automation with Examples](https://www.lambdatest.com/blog/junit-parameterized-test-selenium/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [JUnit Asserts with Examples](https://www.lambdatest.com/blog/junit-assertions-example-for-selenium-testing/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [Tutorial on JUnit Annotations with Examples](https://www.lambdatest.com/blog/tutorial-on-junit-annotations-in-selenium-with-examples/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [Automated Testing with JUnit and Selenium for Browser Compatibility](https://www.lambdatest.com/blog/automated-testing-with-junit-and-selenium-for-browser-compatibility/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)

For video tutorials on Selenium JUnit, please refer to our [JUnit Tutorial Playlist](https://www.youtube.com/playlist?list=PLZMWkkQEwOPn68qzCGJl07ZbnI7Ix5zKU). ▶️

Subscribe To Our [LambdaTest YouTube Channel 🔔](https://www.youtube.com/c/LambdaTest) and keep up-to-date on the latest video tutorial around software testing world.

## Documentation & Resources :books:

      
Visit the following links to learn more about LambdaTest's features, setup and tutorials around test automation, mobile app testing, responsive testing, and manual testing.

* [LambdaTest Documentation](https://www.lambdatest.com/support/docs/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [LambdaTest Blog](https://www.lambdatest.com/blog/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [LambdaTest Learning Hub](https://www.lambdatest.com/learning-hub/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)    

## LambdaTest Community :busts_in_silhouette:

The [LambdaTest Community](https://community.lambdatest.com/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample) allows people to interact with tech enthusiasts. Connect, ask questions, and learn from tech-savvy people. Discuss best practises in web development, testing, and DevOps with professionals from across the globe 🌎

## What's New At LambdaTest ❓

To stay updated with the latest features and product add-ons, visit [Changelog](https://changelog.lambdatest.com) 
      
## About LambdaTest

[LambdaTest](https://www.lambdatest.com/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample) is a leading test execution and orchestration platform that is fast, reliable, scalable, and secure. It allows users to run both manual and automated testing of web and mobile apps across 3000+ different browsers, operating systems, and real device combinations. Using LambdaTest, businesses can ensure quicker developer feedback and hence achieve faster go to market. Over 500 enterprises and 1 Million + users across 130+ countries rely on LambdaTest for their testing needs.    

### Features

* Run Selenium, Cypress, Puppeteer, Playwright, and Appium automation tests across 3000+ real desktop and mobile environments.
* Real-time cross browser testing on 3000+ environments.
* Test on Real device cloud
* Blazing fast test automation with HyperExecute
* Accelerate testing, shorten job times and get faster feedback on code changes with Test At Scale.
* Smart Visual Regression Testing on cloud
* 120+ third-party integrations with your favorite tool for CI/CD, Project Management, Codeless Automation, and more.
* Automated Screenshot testing across multiple browsers in a single click.
* Local testing of web and mobile apps.
* Online Accessibility Testing across 3000+ desktop and mobile browsers, browser versions, and operating systems.
* Geolocation testing of web and mobile apps across 53+ countries.
* LT Browser - for responsive testing across 50+ pre-installed mobile, tablets, desktop, and laptop viewports

    
[<img height="58" width="200" src="https://user-images.githubusercontent.com/70570645/171866795-52c11b49-0728-4229-b073-4b704209ddde.png">](https://accounts.lambdatest.com/register)

      
## We are here to help you :headphones:

* Got a query? we are available 24x7 to help. [Contact Us](mailto:support@lambdatest.com)
* For more info, visit - [LambdaTest](https://www.lambdatest.com/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
