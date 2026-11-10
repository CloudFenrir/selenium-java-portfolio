package com.cloudfenrir.listeners;

import com.cloudfenrir.utils.ScreenshotUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            String path = ScreenshotUtil.takeScreenshot(result.getName());
            System.out.println("Screenshot saved: " + path);
        } catch (Exception e) {
            System.out.println("Could not take screenshot for " + result.getName() + ": " + e.getMessage());
        }
    }
}
