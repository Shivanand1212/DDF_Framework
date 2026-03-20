package Com.Automation.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class listners implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        Extentreporter.initReport("C:\\Users\\Shivanand\\eclipse-workspace\\SeleAutomation\\reports");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String description = result.getMethod()
                                   .getConstructorOrMethod()
                                   .getMethod()
                                   .getAnnotation(Test.class)
                                   .description();

        if (description == null || description.isEmpty()) {
            description = result.getMethod().getMethodName();
        }

        Extentreporter.createTest(description);
        Extentreporter.getTest().log(Status.INFO, "Starting Test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Extentreporter.getTest().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Extentreporter.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Extentreporter.getTest().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Extentreporter.flushReport();
    }
}