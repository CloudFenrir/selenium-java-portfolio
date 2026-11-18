package com.cloudfenrir.driver.strategies;

import org.openqa.selenium.WebDriver;

public interface DriverStrategy {

    WebDriver createDriver(boolean headless);

}
