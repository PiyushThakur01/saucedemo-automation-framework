package com.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
            spark.config().setReportName("SauceDemo Automation Report");
            spark.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Application", ConfigReader.get("baseUrl"));
            extent.setSystemInfo("Browser", ConfigReader.get("browser"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }
}
