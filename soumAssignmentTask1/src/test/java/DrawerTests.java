import config.ApplicationConfigReader;
import general.BaseTest;
import objects.AddToCartPage;
import objects.DrawerPage;
import objects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for the functionality related to the left drawer in the application.
 */
public class DrawerTests extends BaseTest {
    private LoginPage loginPage;
    private AddToCartPage addToCartPage;
    private DrawerPage drawerPage;

    /**
     * Setup method to initialize necessary objects and perform a valid login.
     */
    @BeforeMethod
    public void setUp() {
        // Initialize the LoginPage, DrawerPage, and AddToCartPage objects only once
        loginPage = new LoginPage(driver);
        drawerPage = new DrawerPage(driver);
        addToCartPage = new AddToCartPage(driver);

        // Perform a valid login
        loginPage.login(ApplicationConfigReader.getUsername(), ApplicationConfigReader.getPassword());

        // Navigate to the inventory page
        driver.get(ApplicationConfigReader.getInventoryUrl());
    }

    /**
     * Test method to verify the functionality of opening the left drawer and checking its content.
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or otherwise occupied and the thread is interrupted.
     */
    @Test
    public void openLeftDrawerTest() throws InterruptedException {
        // Click on the menu icon to open the left drawer
        drawerPage.clickOpenLeftDrawer();

        // Assuming you have already located the "All Items" link
        WebElement allItemsLink = driver.findElement(By.id("inventory_sidebar_link"));

        // Assertion to check if the link contains the text "All Items"
        Assert.assertTrue(allItemsLink.getText().contains("All Items"), "Left drawer should contain 'All Items'");
    }

    /**
     * Test method to verify the functionality of navigating to the About page from the left drawer.
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or otherwise occupied and the thread is interrupted.
     */
    @Test
    public void aboutPageTest() throws InterruptedException {
        // Click on the menu icon to open the left drawer
        drawerPage.clickOpenLeftDrawer();

        // Click On about button in left drawer
        drawerPage.clickAboutButtonLeftDrawer();

        WebElement paragraphElement = driver.findElement(By.cssSelector(".MuiTypography-root.MuiTypography-body1.css-sere2z"));
        String actualText = paragraphElement.getText();

        // Assertion to check if the paragraph contains the expected text
        Assert.assertTrue(actualText.contains("The world relies"));
    }

    /**
     * Test method to verify the functionality of logging out from the left drawer.
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or otherwise occupied and the thread is interrupted.
     */
    @Test
    public void logoutTest() throws InterruptedException {
        // Click on the menu icon to open the left drawer
        drawerPage.clickOpenLeftDrawer();

        // Assuming you have already located the "All Items" link
        drawerPage.clickLogoutButtonLeftDrawer();

        WebElement paragraphElement = driver.findElement(By.id("login_credentials"));
        String actualText = paragraphElement.getText();

        // Assertion to check if the paragraph contains the expected text
        Assert.assertTrue(actualText.contains("Accepted usernames are:"));
    }

    /**
     * Test method to verify the functionality of resetting the application state from the left drawer.
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or otherwise occupied and the thread is interrupted.
     */
    @Test
    public void resetAppStateTest() throws InterruptedException {
        // Assuming you have already located the "Add to Cart" button
        addToCartPage.clickAddToCartButton();

        // Click on the menu icon to open the left drawer
        drawerPage.clickOpenLeftDrawer();

        // Click on the reset button in the left drawer
        drawerPage.clickResetButtonLeftDrawer();

        // Find the element
        WebElement element = null;

        // Attempt to find the element
        try {
            element = driver.findElement(By.className("shopping_cart_badge"));

        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If NoSuchElementException is thrown, the element is not present
            System.out.println("Element with class 'shopping_cart_badge' does not exist.");
        }

        // Assertion to check if the element with class 'shopping_cart_badge' does not exist
        Assert.assertNull(element, "Element with class 'shopping_cart_badge' should not exist.");
    }
}
