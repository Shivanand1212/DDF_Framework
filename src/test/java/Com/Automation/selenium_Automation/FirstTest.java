package Com.Automation.selenium_Automation;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import Com.Automation.base.Base;
import Com.Automation.base.ConfigReader;
import Com.Automation.base.LoggerUtil;
import Com.Automation.base.Utility;
import Com.Automation.pages.HandlingWebelement;
import Com.Automation.base.listners;

@Listeners(listners.class)   // Attach the listener
public class FirstTest extends Utility {
    ConfigReader readconfig = new ConfigReader();

    @BeforeSuite
    public void setupSuite() {
        openBrowser();
        LoggerUtil.setDriver(Base.getDriver());
        // No need to call initReport here, listener handles it
    }

    @Test(description = "Do Test -First test case")
    public void testGoogle() throws InterruptedException {
        getDriver().get(readconfig.getProperty("url"));
        HandlingWebelement.Broken_imgs();
    }

    @AfterSuite
    public void tearDownSuite() {
        getDriver().quit();
        // No need to flush here, listener handles it
    }
}