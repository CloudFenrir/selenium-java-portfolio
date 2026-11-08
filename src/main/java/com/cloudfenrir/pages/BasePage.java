package com.cloudfenrir.pages;

import com.cloudfenrir.config.ConfigReader;
import com.cloudfenrir.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(ConfigReader.getInt("timeout")));
    }

    protected void click(By locator) {
        this.wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected boolean isDisplayed(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    protected static By byDataTest(String value) {
        return By.cssSelector("[data-test='" + value + "']");
    }

    protected abstract By pageIdentifier();

    public boolean isLoaded() {
         try {
             this.wait.until(ExpectedConditions.visibilityOfElementLocated(pageIdentifier()));
             return true;
         } catch (TimeoutException e) {
             return false;
         }
    }

}
