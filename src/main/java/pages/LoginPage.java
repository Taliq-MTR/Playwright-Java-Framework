package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ExtentLogger;

public class LoginPage {

    private static final Logger logger =
            LoggerFactory.getLogger(LoginPage.class);

    private final Page page;

    private final Locator txtUsername;
    private final Locator txtPassword;
    private final Locator btnLogin;
    private final Locator lblErrorMessage;

    public LoginPage(Page page) {

        this.page = page;

        txtUsername =
                page.getByPlaceholder("Username");

        txtPassword =
                page.getByPlaceholder("Password");

        btnLogin =
                page.getByRole(
                        AriaRole.BUTTON,
                        new Page.GetByRoleOptions().setName("Login"));

        lblErrorMessage =
                page.locator("[data-test='error']");
    }

    public LoginPage enterUsername(String username) {

        try {

            logger.info("Entering username : {}", username);
            ExtentLogger.info("Entering Username");

            txtUsername.fill(username);

        } catch (Exception e) {

            logger.error("Unable to enter username", e);
            ExtentLogger.fail("Unable to enter Username");

            throw e;
        }

        return this;
    }

    public LoginPage enterPassword(String password) {

        try {

            logger.info("Entering password");
            ExtentLogger.info("Entering Password");

            txtPassword.fill(password);

        } catch (Exception e) {

            logger.error("Unable to enter password", e);
            ExtentLogger.fail("Unable to enter Password");

            throw e;
        }

        return this;
    }

    public InventoryPage clickLogin() {

        try {

            logger.info("Clicking Login button");
            ExtentLogger.info("Click Login");

            btnLogin.click();

            ExtentLogger.pass("Login Successful");

            return new InventoryPage(page);

        } catch (Exception e) {

            logger.error("Login failed", e);
            ExtentLogger.fail("Login Failed");

            throw e;
        }
    }

    public InventoryPage login(String username,
                               String password) {

        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    public String getErrorMessage() {

        return lblErrorMessage.textContent();
    }

    public boolean isLoginPageDisplayed() {

        return txtUsername.isVisible();
    }
}