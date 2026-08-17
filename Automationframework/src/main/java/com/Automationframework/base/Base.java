package com.Automationframework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
        String headless = ReadConfig.getConfigdata("headless"); // true/false
        WebDriver driver;

        if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            // Explicitly point to 64-bit Edge binary
            options.setBinary("C:\\Program Files\\Microsoft\\Edge\\Application\\msedge.exe");

            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-software-rasterizer");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new EdgeDriver(options);
        } else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }
            driver = new FirefoxDriver(options);

        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        setDriver(driver);
        getDriver().manage().window().maximize();
    }
}
