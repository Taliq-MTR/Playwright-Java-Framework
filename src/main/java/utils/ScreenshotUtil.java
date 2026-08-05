package utils;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final Logger logger =
            LoggerFactory.getLogger(ScreenshotUtil.class);

    private ScreenshotUtil() {
        // Prevent object creation
    }

    public static String captureScreenshot(Page page, String testName) {

        try {

            logger.info("Capturing screenshot for test : {}", testName);

            // Create screenshots folder if it doesn't exist
            File folder = new File("reports/screenshots");

            if (!folder.exists()) {

                logger.info("Creating screenshots directory");

                folder.mkdirs();
            }

            String timeStamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String path =
                    "reports/screenshots/"
                            + testName + "_"
                            + timeStamp + ".png";

            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(Paths.get(path))
                            .setFullPage(true));

            logger.info("Screenshot saved successfully : {}", path);

            return path;

        } catch (Exception e) {

            logger.error("Unable to capture screenshot", e);

            throw new RuntimeException(
                    "Unable to capture screenshot.",
                    e);
        }
    }
}