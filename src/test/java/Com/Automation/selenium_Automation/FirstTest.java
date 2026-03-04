package Com.Automation.selenium_Automation;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.Status;

import Com.Automation.base.Base;
import Com.Automation.base.Extentreporter;
import Com.Automation.base.LoggerUtil;

public class FirstTest extends Base {

    @BeforeSuite
    public void setupSuite() {
        openBrowser();
        LoggerUtil.setDriver(Base.getDriver());
       Extentreporter.initReport("C:\\Users\\Shivanand\\eclipse-workspace\\SeleAutomation\\reports");
    }

    @Test(description = "Verify Google homepage")
    public void testGoogle() {
        getDriver().get("https://www.google.com");
        LoggerUtil.logReport("Opened Google homepage", Status.INFO);
    }

    @AfterSuite
    public void tearDownSuite() {
        getDriver().quit();
        
    }
}