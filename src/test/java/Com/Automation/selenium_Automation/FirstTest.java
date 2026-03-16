package Com.Automation.selenium_Automation;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.Status;

import Com.Automation.base.Base;
import Com.Automation.base.ConfigReader;
import Com.Automation.base.Extentreporter;
import Com.Automation.base.LoggerUtil;
import Com.Automation.base.Utility;
import Com.Automation.pages.ABtestpage;

public class FirstTest extends Utility {
ConfigReader readconfig= new ConfigReader();
    @BeforeSuite
    public void setupSuite() {
        openBrowser();
        LoggerUtil.setDriver(Base.getDriver());
       Extentreporter.initReport("C:\\Users\\Shivanand\\eclipse-workspace\\SeleAutomation\\reports");
    }

    @Test(description = "Do Test -First test case")
    public void testGoogle() throws InterruptedException {
        getDriver().get(readconfig.getProperty("url"));
        ABtestpage.verify_versionheading();
    }

    @AfterSuite
    public void tearDownSuite() {
        getDriver().quit();
        
    }
}