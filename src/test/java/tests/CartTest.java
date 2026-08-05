package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentLogger;

public class CartTest extends BaseTest {

    @Test(description = "Verify user can add and remove product from cart")
    public void verifyAddRemoveProduct() {

        page.navigate(ConfigReader.get("base.url"));

        LoginPage loginPage = new LoginPage(page);

        InventoryPage inventoryPage = loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password"));

        inventoryPage.addBackpackToCart();

        Assert.assertEquals(
                inventoryPage.getCartCount(),
                1,
                "Cart count mismatch.");

        CartPage cartPage = inventoryPage.openCart();

        Assert.assertTrue(
                cartPage.isBackpackDisplayed(),
                "Backpack is not displayed in cart.");

        cartPage.removeBackpack();

        Assert.assertTrue(
                cartPage.isCartEmpty(),
                "Cart is not empty.");

        ExtentLogger.pass("Add and Remove Product verification completed successfully");
    }
}