package com.Automationframework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
    public ReadConfig prop = new ReadConfig();
    private static ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    // Set driver in ThreadLocal
    public static void setDriver(WebDriver driver) {
        TL.set(driver);
    }

    // Get driver from ThreadLocal
    public static WebDriver getDriver() {
        return TL.get();
    }

    public void initializeBrowser() {
        try {
            ReadConfig.loadprop(); // load properties first
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }

        String browser = ReadConfig.getConfigdata("browser");
        WebDriver driver;

        if (browser.equalsIgnoreCase("edge")) {
            // System.setProperty("webdriver.edge.driver", "path/to/msedgedriver.exe");
            driver = new EdgeDriver();

        } else if (browser.equalsIgnoreCase("chrome")) {
            // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            // System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");
            driver = new FirefoxDriver();

        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        setDriver(driver);
        getDriver().manage().window().maximize();
    }
}
