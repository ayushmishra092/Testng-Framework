package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Properties;
import java.lang.reflect.Method;

public class BaseTest {
    protected static WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;
    private String browserType;
    private String appUrl;
    protected static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setupClass() throws Exception {
        // Build path dynamically
        String configPath = System.getProperty("user.dir") + File.separator + "config.properties";

        // Load properties
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(configPath);
        props.load(fis);

        browserType = props.getProperty("browser").trim();
        appUrl = props.getProperty("url").trim();


        // Extent Report setup
        extent = utils.ExtentManager.getExtentReports();
       // test = extent.createTest("Your Test Name");
    }

    @BeforeMethod
    public void setupMethod(Method method, ITestResult result) {

        // Create ExtentTest and store in ITestResult
        ExtentTest test = extent.createTest(method.getName());
        testThread.set(test);
        result.setAttribute("extentTest", test);


        if (browserType.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get(appUrl);
    }
    protected static ExtentTest getTest() {
        return testThread.get(); // ✅ returns the correct test instance
    }


    @AfterMethod
    public void tearDownMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void tearDownClass() {
        extent.flush();
    }

    // Screenshot utility
    public static String takeScreenshot(String screenshotName) {
        try {
            new File("screenshots").mkdirs();

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + screenshotName + ".png";
            File dest = new File(destPath);
            FileUtils.copyFile(src, dest);

            return dest.getAbsolutePath(); // ✅ Fix: Use absolute path
        } catch (Exception e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
            return null;
        }

    }
    }
