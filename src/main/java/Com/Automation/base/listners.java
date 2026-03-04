package Com.Automation.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class listners implements ITestListener {

	  @Override
	    public void onTestStart(ITestResult result) {
	        // Create a new test entry in Extent Report
	        ExtentTest test = Extentreporter.createTest(result.getMethod().getMethodName());
	        test.log(Status.INFO, "Starting Test: " + result.getMethod().getMethodName());
	        System.out.println("Starting Test: " + result.getMethod().getMethodName());
	    }


    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("PASSED: " + result.getMethod().getMethodName());
        Extentreporter.flushReport();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("FAILED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("SKIPPED: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        Extentreporter.flushReport(); // flush report here
    }

}