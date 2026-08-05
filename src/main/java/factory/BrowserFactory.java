package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserFactory {

    private static final Logger logger =
            LoggerFactory.getLogger(BrowserFactory.class);
    public Browser launchBrowser(Playwright playwright,
                                 String browserName) {

        logger.info("Launching browser : {}", browserName);

        Browser browser;

        BrowserType.LaunchOptions options =
                new BrowserType.LaunchOptions()
                        .setHeadless(ConfigReader.getBoolean("headless"));

        switch (browserName.toLowerCase()) {

            case "chrome":
            case "chromium":

                browser = playwright.chromium().launch(options);

                logger.info("Chrome browser launched successfully");

                break;

            case "firefox":

                browser = playwright.firefox().launch(options);

                logger.info("Firefox browser launched successfully");

                break;

            case "webkit":

                browser = playwright.webkit().launch(options);

                logger.info("Webkit browser launched successfully");

                break;

            default:

                logger.error("Unsupported Browser : {}", browserName);

                throw new IllegalArgumentException(
                        "Browser not supported : " + browserName);
        }

        return browser;
    }

}
