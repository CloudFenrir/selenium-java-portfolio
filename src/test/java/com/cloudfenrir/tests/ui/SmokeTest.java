package com.cloudfenrir.tests.ui;

import com.cloudfenrir.config.ConfigReader;
import com.cloudfenrir.driver.DriverFactory;
import com.cloudfenrir.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    private void validateTitle() {
        Assert.assertEquals(DriverFactory.getDriver().getTitle(), "Swag Labs");
    }

}
