# JUnit with Selenium
***

![junit](https://user-images.githubusercontent.com/70570645/170549180-f34251ab-02da-42d3-811d-d7c5b81bafe5.png)

*Learn how to configure and run your Java automation testing scripts on [LambdaTest](https://www.lambdatest.com/selenium-automation) platform using **JUnit**.*

## Table of Contents:
***

* [Pre-requisites](#pre-requisites)
* [Run Your First Test](#run-your-first-test)
* [Parallel Testing With JUnit](#run-parallel-tests-using-junit)
* [Local Testing With JUnit](#testing-locally-hosted-or-privately-hosted-projects)
* [Additional Links](#additional-links)



## Pre-requisites
---

Before you can start performing Java automation testing with Selenium, you would need to:

- Install the latest **Java development environment** i.e. **JDK 1.6** or higher. We recommend using the latest version.

- Download the latest **Selenium Client** and its **WebDriver bindings** from the [official website](https://www.selenium.dev/downloads/). Latest versions of Selenium Client and WebDriver are ideal for running your automation script on LambdaTest Selenium cloud grid.

- Install **Maven** which supports **JUnit** framework out of the box. **Maven** can be downloaded and installed following the steps from [the official website](https://maven.apache.org/). Maven can also be installed easily on **Linux/MacOS** using [**Homebrew**](https://brew.sh/) package manager.

- You would have to add the following maven dependency to your `pom.xml` file if working on your local project.
  ```xml
  <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
  </dependency>
  ```

### Cloning Repo and Installing Dependencies ⬇️
---

**Step 1:** Clone the LambdaTest’s [junit-selenium-sample](https://github.com/LambdaTest/junit-selenium-sample) repository and navigate to the code directory as shown below:

```bash
git clone https://github.com/LambdaTest/junit-selenium-sample
cd junit-selenium-sample
```

You may also want to run the command below to check for outdated dependencies.

```bash
mvn versions:display-dependency-updates
```

### Setting up your Authentication ⚙️
---

Make sure you have your LambdaTest credentials with you to run test automation scripts on LambdaTest Selenium Grid. You can obtain these credentials from the [LambdaTest Automation Dashboard](https://automation.lambdatest.com/build) or through [LambdaTest Profile](https://accounts.lambdatest.com/login).

**Step 2:** Set LambdaTest **Username** and **Access Key** in environment variables.

**Linux / macOS**

```
export LT_USERNAME="YOUR_LAMBDATEST_USERNAME" \
export LT_ACCESS_KEY="YOUR_LAMBDATEST_ACCESS_KEY"
```

**Windows**

```
set LT_USERNAME="YOUR_LAMBDATEST_USERNAME" `
set LT_ACCESS_KEY="YOUR_LAMBDATEST_ACCESS_KEY"
```

## Run Your First Test
---

**Test Scenario**: Check out [JUnitTodo.java](https://github.com/LambdaTest/junit-selenium-sample/blob/master/src/test/java/com/lambdatest/JUnitTodo.java) file to view the sample test case.

### Configuring your Test Capabilities
---

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

>**Note**: You can generate capabilities for your test requirements with the help of our inbuilt :link: **[Capabilities Generator Tool](https://www.lambdatest.com/capabilities-generator/)**.

### Executing the Test
---

**Step 4:** The tests can be executed in the terminal using the following command.

```bash
mvn test -P single
```

>**Note**: Your test results would be displayed on the test console (or command-line interface if you are using terminal/cmd) and on [LambdaTest automation dashboard](https://automation.lambdatest.com/build). LambdaTest Automation Dashboard will help you view all your text logs, screenshots and video recording for your entire automation tests.

## Run Parallel Tests Using JUnit
---

### Setting up the Parallel Environment
---

**Test Scenario**: 

Check out the [Parallelized.java](https://github.com/LambdaTest/junit-selenium-sample/blob/master/src/test/java/com/lambdatest/Parallelized.java) file for the Parallelized class that is used for running our Parallel Tests using JUnit.

You can visit [JUnitConcurrentTodo.java](https://github.com/LambdaTest/junit-selenium-sample/blob/master/src/test/java/com/lambdatest/JUnitConcurrentTodo.java) file for executing parallel test using JUnit automation framework.


### Executing Parallel Tests using JUnit
---

To run parallel tests using **JUnit**, we would have to execute the below command in the terminal:

```bash
mvn test -P parallel
```

>**Note**: Your test results would be displayed on the test console (or command-line interface if you are using terminal/cmd) and on [LambdaTest automation dashboard](https://automation.lambdatest.com/build).

## Testing Locally Hosted or Privately Hosted Projects
---

You can test your locally hosted or privately hosted projects using LambdaTest Tunnel. All you would have to do is set up an SSH tunnel using LambdaTest Tunnel app and pass toggle `tunnel = True` via desired capabilities. LambdaTest Tunnel establishes a secure SSH protocol based tunnel that allows you in testing your locally hosted or privately hosted pages, even before they are made live.

for more information, refer to [LambdaTest Tunnel documentation](https://www.lambdatest.com/support/docs/testing-locally-hosted-pages/).

Here’s how you can establish LambdaTest Tunnel.

- [LambdaTest Tunnel for Windows](https://downloads.lambdatest.com/tunnel/windows/64bit/LT_Windows.zip)
- [LambdaTest Tunnel for Mac](https://downloads.lambdatest.com/tunnel/mac/64bit/LT_Mac.zip)
- [LambdaTest Tunnel for Linux](https://downloads.lambdatest.com/tunnel/linux/64bit/LT_Linux.zip)

Open command prompt and navigate to the binary folder.

Run the following command:

```bash
./LT -user {user’s login email} -key {user’s access key}
```

So if your user name is **lambdatest@example.com**, the command would be:

```
./LT -user lambdatest@example.com -key ${ YOUR_LAMBDATEST_ACCESS_KEY()
```

Once you are able to connect **LambdaTest Tunnel** successfully, you would just have to pass on tunnel capabilities in the code as shown:

```
DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("tunnel", true);
```

## Additional Links 🔗
---

- [Advanced Configuration for Capabilities](https://www.lambdatest.com/support/docs/selenium-automation-capabilities/)
- [How to test locally hosted apps](https://www.lambdatest.com/support/docs/testing-locally-hosted-pages/)
- [How to integrate LambdaTest with CI/CD](https://www.lambdatest.com/support/docs/integrations-with-ci-cd-tools/)

## LambdaTest Community :busts_in_silhouette:
***
The [LambdaTest Community](https://community.lambdatest.com/) allows people to interact with tech enthusiasts. Connect, ask questions, and learn from tech-savvy people. Discuss best practises in web development, testing, and DevOps with professionals from across the globe.

## Documentation & Resources :books:
***
      
If you want to learn more about the LambdaTest's features, setup, and usage, visit the [LambdaTest documentation](https://www.lambdatest.com/support/docs/). You can also find in-depth tutorials around test automation, mobile app testing, responsive testing, manual testing on [LambdaTest Blog](https://www.lambdatest.com/blog/) and [LambdaTest Learning Hub](https://www.lambdatest.com/learning-hub/).     
      
 ## About LambdaTest
***
[LambdaTest](https://www.lambdatest.com) is a leading test execution and orchestration platform that is fast, reliable, scalable, and secure. It allows users to run both manual and automated testing of web and mobile apps across 3000+ different browsers, operating systems, and real device combinations. Using LambdaTest, businesses can ensure quicker developer feedback and hence achieve faster go to market. Over 500 enterprises and 1 Million + users across 130+ countries rely on LambdaTest for their testing needs.

[<img height="70" src="https://user-images.githubusercontent.com/70570645/169649126-ed61f6de-49b5-4593-80cf-3391ca40d665.PNG">](https://accounts.lambdatest.com/register)
      
## We are here to help you :headphones:
***
* Got a query? we are available 24x7 to help. [Contact Us](mailto:support@lambdatest.com)
* For more info, visit - https://www.lambdatest.com

