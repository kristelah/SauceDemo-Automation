//Contains test scripts that use the page objects.

package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import org.junit.AfterClass;
import io.github.bonigarcia.wdm.WebDriverManager;


public class SauceDemoTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        // Set up the WebDriver (you can also use WebDriverManager for easier browser management)
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testLoginAndCheckout() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);

        // Step 1: Login with valid credentials
        loginPage.login("standard_user", "secret_sauce");

        // Validate successful login
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

        // Step 2: Sort products by price high to low
        productPage.sortByPriceHighToLow();

        // Step 3: Add two products to the cart
        productPage.addProductsWithPrice(15.99);


        // Step 4: Proceed to checkout
        cartPage = productPage.goToCart();
        checkoutPage = cartPage.proceedToCheckout();

        // Step 5: Complete the purchase
        checkoutPage.completeCheckout("John", "Doe", "12345");

        // Step 6: Validate checkout summary
        String checkoutSummary = checkoutPage.getCheckoutSummary();
        Assert.assertTrue(checkoutSummary.contains("THANK YOU FOR YOUR ORDER"));

        // Test for locked_out_user
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked_out_user"));
    }

    @Test
    public void testInvalidLogin() {
        loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "invalid_pass");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
