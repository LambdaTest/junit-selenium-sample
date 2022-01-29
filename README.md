# JUnit Selenium tutorial

![MSTest](https://www.lambdatest.com/resources/images/meta/With-Junit.jpg)

## Prerequisites

To run your test script using JUnit with Selenium, first you need to setup the environment.

1. Install JDK 1.6 or higher version
2. Latest Selenium client and it’s WebDriver Bindings.
3. Download Maven([Windows](https://maven.apache.org/download.cgi), [Linux](https://maven.apache.org/install.html), and [Mac](https://maven.apache.org/install.html)) or Ant. Maven supports JUnit out of the box. You would just have to define Selenium dependencies in it’s project object model file or pom.xml file.
4. Optional - To test your locally or privately hosted files, you need LambdaTest Tunnel binary file.

## Steps to Run your First Test

Step 1. Clone the Python-Pytest-Selenium Repository.

```
git clone https://github.com/LambdaTest/junit-selenium-sample.git
```

Step 2. Inside Junit-selenium-sample folder, export the Lambda-test Credentials. You can get these from your automation dashboard.

<p align="center">
   <b>For Linux/macOS:</b>
   
```
export LT_USERNAME="YOUR_USERNAME"
export LT_ACCESS_KEY="YOUR ACCESS KEY"
```
<p align="center">
   <b>For Windows:</b>
```
set LT_USERNAME="YOUR_USERNAME"
set LT_ACCESS_KEY="YOUR ACCESS KEY"
```
Step 3. To run your First Test.
```
mvn test -P single
```
Step 4. To run Parallel Test.
```
mvn test -P parallel
```
## See the Results
You can see the results of the test on Lambdatest [Automation Dashboard](https://automation.lambdatest.com/build)
![Dashboard](https://github.com/LambdaTest/junit-selenium-sample/dashboard)

## Testing Locally Hosted or Privately Hosted Projects

To help you perform cross browser testing of your locally stored web pages, LambdaTest provides an SSH(Secure Shell) tunnel connection with the name Lambda Tunnel. With Lambda Tunnel, you can test your locally hosted files before you make them live over the internet. You could even perform cross browser testing from different IP addresses belonging to various geographic locations. You can also use LambdaTest Tunnel to test web-apps and websites that are permissible inside your corporate firewall.

- Set tunnel value to True in test capabilities
  > OS specific instructions to download and setup tunnel binary can be found at the following links.
  >
  > - [Windows](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Windows)
  > - [Mac](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+MacOS)
  > - [Linux](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Linux)
  >   After setting tunnel you can also see the active tunnel in our LambdaTest dashboard:
  >   ![tunnel active](https://github.com/LambdaTest/Robot-Selenium-Sample/blob/master/tutorial-images/tn.PNG)

## About LambdaTest

[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your [selenium automation testing](https://www.lambdatest.com/selenium-automation) to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.

# JUnit Selenium tutorial

![MSTest](https://www.lambdatest.com/resources/images/meta/With-Junit.jpg)

## Prerequisites

To run your test script using JUnit with Selenium, first you need to setup the environment.

1. Install JDK 1.6 or higher version
2. Latest Selenium client and it’s WebDriver Bindings.
3. Download Maven([Windows](https://maven.apache.org/download.cgi), [Linux](https://maven.apache.org/install.html), and [Mac](https://maven.apache.org/install.html)) or Ant. Maven supports JUnit out of the box. You would just have to define Selenium dependencies in it’s project object model file or pom.xml file.
4. Optional - To test your locally or privately hosted files, you need LambdaTest Tunnel binary file.

## Steps to Run your First Test

Step 1. Clone the Python-Pytest-Selenium Repository.

```
git clone https://github.com/LambdaTest/junit-selenium-sample.git
```

Step 2. Inside Junit-selenium-sample folder, export the Lambda-test Credentials. You can get these from your automation dashboard.

<p align="center">
   <b>For Linux/macOS:</b>
   
```
export LT_USERNAME="YOUR_USERNAME"
export LT_ACCESS_KEY="YOUR ACCESS KEY"
```
<p align="center">
   <b>For Windows:</b>
```
set LT_USERNAME="YOUR_USERNAME"
set LT_ACCESS_KEY="YOUR ACCESS KEY"
```
Step 3. To run your First Test.
```
mvn test -P single
```
Step 4. To run Parallel Test.
```
mvn test -P parallel
```
## See the Results
You can see the results of the test on Lambdatest [Automation Dashboard](https://automation.lambdatest.com/build)
![Dashboard](https://github.com/LambdaTest/junit-selenium-sample/dashboard)

## Testing Locally Hosted or Privately Hosted Projects

To help you perform cross browser testing of your locally stored web pages, LambdaTest provides an SSH(Secure Shell) tunnel connection with the name Lambda Tunnel. With Lambda Tunnel, you can test your locally hosted files before you make them live over the internet. You could even perform cross browser testing from different IP addresses belonging to various geographic locations. You can also use LambdaTest Tunnel to test web-apps and websites that are permissible inside your corporate firewall.

- Set tunnel value to True in test capabilities
  > OS specific instructions to download and setup tunnel binary can be found at the following links.
  >
  > - [Windows](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Windows)
  > - [Mac](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+MacOS)
  > - [Linux](https://www.lambdatest.com/support/docs/display/TD/Local+Testing+For+Linux)
  >   After setting tunnel you can also see the active tunnel in our LambdaTest dashboard:
  >   ![tunnel active](https://github.com/LambdaTest/Robot-Selenium-Sample/blob/master/tutorial-images/tn.PNG)

## About LambdaTest

[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your [selenium automation testing](https://www.lambdatest.com/selenium-automation) to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.
