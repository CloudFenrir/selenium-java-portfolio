package com.cloudfenrir.tests;

import com.cloudfenrir.config.ConfigReader;
import com.cloudfenrir.driver.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {


    @BeforeMethod
    public void setup() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }


    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }


}
