package com.cloudfenrir.driver;

import com.cloudfenrir.config.ConfigReader;
import com.cloudfenrir.driver.strategies.ChromeStrategy;
import com.cloudfenrir.driver.strategies.DriverStrategy;
import com.cloudfenrir.driver.strategies.EdgeStrategy;
import com.cloudfenrir.driver.strategies.FirefoxStrategy;
import com.cloudfenrir.utils.FrameworkException;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Map<Browser, DriverStrategy> STRATEGIES = Map.of(
            Browser.CHROME, new ChromeStrategy(),
            Browser.FIREFOX, new FirefoxStrategy(),
            Browser.EDGE, new EdgeStrategy()
    );

    private DriverFactory() { }

    public static void initDriver() {

       Browser browser = Browser.fromConfig(ConfigReader.get("browser"));
       boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
       DriverStrategy driverStrategy = STRATEGIES.get(browser);
       if (driverStrategy == null) {
           throw new FrameworkException("No driver strategy registered for: " + browser);
       }
       WebDriver webDriver = driverStrategy.createDriver(headless);

        if (!headless) {
            webDriver.manage().window().maximize();
        }

        driver.set(webDriver);

    }

    public static WebDriver getDriver() {
        if (driver.get() != null) {
            return driver.get();
        } else {
            throw new FrameworkException("Driver not initialized. Call initDriver() first");
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
