![LambdaTest Logo](https://www.lambdatest.com/static/images/logo.svg)
---

# junit-selenium-sample
A Sample Junit app to run selenium automation tests on LambdaTest grid. 

### Prerequisites
- Make sure maven is installed in your system. Tested on mvn==3.6.0


### Configuration steps
- Set env varibles. 
    ```bash
        export LT_GRID_URL=https://{username}:{accessToken}@hub.lambdatest.com/wd/hub
    ```
- Desired capabilities list can be found at location `test->resources->config.json`.

### Installation
```bash
# setup project dependencies
mvn clean install
```


### Run tests
```bash
php artisan dusk
```

### Generate test cases
- Change directory to project root `cd /your/project`
- Execute `php artisan dusk:make {test case name}` 
    e.g:
    ```bash
    php artisan dusk:make TodoTest
    ```
### Note
Our sample test case can be found in `tests/Browser/TodoTest.php` file. It navigates to our sample to-do app.
