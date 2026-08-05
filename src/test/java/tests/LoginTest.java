package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentLogger;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    @Test(description = "Verify user is able to login with valid credentials")
    public void verifyValidLogin() {

        page.navigate(ConfigReader.get("base.url"));

        ExtentLogger.info("Navigating to SauceDemo Application");

        LoginPage loginPage = new LoginPage(page);

        InventoryPage inventoryPage = loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password"));

        ExtentLogger.info("Verifying Inventory Page");

        assertThat(page)
                .hasURL(ConfigReader.get("base.url") + "/inventory.html");

        assertThat(page)
                .hasTitle("Swag Labs");

        Assert.assertTrue(
                page.getByText("Products").isVisible(),
                "Products label is not displayed.");

        ExtentLogger.pass("Login verification completed successfully");
    }
}