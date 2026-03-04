package Com.Automation.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Extentreporter {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String executionFolder;
    private static String screenshotFolder;

    public static void initReport(String basePath) {
        try {
            String folderName = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            executionFolder = basePath + "\\" + folderName;
            screenshotFolder = executionFolder + "\\screenshots";

            Files.createDirectories(Paths.get(executionFolder));
            Files.createDirectories(Paths.get(screenshotFolder));

            ExtentSparkReporter spark = new ExtentSparkReporter(executionFolder + "\\ExtentReport.html");
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Test Execution");
            spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            System.out.println("Extent Report initialized at: " + executionFolder);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static String getScreenshotFolder() {
        return screenshotFolder;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}