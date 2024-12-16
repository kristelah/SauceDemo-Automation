package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @When("User logs in with valid credentials {string} and {string}")
    public void user_logs_in_with_valid_credentials(String username, String password) {
        loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Then("User should be successfully logged in")
    public void user_should_be_successfully_logged_in() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @When("User sorts products by price from high to low")
    public void user_sorts_products_by_price_from_high_to_low() {
        productPage = new ProductPage(driver);
        productPage.sortByPriceHighToLow();
    }

    @Then("User adds products with price {string} to the cart")
    public void user_adds_products_with_price_to_the_cart(String price) {
        productPage.addProductsWithPrice(Double.parseDouble(price));
    }

    @When("User navigates to the cart")
    public void user_navigates_to_the_cart() {
        cartPage = productPage.goToCart();
    }

    @Then("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        cartPage.checkout();
    }

    @Then("User should see the checkout summary with 2 items")
    public void user_should_see_the_checkout_summary_with_items() {
        Assert.assertTrue(cartPage.getItemCount() == 2);
    }

    @When("User logs in with invalid credentials {string} and {string}")
    public void user_logs_in_with_invalid_credentials(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("User should see an error message")
    public void user_should_see_an_error_message() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        driver.quit();
    }
}
