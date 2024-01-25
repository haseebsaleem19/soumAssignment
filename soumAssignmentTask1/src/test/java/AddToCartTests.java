import config.ApplicationConfigReader;
import general.BaseTest;
import objects.AddToCartPage;
import objects.InventoryPage;
import objects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for adding and removing products from the shopping cart.
 */
public class AddToCartTests extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private AddToCartPage addToCartPage;

    /**
     * Sets up the test environment by initializing necessary objects and performing a valid login.
     */
    @BeforeMethod
    public void setUp() {
        // Initialize the LoginPage and InventoryPage objects only once
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        addToCartPage = new AddToCartPage(driver);

        // Perform a valid login
        loginPage.login(ApplicationConfigReader.getUsername(), ApplicationConfigReader.getPassword());

        // Navigate to the inventory page
        driver.get(ApplicationConfigReader.getInventoryUrl());

        // Click on the Add to Cart Button
        addToCartPage.clickAddToCartButton();
    }

    /**
     * Test to add a product and verify its presence on the cart page.
     */
    @Test
    public void addProductAndVerifyOnCartPageTest() {
        // Verify that the cart icon is updated to show the correct number of items
        WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
        Assert.assertTrue(cartIcon.isDisplayed(), "Cart icon should be displayed");
        Assert.assertEquals(cartIcon.getText(), "1", "Cart icon should show 1 item");

        // Navigate to the cart page
        addToCartPage.clickShoppingCartContainer();

        // Verify that the added product is displayed in the cart
        WebElement cartItem = driver.findElement(By.cssSelector(".cart_item"));
        Assert.assertTrue(cartItem.isDisplayed(), "Added product should be displayed in the cart");
    }

    /**
     * Test to remove a product from the cart and verify its absence.
     */
    @Test
    public void removeProductFromCartTest() {
        // Navigate to the cart page
        addToCartPage.openCart();

        // Remove the product from the cart
        addToCartPage.clickRemoveButton();

        // Verify that the cart is now empty or contains the correct number of items
        Assert.assertTrue(addToCartPage.isCartEmpty(), "Cart should be empty after removing the product");
    }

    /**
     * Test to add multiple products to the cart and verify the count.
     */
    @Test
    public void addMultipleProductsToCartTest() throws InterruptedException {
        // Add multiple products to the cart
        List<WebElement> addToCartButtons = driver.findElements(By.className("btn_inventory"));
        for (WebElement addToCartButton : addToCartButtons) {
            addToCartButton.click();
        }

        // Verify products added to the cart
        WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_badge"));
        int itemCount = Integer.parseInt(shoppingCartBadge.getText());
        Assert.assertTrue(itemCount > 1, "Product count in the cart is not greater than 1");
    }

    /**
     * Test to add and then remove multiple products from the cart.
     */
    @Test
    public void addAndRemoveMultipleProductsFromCartTest() throws InterruptedException {
        // Verify that we are on the Products page
        addMultipleProductsToCartTest();

        // Open the shopping cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Remove products from the cart
        List<WebElement> removeButtons = driver.findElements(By.className("btn_secondary"));
        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }

        // Verify products removed from the cart
        Assert.assertTrue(addToCartPage.isCartEmpty(), "Cart should be empty after removing the product");
    }
}
