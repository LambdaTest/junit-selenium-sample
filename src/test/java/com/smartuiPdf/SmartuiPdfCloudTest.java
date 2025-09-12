package com.smartuiPdf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.lambdatest.SmartUIConfig;
import io.github.lambdatest.SmartUIPdf;
import io.github.lambdatest.models.FormattedResults;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

public class SmartuiPdfCloudTest {

  private WebDriver driver;
  private String projectToken;
  private final String downloadDir = System.getProperty("user.dir") + "/src/test/resources/downloadedPdfs";

  @Before
  public void setup() throws URISyntaxException, MalformedURLException {
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

    // Create download directory
    File downloadDirFile = new File(downloadDir);
    if (!downloadDirFile.exists()) {
      downloadDirFile.mkdirs();
    }

    ChromeOptions browserOptions = new ChromeOptions();
    browserOptions.setCapability("PlatformName","Windows 10");
    HashMap<String, Object> ltOptions = new HashMap<String, Object>();
    ltOptions.put("project", "SmartUI PDF Sample");
    ltOptions.put("w3c", true);
    ltOptions.put("plugin", "java-junit");
    browserOptions.setCapability("LT:Options", ltOptions);

    // Create remote web driver instance
    String hubURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
    driver = new RemoteWebDriver(new URI(hubURL).toURL(), browserOptions);
  }

  @Test
  public void pdfCloudTest() throws InterruptedException, Exception {
    // Open a URL for downloading the pdf
    driver.get("https://www.lambdatest.com/selenium-playground/download-file-demo");

    // Click on the download button
    WebElement downloadButton = driver.findElement(By.xpath("//button[text()='Download File']"));
    downloadButton.click();

    // Wait for the pdf to download
    Thread.sleep(10000);

    // Using LambdaTest hook to get the downloaded pdf
    File downloadedPdf = null;
    try {
      String fileContent = Objects.requireNonNull(
        ((JavascriptExecutor) driver).executeAsyncScript("lambda-file-content=LambdaTest.pdf")).toString();
      
      // Convert Base64 to PDF file
      downloadedPdf = convertBase64ToPdf(fileContent);
      
      if (downloadedPdf != null) {
        System.out.println("PDF downloaded successfully: " + downloadedPdf.getAbsolutePath());
        System.out.println("PDF size: " + downloadedPdf.length() + " bytes");
      }
    } catch (Exception e) {
      System.out.println("Failed to get file from LT cloud with error : "+ e.getMessage());
    }

    // Upload the pdf to LambdaTest
    if (downloadedPdf != null) {
      SmartUIConfig config = new SmartUIConfig()
        .withProjectToken(projectToken)
        .withFetchResult(true);

      SmartUIPdf pdfUploader = new SmartUIPdf(config);

      String pdfPath = downloadedPdf.getAbsolutePath();
      System.out.println("Uploading PDF: " + pdfPath);
      FormattedResults result= pdfUploader.uploadPDF(pdfPath);

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String jsonResult = gson.toJson(result);
      System.out.println("=== COMPLETE RESULT AS JSON ===");
      System.out.println(jsonResult);
      System.out.println("=== END OF JSON RESULT ===");
    } else {
      System.out.println("No PDF file available for upload");
    }

  }

  private File convertBase64ToPdf(String base64Content) {
    try {
      // Decode Base64 content
      byte[] pdfBytes = Base64.getDecoder().decode(base64Content);
      
      // Generate unique filename with timestamp
      String fileName = "LambdaTest.pdf";
      String filePath = downloadDir + "/" + fileName;
      
      // Create the PDF file
      File pdfFile = new File(filePath);
      try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
        fos.write(pdfBytes);
        fos.flush();
      }
      
      System.out.println("PDF file created: " + pdfFile.getAbsolutePath());
      return pdfFile;
      
    } catch (IOException e) {
      System.err.println("Error creating PDF file: " + e.getMessage());
      return null;
    } catch (IllegalArgumentException e) {
      System.err.println("Invalid Base64 content: " + e.getMessage());
      return null;
    }
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

}
