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
    public void validateUser() throws Exception {
        Thread.sleep(5000);
        System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get("http://prestashop.lambdatest.io/index.php");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("email")).sendKeys("qademo@qademo.com");
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("111111");
        Thread.sleep(2000);
        driver.findElement(By.id("submit-login")).click();
        Thread.sleep(10000);
    }

}
