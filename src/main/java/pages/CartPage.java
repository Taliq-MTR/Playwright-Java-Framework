package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ExtentLogger;

public class CartPage {

    private static final Logger logger =
            LoggerFactory.getLogger(CartPage.class);

    private final Page page;

    private final Locator lblBackpack;
    private final Locator btnRemove;
    private final Locator btnContinueShopping;

    public CartPage(Page page) {

        this.page = page;

        lblBackpack =
                page.locator(".inventory_item_name");

        btnRemove =
                page.locator("[data-test='remove-sauce-labs-backpack']");

        btnContinueShopping =
                page.locator("[data-test='continue-shopping']");
    }

    public boolean isBackpackDisplayed() {

        logger.info("Verifying Backpack availability");
        ExtentLogger.info("Verify Backpack");

        return lblBackpack.isVisible();
    }

    public CartPage removeBackpack() {

        try {

            logger.info("Removing Backpack");
            ExtentLogger.info("Removing Backpack");

            btnRemove.click();

            ExtentLogger.pass("Backpack Removed");

        } catch (Exception e) {

            logger.error("Unable to remove backpack", e);
            ExtentLogger.fail("Unable to Remove Backpack");

            throw e;
        }

        return this;
    }

    public boolean isCartEmpty() {

        logger.info("Verifying Cart Empty");
        ExtentLogger.info("Verify Cart Empty");

        return lblBackpack.count() == 0;
    }
}