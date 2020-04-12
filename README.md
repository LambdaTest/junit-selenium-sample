![LambdaTest Logo](https://www.lambdatest.com/static/images/logo.svg)
---

# Run Junit Script On LambdaTest Selenium Grid
A Sample Junit app to run selenium automation tests on [LambdaTest](https://www.lambdatest.com/) grid. 

### Prerequisites
- JDK should be installed.
- Make sure maven is installed in your system. Tested on mvn==3.6.0.
- Make sure JUnit should be installed.

### Configuration steps

- Set envirnment variables.
- Put Lambdatest User name.
- Put Lambdatest Access Key.
- Put gridURL = "@hub.lambdatest.com/wd/hub
- Get the desired capability from here https://www.lambdatest.com/capabilities-generator/

  DesiredCapabilities capabilities = new DesiredCapabilities();
  
  capabilities.setCapability("build", "your build name");
  
  capabilities.setCapability("name", "your test name");
  
  capabilities.setCapability("platform", "macOS High Sierra");
  
  capabilities.setCapability("browserName", "Firefox");
  
  capabilities.setCapability("version","64.0");
  
  capabilities.setCapability("selenium_version","3.13.0");
  
  capabilities.setCapability("visual",true);
  
  capabilities.setCapability("firefox.driver",v0.23.0);


### Installation

- Go to project directory
- Get the project code from here https://github.com/LambdaTest/junit-selenium-sample/tree/master/src/test/java/com/lambdatestS
- Install JUnit dependencies in POM.xml file.
 
 < dependency >
 
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>  
    
< /dependency >


### Run tests

- Open a terminal window.
- Change directory to your root project.
- Run the below mvn command.

   mvn test -P single


 ## About LambdaTest
[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your [selenium automation testing](https://www.lambdatest.com/selenium-automation) to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.

## Resource Documents and links.

- https://www.lambdatest.com/support/docs/junit-with-selenium-running-junit-automation-scripts-on-lambdatest-selenium-grid/
- https://github.com/LambdaTest/junit-selenium-sample



