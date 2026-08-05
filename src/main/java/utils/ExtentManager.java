package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtentManager {

    private static final Logger logger =
            LoggerFactory.getLogger(ExtentManager.class);

    private static ExtentReports extent;

    private ExtentManager() {
        // Prevent object creation
    }

    public static ExtentReports getExtentReport() {

        if (extent == null) {

            logger.info("Initializing Extent Report");

            // Create Report
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("reports/PlaywrightReport.html");

            spark.config().setReportName("Playwright Automation Report");
            spark.config().setDocumentTitle("Automation Test Execution Report");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo(
                    "Automation Tester",
                    "Mohammad Taliq Ur Rahman");

            extent.setSystemInfo(
                    "Framework",
                    "Playwright Java");

            extent.setSystemInfo(
                    "Environment",
                    System.getProperty("env", "qa").toUpperCase());

            extent.setSystemInfo(
                    "Browser",
                    ConfigReader.get("browser"));

            extent.setSystemInfo(
                    "Operating System",
                    System.getProperty("os.name"));

            extent.setSystemInfo(
                    "Java Version",
                    System.getProperty("java.version"));

            logger.info("Extent Report initialized successfully");
        }

        return extent;
    }
}