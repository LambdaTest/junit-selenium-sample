package com.smartuiPdf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.lambdatest.SmartUIConfig;
import io.github.lambdatest.SmartUIPdf;
import io.github.lambdatest.models.FormattedResults;
import org.junit.Before;
import org.junit.Test;

public class SmartuiPdfLocalTest {

  String projectToken;

  @Before
  public void setup() {
    // Get LambdaTest credentials from environment variables
    String username = (System.getenv("LT_USERNAME") != null) ? System.getenv("LT_USERNAME")
      : "YOUR LAMBDA TEST USERNAME";
    String accessKey = (System.getenv("LT_ACCESS_KEY") != null) ? System.getenv("LT_ACCESS_KEY")
      : "YOUR LAMBDA TEST ACCESS KEY";
    projectToken = (System.getenv("PROJECT_TOKEN") != null) ? System.getenv("PROJECT_TOKEN")
      : "YOUR PROJECT TOKEN";

    if (username == null || accessKey == null || projectToken == null) {
      throw new RuntimeException("Please set LT_USERNAME, LT_ACCESS_KEY and PROJECT_TOKEN environment variables");
    }
  }

  @Test
  public void pdfTest() throws Exception {

    SmartUIConfig config = new SmartUIConfig()
      .withProjectToken(projectToken)
      .withFetchResult(true);

    SmartUIPdf pdfUploader = new SmartUIPdf(config);

    String pdfPath = "src/test/resources/samplePdfs/FY-23";
    pdfUploader.uploadPDF(pdfPath);

    Thread.sleep(5000);

    pdfPath = "src/test/resources/samplePdfs/FY-24";
    FormattedResults result = pdfUploader.uploadPDF(pdfPath);
    
    // Print the entire result as formatted JSON
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonResult = gson.toJson(result);
    System.out.println("=== COMPLETE RESULT AS JSON ===");
    System.out.println(jsonResult);
    System.out.println("=== END OF JSON RESULT ===");
  }

}
