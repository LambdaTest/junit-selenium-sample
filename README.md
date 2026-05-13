# Run Selenium Tests With JUnit — TestMu AI (Formerly LambdaTest)

![image](https://user-images.githubusercontent.com/70570645/171432631-dcc31b10-6590-4877-98c0-4ac702fbd441.png)

<p align="center">
  <a href="https://www.testmuai.com/blog/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample" target="_bank">Blog</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.testmuai.com/support/docs/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample" target="_bank">Docs</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.testmuai.com/learning-hub/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample" target="_bank">Learning Hub</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.testmuai.com/newsletter/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample" target="_bank">Newsletter</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.testmuai.com/certifications/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample" target="_bank">Certifications</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.youtube.com/@TestMuAI" target="_bank">YouTube</a>
</p>
&emsp;
&emsp;
&emsp;

*Learn how to use JUnit framework to configure and run your Java automation testing scripts on the TestMu AI platform*

[<img height="58" width="200" src="https://user-images.githubusercontent.com/70570645/171866795-52c11b49-0728-4229-b073-4b704209ddde.png">](https://accounts.lambdatest.com/register?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)


## Table Of Contents

* [Pre-requisites](#pre-requisites)
* [Run Your First Test](#run-your-first-test)
* [Parallel Testing With JUnit](#run-parallel-tests-using-junit)
* [Local Testing With JUnit](#testing-locally-hosted-or-privately-hosted-projects)

## Pre-requisites

Before you can start performing Java automation testing with Selenium, you would need to:

- Install the latest **Java development environment** i.e. **JDK 1.6** or higher. We recommend using the latest version.

- Download the latest **Selenium Client** and its **WebDriver bindings** from the [official website](https://www.selenium.dev/downloads/). Latest versions of Selenium Client and WebDriver are ideal for running your automation script on TestMu AI Selenium cloud grid.

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

**Step 1:** Clone the TestMu AI’s JUnit-Selenium-Sample repository and navigate to the code directory as shown below:

```bash
git clone https://github.com/LambdaTest/junit-selenium-sample
cd junit-selenium-sample
```

You may also want to run the command below to check for outdated dependencies.

```bash
mvn versions:display-dependency-updates
```

### Setting Up Your Authentication

Make sure you have your TestMu AI credentials with you to run test automation scripts. You can get these credentials from the [TestMu AI Automation Dashboard](https://automation.lambdatest.com/build?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample) or by your [TestMu AI Profile](https://accounts.lambdatest.com/login?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample).

**Step 2:** Set TestMu AI **Username** and **Access Key** in environment variables.

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

**Step 3:** In the test script, you need to update your test capabilities. In this code, we are passing browser, browser version, and operating system information, along with TestMu AI Selenium grid capabilities via capabilities object. The capabilities object in the above code are defined as:

```java
ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setCapability("platformName", "Windows 10");
        browserOptions.setCapability("browserVersion", "latest");

        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "JUnitSampleTestApp");
        ltOptions.put("name", "JUnitSampleTest");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("project", "");  //Enter Project name here
        ltOptions.put("smartUI.project", "");  //Enter smartUI Project name here
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "junit-junit");
        ltOptions.put("visual", true); // To enable step by step screenshot
        ltOptions.put("network", true); // To enable network logs
        ltOptions.put("video", true); // To enable video recording
        ltOptions.put("console", true); // To capture console logs
        browserOptions.setCapability("LT:Options", ltOptions);
```

You can generate capabilities for your test requirements with the help of our inbuilt [Desired Capability Generator](https://www.testmuai.com/capabilities-generator/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample).

### Executing the Test

**Step 4:** The tests can be executed in the terminal using the following command.

```bash
mvn test -P single
```

Your test results would be displayed on the test console (or command-line interface if you are using terminal/cmd) and on [TestMu AI automation dashboard](https://automation.lambdatest.com/build). 

## Run Parallel Tests Using JUnit

Check out the [Parallelized.java](https://github.com/LambdaTest/junit-selenium-sample/blob/master/src/test/java/com/lambdatest/Parallelized.java) class we have used for running our Parallel Tests using JUnit.


Check out the [JUnitConcurrentTodo.java](https://github.com/LambdaTest/junit-selenium-sample/blob/master/src/test/java/com/lambdatest/JUnitConcurrentTodo.java) file for executing parallel test using JUnit automation framework.

### Executing Parallel Tests Using JUnit

To run parallel tests using **JUnit**, we would have to execute the below command in the terminal:

```bash
mvn test -P parallel
```

## Testing Locally Hosted Or Privately Hosted Projects

You can test your locally hosted or privately hosted projects with TestMu AI Selenium grid using TestMu AI Tunnel. All you would have to do is set up an SSH tunnel using tunnel and pass toggle `tunnel = True` via desired capabilities. TestMu AI Tunnel establishes a secure SSH protocol based tunnel that allows you in testing your locally hosted or privately hosted pages, even before they are live.

Refer our [TestMu AI Tunnel documentation](https://www.testmuai.com/support/docs/testing-locally-hosted-pages/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample) for more information.

Here’s how you can establish TestMu AI Tunnel.

Download the binary file of:
* [TestMu AI Tunnel for Windows](https://downloads.lambdatest.com/tunnel/v3/windows/64bit/LT_Windows.zip)
* [TestMu AI Tunnel for macOS](https://downloads.lambdatest.com/tunnel/v3/mac/64bit/LT_Mac.zip)
* [TestMu AI Tunnel for Linux](https://downloads.lambdatest.com/tunnel/v3/linux/64bit/LT_Linux.zip)

Open command prompt and navigate to the binary folder.

Run the following command:

```bash
LT -user {user’s login email} -key {user’s access key}
```
So if your user name is lambdatest@example.com and key is 123456, the command would be:

```bash
LT -user lambdatest@example.com -key 123456
```
Once you are able to connect **TestMu AI Tunnel** successfully, you would just have to pass on tunnel capabilities in the code shown below :

**Tunnel Capability**

```java
ChromeOptions browserOptions = new ChromeOptions();         
        browserOptions.setCapability("platformName", "Windows 10");
        browserOptions.setCapability("browserVersion", "latest");

        Map<String, Object> ltOptions = new HashMap<>();
         ltOptions.put("tunnel", true);
```

### Run your First SmartUI PDF Local Test
1. Clone the Java-Selenium-Sample repository. 
```
git clone https://github.com/LambdaTest/java-selenium-sample.git
```
2. Next get into Java-Selenium-Sample folder, and import Lamabdatest SmartUI Credentials. You can get these from lambdatest automation dashboard.
   <p align="center">
   <b>For Linux/macOS:</b>:
 
```
export LT_USERNAME="YOUR_USERNAME"
export LT_ACCESS_KEY="YOUR ACCESS KEY"
export PROJECT_TOKEN="YOUR PROJECT TOKEN"
```
<p align="center">
   <b>For Windows:</b>

```
set LT_USERNAME="YOUR_USERNAME"
set LT_ACCESS_KEY="YOUR ACCESS KEY"
set PROJECT_TOKEN="YOUR PROJECT TOKEN"
```
Step 3. Run SmartUI PDF Local test.
```
mvn test -P smartuiPdfLocal
```

### Run your First SmartUI PDF Cloud Test
1. Clone the Java-Selenium-Sample repository. 
```
git clone https://github.com/LambdaTest/java-selenium-sample.git
```
2. Next get into Java-Selenium-Sample folder, and import Lamabdatest SmartUI Credentials. You can get these from lambdatest automation dashboard.
   <p align="center">
   <b>For Linux/macOS:</b>:
 
```
export LT_USERNAME="YOUR_USERNAME"
export LT_ACCESS_KEY="YOUR ACCESS KEY"
export PROJECT_TOKEN="YOUR PROJECT TOKEN"
```
<p align="center">
   <b>For Windows:</b>

```
set LT_USERNAME="YOUR_USERNAME"
set LT_ACCESS_KEY="YOUR ACCESS KEY"
set PROJECT_TOKEN="YOUR PROJECT TOKEN"
```
Step 3. Run SmartUI PDF Local test.
```
mvn test -P smartuiPdfCloud
```

## Tutorials 📙

Check out our latest tutorials on JUnit automation testing 👇

* [JUnit 5 vs TestNG: Choosing the Right Frameworks for Selenium Automation Testing](https://www.testmuai.com/blog/junit-5-vs-testng/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [TestNG vs JUnit: Which Testing Framework Should You Choose?](https://www.testmuai.com/blog/testng-vs-junit-which-testing-framework-should-you-choose/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [JUnit With Selenium](https://www.testmuai.com/blog/automated-testing-with-junit-and-selenium-for-browser-compatibility/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [JUnit Environment Setup](https://www.testmuai.com/blog/setup-junit-environment/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to Run JUnit Selenium Tests Using TestNG?](https://www.testmuai.com/blog/test-example-junit-and-testng-in-selenium/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [[Complete JUnit 5 Mockito Tutorial for Unit Testing]](https://www.testmuai.com/blog/junit5-mockito-tutorial/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [Parallel Testing with JUnit 5 and Selenium [Tutorial]](https://www.testmuai.com/blog/parallel-testing-with-junit5-and-selenium/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to Execute JUnit 4 Tests with JUnit 5 [Tutorial]](https://www.testmuai.com/blog/execute-junit4-tests-with-junit5/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to Run JUnit Tests from the Command Line?](https://www.testmuai.com/blog/run-junit-from-command-line/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to Minimize Browsers in Selenium WebDriver Using JUnit?](https://www.testmuai.com/blog/minimize-browsers-in-selenium-webdriver/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to use @RepeatedTest Annotations in JUnit 5?](https://www.testmuai.com/blog/repeatedtest-annotation-in-junit-5/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [A Comprehensive Guide on JUnit 5 Extensions](https://www.testmuai.com/blog/junit5-extensions/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [How to Run JUnit Selenium Tests Using TestNG?](https://www.testmuai.com/blog/test-example-junit-and-testng-in-selenium/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [JUnit Parameterized Test for Selenium Automation with Examples](https://www.testmuai.com/blog/junit-parameterized-test-selenium/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [JUnit Asserts with Examples](https://www.testmuai.com/blog/junit-assertions-example-for-selenium-testing/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [Tutorial on JUnit Annotations with Examples](https://www.testmuai.com/blog/tutorial-on-junit-annotations-in-selenium-with-examples/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [Automated Testing with JUnit and Selenium for Browser Compatibility](https://www.testmuai.com/blog/automated-testing-with-junit-and-selenium-for-browser-compatibility/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)

For video tutorials on Selenium JUnit, please refer to our [JUnit Tutorial Playlist](https://www.youtube.com/playlist?list=PLZMWkkQEwOPn68qzCGJl07ZbnI7Ix5zKU). ▶️

Subscribe To Our [TestMu AI YouTube Channel 🔔](https://www.youtube.com/@TestMuAI) and keep up-to-date on the latest video tutorial around software testing world.

## Documentation & Resources :books:

      
Visit the following links to learn more about TestMu AI's features, setup and tutorials around test automation, mobile app testing, responsive testing, and manual testing.

* [TestMu AI Documentation](https://www.testmuai.com/support/docs/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [TestMu AI Blog](https://www.testmuai.com/blog/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)
* [TestMu AI Learning Hub](https://www.testmuai.com/learning-hub/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample)    

## TestMu AI Community :busts_in_silhouette:

The [TestMu AI Community](https://community.testmuai.com/?utm_source=github&utm_medium=repo&utm_campaign=junit-selenium-sample) allows people to interact with tech enthusiasts. Connect, ask questions, and learn from tech-savvy people. Discuss best practises in web development, testing, and DevOps with professionals from across the globe 🌎

## What's New At TestMu AI ❓

To stay updated with the latest features and product add-ons, visit [Changelog](https://changelog.lambdatest.com)

## 🚀 LambdaTest is Now TestMu AI

👋 Welcome to TestMu AI, the next evolution of LambdaTest. As of January 2026, [LambdaTest is Now TestMu AI](https://www.testmuai.com/lambdatest-is-now-testmuai/) - we have evolved from a cross-browser testing cloud into a unified, AI-native quality engineering platform designed for the modern DevOps era.

Whether you have been part of the LambdaTest community for years or are just discovering TestMu AI, our mission remains the same: to help you ship faster with high-scale test execution, autonomous testing, and deep quality analytics.

### 🔄 Our Rebrand Journey

In 2017, we introduced LambdaTest with a clear mission: to become the world's most trusted cloud testing platform. We built a scalable, high-performance test cloud that eliminated flakiness, improved developer feedback cycles, and accelerated release velocity for teams worldwide.

As LambdaTest grew, we expanded the platform into Test Intelligence, Visual Regression Testing, Accessibility Testing, API Testing, and Performance Testing, covering the entire testing lifecycle. These capabilities enabled teams to test any stack, on any technology, at enterprise scale.

Over time, we rebuilt the architecture to be AI-native from the ground up. What began as LambdaTest's high-performance testing cloud has now evolved into TestMu AI, an AI-native, multi-agent platform redefining modern quality engineering.

We chose the name TestMu AI to reflect our shift towards intelligent, autonomous testing. While our identity has changed, our core technology and commitment to the testing community stay the same.

👉 Find [LambdaTest's New Home](https://www.testmuai.com/).

### 🔭 Explore TestMu AI

The same infrastructure LambdaTest customers relied on, now delivered through autonomous AI agents.

- [KaneAI](https://www.testmuai.com/kane-ai/)
- [Agent-to-Agent Testing](https://www.testmuai.com/agent-to-agent-testing/)
- [HyperExecute](https://www.testmuai.com/hyperexecute/)
- [Real Device Cloud](https://www.testmuai.com/real-device-cloud/)
- [Pricing](https://www.testmuai.com/pricing/)
- [Documentation](https://www.testmuai.com/support/docs/)