package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import core.PlaywrightManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class ExtentListener implements ITestListener {

    private static final Logger logger =
            LoggerFactory.getLogger(ExtentListener.class);

    private static final ExtentReports extent =
            ExtentManager.getExtentReport();

    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    public static ExtentTest getTest() {
        return test.get();
    }

    @Override
    public void onTestStart(ITestResult result) {

        logger.info("Test Started : {}",
                result.getMethod().getMethodName());

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        logger.info("Test Passed : {}",
                result.getMethod().getMethodName());

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        logger.error("Test Failed : {}",
                result.getMethod().getMethodName(),
                result.getThrowable());

        test.get().fail(result.getThrowable());

        try {

            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            PlaywrightManager.getPage(),
                            result.getMethod().getMethodName());

            test.get().fail(
                    MediaEntityBuilder
                            .createScreenCaptureFromPath(screenshotPath)
                            .build());

        } catch (Exception e) {

            logger.error("Unable to attach screenshot", e);

            test.get().warning(
                    "Unable to capture screenshot : "
                            + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        logger.warn("Test Skipped : {}",
                result.getMethod().getMethodName());

        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        logger.info("Flushing Extent Report");

        extent.flush();

        logger.info("Extent Report generated successfully");
    }
}