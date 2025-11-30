package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            // Create Reports folder if it doesn't exist
            new File("Reports").mkdirs();

            // Generate unique report name with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "Reports/ExtentReport_" + timestamp + ".html";

            // Setup Spark Reporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // ✅ Load system info from config.properties
            try {
                Properties props = new Properties();
                FileInputStream fis = new FileInputStream("config.properties");
                props.load(fis);

                extent.setSystemInfo("Browser", props.getProperty("browser"));
                extent.setSystemInfo("Environment", props.getProperty("env"));
                extent.setSystemInfo("Base URL", props.getProperty("url"));
            } catch (Exception e) {
                System.out.println("Could not load config for system info: " + e.getMessage());
            }

            // ✅ Add system-level info
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("User", System.getProperty("user.name"));

        }
        return extent;
    }
}