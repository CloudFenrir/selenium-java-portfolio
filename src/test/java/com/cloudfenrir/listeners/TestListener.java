package com.cloudfenrir.listeners;

import com.cloudfenrir.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.atomic.AtomicInteger;

public class TestListener implements ITestListener {

    private static final AtomicInteger failures = new AtomicInteger(0);

    @Override
    public void onTestFailure(ITestResult result) {
        int failureNumber = failures.incrementAndGet();
        try {
            String path = ScreenshotUtil.takeScreenshot(result.getName());
            System.out.println("Failure #" + failureNumber + ": " + result.getName() + ". Screenshot saved: " + path);
        } catch (Exception e) {
            System.out.println("Failure #" + failureNumber + ": " + result.getName()
                    + ". Could not take screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Total failures in this run: " + failures.get());
    }
}
