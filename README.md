![LambdaTest Logo](https://www.lambdatest.com/static/images/logo.svg)
---

# junit-selenium-sample
A Sample Junit app to run selenium automation tests on [LambdaTest](https://www.lambdatest.com/) grid. 

### Prerequisites
- Make sure maven is installed in your system. Tested on mvn==3.6.0


### Configuration steps
- Set env varibles. 
```bash
export LT_GRID_URL=https://{username}:{accessToken}@hub.lambdatest.com/wd/hub
```
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
[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. All test data generated during testing including Selenium command logs, screenshots generated in testing, video logs, selenium logs, network logs, console logs, and metadata logs can be extracted using [LambdaTest automation APIs](https://www.lambdatest.com/support/docs/api-doc/). This data can then be used for creating custom reports.
