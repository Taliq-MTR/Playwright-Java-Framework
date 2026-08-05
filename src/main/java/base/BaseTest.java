package base;

import com.microsoft.playwright.*;
import core.PlaywrightManager;
import factory.BrowserFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;



    private static final Logger logger =
            LoggerFactory.getLogger(BaseTest.class);

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browserName) {

        logger.info("Initializing Playwright");

        playwright = Playwright.create();

        logger.info("Creating Browser");

        BrowserFactory browserFactory =
                new BrowserFactory();

        browser =
                browserFactory.launchBrowser(
                        playwright,
                        browserName);

        logger.info("Initializing Playwright Manager");

        PlaywrightManager.initialize(
                playwright,
                browser);

        context =
                PlaywrightManager.getContext();

        page =
                PlaywrightManager.getPage();
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Closing Playwright Resources");

        PlaywrightManager.unload();
    }
}
