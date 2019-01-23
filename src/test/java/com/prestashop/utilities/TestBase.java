package com.prestashop.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected Actions actions;
    protected Faker faker;
    protected Random random;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void setUpmethod() {
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        actions = new Actions(driver);
        faker = new Faker();
        random = new Random();
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void afterMethod() {
      //  Driver.closeDriver();
        softAssert.assertAll();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("end of test, thank you");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }
       // driver.quit();

    }
}
