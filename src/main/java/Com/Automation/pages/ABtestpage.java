package Com.Automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Com.Automation.base.Utility;

public class ABtestpage extends Utility {

	public static WebElement abTesting = getDriver().findElement(By.xpath("//ul/li/a[contains(normalize-space(.),'A/B Testing')]"));
	public static WebElement abTestingv = getDriver().findElement(By.id("ab-heading"));

	public static void verify_versionheading() throws InterruptedException {
		
		waitForVisibility(getDriver(),abTesting, "Abtesting");
		
		Thread.sleep(3000);
		
		Click(abTesting, "Abtesting");
		
		Thread.sleep(3000);

		verifytext(abTestingv, "Version A: Original Content");
		
	}
	
}
