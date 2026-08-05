package core;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaywrightManager {
    private static final Logger logger =
            LoggerFactory.getLogger(PlaywrightManager.class);

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    public static void initialize(Playwright pw, Browser br) {
        logger.info("Initializing Playwright Manager");
        playwright.set(pw);

        browser.set(br);

        context.set(browser.get().newContext());
        logger.info("Browser Context Created");
        page.set(context.get().newPage());
        logger.info("Page Created");
    }

    public static Playwright getPlaywright() {
        return playwright.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    public static BrowserContext getContext() {
        return context.get();
    }

    public static Page getPage() {
        return page.get();
    }

    public static void unload() {

        if (page.get() != null) {
            page.get().close();
            page.remove();
        }

        if (context.get() != null) {
            context.get().close();
            context.remove();
        }

        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }

        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
        }
    }
}