package com.cloudfenrir.steps;

import com.cloudfenrir.config.ConfigReader;
import com.cloudfenrir.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

}
