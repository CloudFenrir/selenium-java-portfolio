package com.cloudfenrir.tests.ui;

import com.cloudfenrir.config.ConfigReader;
import com.cloudfenrir.driver.DriverFactory;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SmokeTest {

    @BeforeMethod
    private void beforeMethodHook() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }

    @Test
    private void validateTitle() {
        Assert.assertEquals(DriverFactory.getDriver().getTitle(), "Swag Labs");
    }

    @AfterMethod
    private void tearDown() {
        DriverFactory.quitDriver();
    }

}
