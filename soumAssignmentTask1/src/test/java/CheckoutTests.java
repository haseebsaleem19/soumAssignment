import config.ApplicationConfigReader;
import general.BaseTest;
import objects.AddToCartPage;
import objects.CheckoutPage;
import objects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for the checkout process.
 */
public class CheckoutTests extends BaseTest {
    private LoginPage loginPage;
    private AddToCartPage addToCartPage;
    private CheckoutPage checkoutPage;

    /**
     * Setup method to initialize necessary objects and perform a valid login.
     */
    @BeforeMethod
    public void setUp() {
        // Initialize the LoginPage, AddToCartPage, and CheckoutPage objects only once
        loginPage = new LoginPage(driver);
        addToCartPage = new AddToCartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Perform a valid login
        loginPage.login(ApplicationConfigReader.getUsername(), ApplicationConfigReader.getPassword());

        // Navigate to the inventory page
        driver.get(ApplicationConfigReader.getInventoryUrl());

        // Click on the "Add to Cart" button
        addToCartPage.clickAddToCartButton();
    }

    /**
     * Test method to perform the add to cart and checkout process.
     */
    @Test
    public void addToCartAndCheckoutTest() {
        // Click on the cart icon to go to the cart
        checkoutPage.clickShoppingCartContainer();

        // Click on the checkout button
        checkoutPage.clickCheckoutButton();

        // Fill in the checkout information
        checkoutPage.fillCheckOutInformation(
                ApplicationConfigReader.getFirstName(),
                ApplicationConfigReader.getLastName(),
                ApplicationConfigReader.getZipCode());

        // Verify that the summary page is displayed
        WebElement summaryTitle = driver.findElement(By.className("title"));
        Assert.assertTrue(summaryTitle.isDisplayed(), "Checkout summary page should be displayed");

        // Click on the finish button
        checkoutPage.clickFinishButton();

        // Verify that the thank you page is displayed
        WebElement thankYouMessage = driver.findElement(By.className("complete-header"));
        Assert.assertTrue(thankYouMessage.isDisplayed(), "Thank you page should be displayed");
    }
}