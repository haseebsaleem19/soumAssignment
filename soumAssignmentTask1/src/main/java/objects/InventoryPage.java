package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the page where the user can interact with the inventory and sort products.
 */
public class InventoryPage {

    private final WebDriver driver;

    /**
     * Initializes a new instance of the InventoryPage class.
     * @param driver The WebDriver instance to interact with the browser.
     */
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks to sort products on the page by name.
     */
    public void clickSortProductsByName() {
        driver.findElement(By.cssSelector(".product_sort_container")).click();
    }

    /**
     * Clicks to sort products on the page by price.
     */
    public void clickSortProductsByPrice() {
        driver.findElement(By.cssSelector("select option[value='lohi']")).click();
    }

}