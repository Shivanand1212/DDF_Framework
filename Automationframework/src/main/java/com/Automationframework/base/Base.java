package com.Automationframework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	public  ReadConfig prop= new ReadConfig();
	public static WebDriver driver;
	public static ThreadLocal<WebDriver> TL= new ThreadLocal<>();
	
	public static void setDriver(WebDriver driver) {
		
           TL.set(driver);
		
	}
	
	public static WebDriver getDriver() {
		return TL.get();
	}
	
	
	public  void initializeBrowser()
	{
		  try {
		        ReadConfig.loadprop(); // load properties first
		    } catch (Exception e) {
		        throw new RuntimeException("Failed to load config file", e);
		    }
		String browser= ReadConfig.getConfigdata("browser");

		 if (browser.equalsIgnoreCase("edge")) {
	            System.setProperty("webdriver.edge.driver", "C:\\Users\\Shivanand\\eclipse-workspace\\Automationframework\\drivers\\msedgedriver.exe");
	            driver = new EdgeDriver();
	            setDriver(driver);
	        } else if (browser.equalsIgnoreCase("chrome")) {
	            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
	            driver = new ChromeDriver();
	            setDriver(driver);
	        } else if (browser.equalsIgnoreCase("firefox")) {
	            System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");
	            driver = new FirefoxDriver();
	            setDriver(driver);
	        } else {
	            throw new RuntimeException("Unsupported browser: " + browser);
	        }
		 getDriver().manage().window().maximize();
		
	}

}
