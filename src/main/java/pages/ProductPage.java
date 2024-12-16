//Represent the product page and its actions

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductPage {
    WebDriver driver;

    // Locators
    By sortDropdown = By.className("product_sort_container");
    By addToCartButtons = By.xpath("//button[contains(@class, 'btn_inventory')]");
    By cartButton = By.className("shopping_cart_link");
    By productPrices = By.className("inventory_item_price");  // Price of each product

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Sort by price high to low
    public void sortByPriceHighToLow() {
        driver.findElement(sortDropdown).sendKeys("Price (high to low)");
    }

    // Add products with price $15.99 to cart
    public void addProductsWithPrice(double price) {
        List<WebElement> prices = driver.findElements(productPrices);  // Get all product prices
        List<WebElement> buttons = driver.findElements(addToCartButtons);  // Get all add to cart buttons

        // Loop through all products and find the ones with the price $15.99
        for (int i = 0; i < prices.size(); i++) {
            String priceText = prices.get(i).getText().replace("$", "").trim();  // Clean the price text
            double productPrice = Double.parseDouble(priceText);

            // If price is 15.99, click the "Add to Cart" button for this product
            if (productPrice == price) {
                buttons.get(i).click();  // Click the add to cart button
                System.out.println("Added product with price: $" + productPrice + " to the cart.");
            }
        }
    }

    // Navigate to the cart
    public CartPage goToCart() {
        driver.findElement(cartButton).click();
        return new CartPage(driver);
    }
}

