package com.cloudfenrir.driver;

import com.cloudfenrir.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() { }

    public static void initDriver() {

       String browser = ConfigReader.get("browser").toLowerCase();
       boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

       WebDriver webDriver;
        switch (browser) {
            case "chrome": {
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new", "--window-size=1920,1080");
                }
                webDriver = new ChromeDriver(options);
                break;
            }
            case "edge": {
                EdgeOptions options = new EdgeOptions();
                if (headless) {
                    options.addArguments("--headless=new", "--window-size=1920,1080");
                }
                webDriver = new EdgeDriver(options);
                break;
            }
            case "firefox": {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("-headless", "-width=1920", "-height=1080");
                }
                webDriver = new FirefoxDriver(options);
                break;
            }
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        if (!headless) {
            webDriver.manage().window().maximize();
        }

        driver.set(webDriver);

    }

    public WebDriver getDriver() {
        if (driver.get() != null) {
            return driver.get();
        } else {
            throw new IllegalStateException("Driver not initialized. Call initDriver() first");
        }
    }

    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
