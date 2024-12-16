//Represent the shopping cart page.

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    // Locators
    By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Proceed to checkout
    public CheckoutPage proceedToCheckout() {
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }
}
