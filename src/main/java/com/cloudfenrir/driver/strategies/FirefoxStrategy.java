package com.cloudfenrir.driver.strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxStrategy implements DriverStrategy {
    @Override
    public WebDriver createDriver(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless", "-width=1920", "-height=1080");
        }
        return new FirefoxDriver(options);
    }
}
