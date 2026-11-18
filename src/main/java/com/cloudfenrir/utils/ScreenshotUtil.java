package com.cloudfenrir.utils;

import com.cloudfenrir.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {

    private ScreenshotUtil() {}

    public static String takeScreenshot(String name) {
        try {
            File screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

            Path directory = Path.of("target", "screenshots");
            Files.createDirectories(directory);

            Path destination = directory.resolve(name + "_" + timestamp + ".png");
            Files.copy(screenshot.toPath(), destination);
            return destination.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not save screenshot: " + name, e);
        }
    }

}
