package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents the page where items can be added to the shopping cart.
 */
public class AddToCartPage {

    private final WebDriver driver;

    /**
     * Initializes a new instance of the AddToCartPage class.
     * @param driver The WebDriver instance to interact with the browser.
     */
    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks the "Add to Cart" button on the page.
     */
    public void clickAddToCartButton() {
        WebElement addToCartButton = driver.findElement(By.cssSelector("button[id^='add-to-cart-']"));
        addToCartButton.click();
    }

    /**
     * Clicks the shopping cart container to view the items in the cart.
     */
    public void clickShoppingCartContainer() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    /**
     * Clicks the "Remove" button to remove an item from the cart.
     */
    public void clickRemoveButton() {
        driver.findElement(By.cssSelector("button[id^='remove-']")).click();
    }

    /**
     * Checks if the shopping cart is empty.
     * @return true if the cart is empty, false otherwise.
     */
    public boolean isCartEmpty() {
        return driver.findElements(By.cssSelector("div[class='cart_quantity']")).isEmpty();
    }

    /**
     * Opens the cart page to view and manage the items in the cart.
     */
    public void openCart() {
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
    }
}
