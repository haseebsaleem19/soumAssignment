import config.ApplicationConfigReader;
import general.BaseTest;
import objects.InventoryPage;
import objects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for testing functionality on the Inventory Page of the application.
 */
public class InventoryTests extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    /**
     * Setup method to initialize necessary objects and perform a valid login.
     */
    @BeforeMethod
    public void setUp() {
        // Initialize the LoginPage and InventoryPage objects only once
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        // Perform valid login
        loginPage.login(ApplicationConfigReader.getUsername(), ApplicationConfigReader.getPassword());

        // Navigate to inventory page
        driver.get(ApplicationConfigReader.getInventoryUrl());
    }

    /**
     * Test method to verify the sorting of products by name on the Inventory Page.
     */
    @Test
    public void sortProductsByNameTest() {
        // Click on Sort Dropdown
        inventoryPage.clickSortProductsByName();

        // Verify that products are sorted by name
        List < WebElement > productNames = driver.findElements(By.cssSelector(".inventory_item_name"));
        for (int i = 1; i < productNames.size(); i++) {
            String previousProductName = productNames.get(i - 1).getText();
            String currentProductName = productNames.get(i).getText();
            Assert.assertTrue(previousProductName.compareTo(currentProductName) <= 0,
                    "Products are not sorted by name");
        }
    }

    /**
     * Test method to verify the sorting of products by price (low to high) on the Inventory Page.
     */
    @Test
    public void sortProductsByPriceLowToHighTest() {
        // Click on Sort Dropdown
        inventoryPage.clickSortProductsByName();

        // Click on the "Price (low to high)" option to sort products
        inventoryPage.clickSortProductsByPrice();

        // Verify that products are sorted by price (low to high)
        List < WebElement > productPrices = driver.findElements(By.cssSelector(".inventory_item_price"));
        for (int i = 1; i < productPrices.size(); i++) {
            double previousPrice = Double.parseDouble(productPrices.get(i - 1).getText().substring(1));
            double currentPrice = Double.parseDouble(productPrices.get(i).getText().substring(1));
            Assert.assertTrue(previousPrice <= currentPrice,
                    "Products are not sorted by price (low to high)");
        }
    }

    /**
     * Test method to check the format of the product amount on the Inventory Page.
     */
    @Test
    public void checkProductAmountTest() {
        // Get all product elements
        List < WebElement > productElements = driver.findElements(By.cssSelector(".inventory_item_price"));

        // Assert that the amount of each product div starts and ends with "$"
        for (WebElement productElement: productElements) {
            String productAmount = productElement.getText();
            Assert.assertTrue(productAmount.startsWith("$"), "Product amount should start with '$'");
        }
    }

    /**
     * Test method to check the visibility of "Add to Cart" buttons on the Inventory Page.
     */
    @Test
    public void checkAddToCartButtonTest() {
        // Get all "Add to Cart" buttons
        List < WebElement > addToCartButtons = driver.findElements(By.cssSelector("button[id^='add-to-cart-']"));

        // Assert that each "Add to Cart" button is displayed
        for (WebElement addToCartButton: addToCartButtons) {
            Assert.assertTrue(addToCartButton.isDisplayed(), "Add to Cart button should be displayed for the product");
        }
    }
}