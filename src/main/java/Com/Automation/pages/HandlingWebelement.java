package Com.Automation.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import Com.Automation.base.Extentreporter;
import Com.Automation.base.Utility;

public class HandlingWebelement extends Utility {
//Locators
	
	public static WebElement abTesting = getDriver().findElement(By.xpath("//ul/li/a[contains(normalize-space(.),'A/B Testing')]"));
	public static WebElement abTestingv = getDriver().findElement(By.id("ab-heading"));
	public static WebElement Brknimgs = getDriver().findElement(By.xpath("//a[contains(text(),'Broken Images')]"));
	public static WebElement Brknimgsheading = getDriver().findElement(By.xpath("//h3[contains(text(),'Broken Images')]"));
	public static WebElement images = getDriver().findElement(By.xpath("//div[@class='image-container']"));

	

	public static void verify_versionheading() throws InterruptedException {
		
		waitForVisibility(getDriver(),abTesting, "Abtesting");
		
		Thread.sleep(3000);
		
		Click(abTesting, "Abtesting");
		
		Thread.sleep(3000);

		verifytext(abTestingv, "Version A: Original Content");
		
	}
	public static void Broken_imgs() {
	    int validCount = 0;
	    int invalidCount = 0;

	    try {
	        Click(Brknimgs, "Broken images");
	        Thread.sleep(3000);
	        verifytext(Brknimgsheading, "Broken Images");

	        List<WebElement> images = getDriver().findElements(By.tagName("img"));
	        System.out.println("Total images found: " + images.size());

	        for (WebElement img : images) {
	            String imgUrl = img.getAttribute("src");
	            try {
	                HttpURLConnection connection = (HttpURLConnection) new URL(imgUrl).openConnection();
	                connection.setRequestMethod("HEAD");
	                connection.connect();

	                int responseCode = connection.getResponseCode();

	                if (responseCode >= 400) {
	                    System.out.println(" Broken image: " + imgUrl + " | Response code: " + responseCode);
	                    invalidCount++;
	                } else {
	                    System.out.println(" Valid image: " + imgUrl);
	                    validCount++;
	                }
	            } catch (Exception e) {
	                System.out.println(" Broken image: " + imgUrl + " | Exception: " + e.getMessage());
	                invalidCount++;
	            }
	        }

	        System.out.println("Valid images: " + validCount);
	        System.out.println("Invalid images: " + invalidCount);

	        // Optional: log into ExtentReport
	        Extentreporter.getTest().log(Status.INFO, "Valid images: " + validCount);
	        Extentreporter.getTest().log(Status.INFO, "Invalid images: " + invalidCount);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}}

s



