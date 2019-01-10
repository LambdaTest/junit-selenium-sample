package com.lambdatest;

import com.lambdatest.LambdaTestBaseTest;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class SimpleTest extends LambdaTestBaseTest {

    /**
     * Simple Test case annotating JUnit Test
     * @throws Exception
     */
//    @Test
//    public void test() throws Exception {
//        driver.get("http://prestashop.lambdatest.io/index.php");
//        Thread.sleep(5000);
//        assertEquals("Google", driver.getTitle());
//    }


    @Test
    public void validateUser(){

        System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get("http://prestashop.lambdatest.io/index.php");
        driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
        driver.findElement(By.id("email")).sendKeys("testuser_3");
        driver.findElement(By.id("passwd")).sendKeys("Test@123");
        driver.findElement(By.id("SubmitLogin")).click();
    }

}
