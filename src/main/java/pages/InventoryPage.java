package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ExtentLogger;

public class InventoryPage {

    private static final Logger logger =
            LoggerFactory.getLogger(InventoryPage.class);

    private final Page page;

    private final Locator btnAddBackpack;
    private final Locator lblCartBadge;
    private final Locator btnCart;
    private final Locator btnMenu;
    private final Locator lnkLogout;

    public InventoryPage(Page page) {

        this.page = page;

        btnAddBackpack =
                page.locator("[data-test='add-to-cart-sauce-labs-backpack']");

        lblCartBadge =
                page.locator(".shopping_cart_badge");

        btnCart =
                page.locator(".shopping_cart_link");

        btnMenu =
                page.locator("#react-burger-menu-btn");

        lnkLogout =
                page.locator("#logout_sidebar_link");
    }

    public InventoryPage addBackpackToCart() {

        try {

            logger.info("Adding Backpack to cart");
            ExtentLogger.info("Adding Backpack");

            btnAddBackpack.click();

            ExtentLogger.pass("Backpack Added Successfully");

        } catch (Exception e) {

            logger.error("Unable to add backpack", e);
            ExtentLogger.fail("Unable to Add Backpack");

            throw e;
        }

        return this;
    }

    public int getCartCount() {

        if (!lblCartBadge.isVisible()) {
            return 0;
        }

        return Integer.parseInt(lblCartBadge.textContent());
    }

    public CartPage openCart() {

        try {

            logger.info("Opening Cart");
            ExtentLogger.info("Opening Cart");

            btnCart.click();

            return new CartPage(page);

        } catch (Exception e) {

            logger.error("Unable to open cart", e);
            ExtentLogger.fail("Unable to Open Cart");

            throw e;
        }
    }

    public LoginPage logout() {

        try {

            logger.info("Logging out");
            ExtentLogger.info("Logout");

            btnMenu.click();

            lnkLogout.click();

            ExtentLogger.pass("Logout Successful");

            return new LoginPage(page);

        } catch (Exception e) {

            logger.error("Logout failed", e);
            ExtentLogger.fail("Logout Failed");

            throw e;
        }
    }
}