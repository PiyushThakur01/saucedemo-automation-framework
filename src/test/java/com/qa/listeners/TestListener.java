package com.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.utils.DriverFactory;
import com.qa.utils.ExtentManager;
import com.qa.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/** Writes results to the Extent HTML report and attaches a screenshot to every failure. */
public class TestListener implements ITestListener {

    private static final ExtentReports EXTENT = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getMethod().getMethodName();
        if (result.getParameters().length > 0) {
            name = name + " " + java.util.Arrays.toString(result.getParameters());
        }
        CURRENT_TEST.set(EXTENT.createTest(name, result.getMethod().getDescription()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        CURRENT_TEST.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        CURRENT_TEST.get().fail(result.getThrowable());
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            String file = ScreenshotUtil.capture(driver, result.getMethod().getMethodName());
            // report sits in reports/, screenshots in reports/screenshots/
            CURRENT_TEST.get().addScreenCaptureFromPath("screenshots/" + file, "Failure screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        CURRENT_TEST.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        EXTENT.flush();
    }
}
