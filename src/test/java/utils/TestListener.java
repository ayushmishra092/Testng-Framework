package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");

        String screenshotPath = base.BaseTest.takeScreenshot(result.getName());

        if (screenshotPath != null && !screenshotPath.isEmpty()) {
            test.fail("Test Failed: " + result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else {
            test.fail("Test Failed: " + result.getThrowable() + " (Screenshot not available)");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        test.info("Test completed successfully");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        test.skip("Test Skipped: " + result.getThrowable());
    }
}