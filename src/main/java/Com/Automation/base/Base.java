package Com.Automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

    private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();
    private static String browser = "edge"; // default browser

    public static void openBrowser() {
        try {
            if (browser == null || browser.isEmpty()) {
                browser = "edge"; // fallback
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver",
                        "C:\\Users\\Shivanand\\eclipse-workspace\\SeleAutomation\\drivers\\chromedriver.exe");
                    dr.set(new ChromeDriver());
                    break;

                case "edge":
                    System.setProperty("webdriver.edge.driver",
                        "C:\\Users\\Shivanand\\eclipse-workspace\\SeleAutomation\\drivers\\msedgedriver.exe");
                    dr.set(new EdgeDriver());
                    break;

                case "firefox":
                    System.setProperty("webdriver.gecko.driver",
                        "C:\\Users\\Shivanand\\eclipse-workspace\\SeleAutomation\\drivers\\geckodriver.exe");
                    dr.set(new FirefoxDriver());
                    break;

                default:
                    System.out.println("Invalid browser specified. Launching Chrome as default.");
                    System.setProperty("webdriver.chrome.driver",
                        "C:\\Users\\Shivanand\\eclipse-workspace\\SeleAutomation\\drivers\\chromedriver.exe");
                    dr.set(new ChromeDriver());
            }

            getDriver().manage().window().maximize();
            getDriver().manage().deleteAllCookies();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        return dr.get();
    }
    
    public static void logReport(String msg) {
      
        String timeStamp = java.time.LocalDateTime.now().toString();
        System.out.println("[Log - " + timeStamp + "] " + msg);
    }

}
