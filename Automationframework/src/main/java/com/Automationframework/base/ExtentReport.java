package com.Automationframework.base;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport extends Base{
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static void genExtentReport()
	{	
		String reportFolder = createReportFolder();
        String reportPath = reportFolder + "/ExtentReport.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		extent=new ExtentReports();
		extent.attachReporter(spark);
	    new File(reportFolder + "/screenshots").mkdirs();
		
	    System.setProperty("reportFolder", reportFolder);
	
	}

public static String createReportFolder() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    String timestamp = LocalDateTime.now().format(formatter);

    // Base folder path
    String basePath = System.getProperty("user.dir") + "/Report/" + timestamp;

    // Create folder
    File reportFolder = new File(basePath);
    if (!reportFolder.exists()) {
        reportFolder.mkdirs(); // creates parent + child directories
    }

    return basePath; // return path for reuse
}
	 
    public static void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }


    public static ExtentTest getTest() {
        return test.get();
    }


    public static void flushReport() {
        extent.flush();
    }

}
