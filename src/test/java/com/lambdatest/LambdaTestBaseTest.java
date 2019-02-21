package com.lambdatest;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(Parallelized.class)
public class LambdaTestBaseTest {
    public WebDriver driver;

    private static JSONArray envData;

    @Parameter(value = 0)
    public int taskID;

    /**
     * JUNIT iterator fetching caps from env pushing into queue
     *
     * @return
     * @throws Exception
     */
    @Parameters
    public static Iterable<? extends Object> data() throws Exception {
        List<Integer> taskIDs = new ArrayList<Integer>();

        if (System.getenv("LT_BROWSERS") != null) {
            JSONParser parser = new JSONParser();
            envData = (JSONArray) parser.parse(System.getenv("LT_BROWSERS"));

            for (int i = 0; i < envData.size(); i++) {
                taskIDs.add(i);
            }
        }else {
            JSONParser parser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
            URL resource = LambdaTestBaseTest.class.getResource("/config.json");
            Paths.get(resource.toURI()).toFile();



            envData = (JSONArray) parser.parse(
                    new FileReader(Paths.get(resource.toURI()).toFile()));//path to the JSON file.

            for (int i = 0; i < envData.size(); i++) {
                taskIDs.add(i);
            }
        }

        return taskIDs;
    }

    /**
     * Setting Capebilties and initiating driver for inherited classes
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {


        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Setting build name from env starts
        String buildName = "";
        if (System.getenv("LT_BUILD_NAME") != null) {
            buildName = System.getenv("LT_BUILD_NAME");
            capabilities.setCapability("build", buildName);

        }

        if (System.getenv("LT_BUILD_NUMBER") != null) {
            buildName += " - " + System.getenv("LT_BUILD_NUMBER");
            capabilities.setCapability("build", buildName);
        }
        //Setting build name from env starts ends


        //Setting capability for video  starts
        if (System.getProperty("video") != null && !System.getProperty("video").equalsIgnoreCase("false")) {
            capabilities.setCapability("video", true);
        }
        //Setting capability for video ends


        //Setting capability for network logs. enable network log capturing starts
        if (System.getProperty("network") != null && !System.getProperty("network").equalsIgnoreCase("false")) {
            capabilities.setCapability("network", true);
        }
        //Setting capability for network logs. enable network log capturing ends


        //Setting capability for visual starts
        if (System.getProperty("visual") != null && !System.getProperty("visual").equalsIgnoreCase("false")) {
            capabilities.setCapability("visual", true);
        }
        //Setting capability for visual ends

        //Setting capability for console starts
        if (System.getProperty("console") != null && !System.getProperty("console").equalsIgnoreCase("false")) {
            capabilities.setCapability("console", true);
        }
        //Setting capability for console ends


        //Setting local tunnel identifiers starts
        if (System.getenv("LT_TUNNEL_NAME") != null) {
            capabilities.setCapability("tunnel", true);
            capabilities.setCapability("tunnelIdentifier", System.getenv("LT_TUNNEL_NAME"));
        }
        //Setting local tunnel identifiers ends




        //Setting caps for platform, browser, version based on env variables starts

        Map<String, String> envCapabilities = (Map<String, String>) envData.get(taskID);
        capabilities.setCapability("platform", envCapabilities.get("operatingSystem"));
        capabilities.setCapability("browserName", envCapabilities.get("browserName"));
        capabilities.setCapability("version", envCapabilities.get("browserVersion"));
        capabilities.setCapability("resolution", envCapabilities.get("resolution"));

        //Setting caps for platform, browser, version based on env variables ends


        //Setting test name starts
        if (System.getenv("LT_TEST_NAME") != null) {
            capabilities.setCapability("name", System.getenv("LT_TEST_NAME"));
        }else{
            String  testName = envCapabilities.get("operatingSystem")+"-"+envCapabilities.get("browserName")+"-"+envCapabilities.get("browserVersion")+taskID;
            capabilities.setCapability("name", testName);
        }
        //Setting test name starts ends



        String gridUrl = System.getenv("LT_GRID_URL");
        if (gridUrl == null) {
            throw new Exception("Missing LT GRID URL value :" + System.getProperty("LT_GRID_URL"));
        }

        System.out.printf("Initiating driver with caps %s grid url %s \n", capabilities.toString(), gridUrl);
        driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
//        driver = new RemoteWebDriver(new URL("https://webhook.site/aa685ef0-0f11-432e-894f-eaa143bac12d"), capabilities);

    }

    /**
     * Destroying driver object
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
