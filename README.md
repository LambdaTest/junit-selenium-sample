![LambdaTest Logo](https://www.lambdatest.com/static/images/logo.svg)
---

# Run Junit Script On LambdaTest Selenium Grid
A Sample Junit app to run selenium automation tests on [LambdaTest](https://www.lambdatest.com/) grid. 

### Prerequisites
- Make sure maven is installed in your system. Tested on mvn==3.6.0


### Configuration steps
LambdaTest Credentials
Set LambdaTest username and access key in environment variables. It can be obtained from LambdaTest Automation Dashboard
example:

* For linux/mac

export LT_USERNAME="YOUR_USERNAME"

export LT_ACCESS_KEY="YOUR ACCESS KEY"

* For Windows

set LT_USERNAME="YOUR_USERNAME"

set LT_ACCESS_KEY="YOUR ACCESS KEY"


- Desired capabilities list can be found at location `test/resources/config.json`.

### Installation
- Go to project dir
- Install dependencies
```bash
# setup project dependencies
mvn clean install
```


### Run tests
```bash
mvn surefire:test "-Dtest=SimpleTest" "-Djunit.parallel.threads=4" "-Dnetwork=true" "-Dvideo=true" "-Dvisual=true" "-Dconsole=true"
```
 ## About LambdaTest
[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your [selenium automation testing](https://www.lambdatest.com/selenium-automation) to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.
