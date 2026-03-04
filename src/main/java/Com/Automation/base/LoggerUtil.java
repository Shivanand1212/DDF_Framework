package Com.Automation.base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {

    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static void logReport(String msg, Status status) {
        try {
            System.out.println("[LOG] " + msg);

            if (driver == null) {
                System.out.println("WebDriver not set. Cannot capture screenshot.");
                Extentreporter.getTest().log(status, msg);
                return;
            }

            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            // Unique screenshot name
            String screenshotName = "screenshot_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".png";
            String screenshotPath = Extentreporter.getScreenshotFolder() + "\\" + screenshotName;

            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));

            // Log message + screenshot in Extent Report
            Extentreporter.getTest().log(status, msg,
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

            System.out.println("Screenshot saved at: " + screenshotPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}