import config.ApplicationConfigReader;
import general.BaseTest;
import objects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for testing login functionality on the application.
 */
public class LoginTests extends BaseTest {
    private LoginPage loginPage;

    /**
     * Setup method to initialize the LoginPage object only once.
     */
    @BeforeMethod
    public void setUp() {
        // Initialize the LoginPage object only once
        loginPage = new LoginPage(driver);
    }

    /**
     * Test method to perform a valid login and verify successful login.
     */
    @Test
    public void validLoginTest() {
        // Perform valid login
        loginPage.login(ApplicationConfigReader.getUsername(), ApplicationConfigReader.getPassword());

        // Verify successful login by checking for the presence of an element on the next page
        Assert.assertTrue(driver.findElement(By.cssSelector(".inventory_container")).isDisplayed(), "Login successful");
    }

    /**
     * Test method to perform an invalid login and verify the error message.
     */
    @Test
    public void invalidLoginTest() {
        // Perform invalid login
        loginPage.login("invalid_username", "invalid_password");

        // Verify error message for invalid login
        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertTrue(errorMessage.contains("Epic sadface: Username and password do not match any user in this service"),
                "Invalid login error message does not match expected pattern");
    }

    /**
     * Test method to perform a login with empty username and password fields and verify the error message.
     */
    @Test
    public void emptyFieldsLoginTest() {
        // Perform login with empty username and password fields
        loginPage.login("", "");

        // Verify error message for empty fields
        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required",
                "Error message for empty username field is incorrect");
    }

    /**
     * Test method to perform a login with a locked-out user and verify the error message.
     */
    @Test
    public void lockedOutUserLoginTest() {
        // Perform invalid login
        loginPage.login(ApplicationConfigReader.getLockedOutUser(), ApplicationConfigReader.getPassword());

        // Verify error message for locked-out user
        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertTrue(errorMessage.contains("Epic sadface: Sorry, this user has been locked out."),
                "Error message for locked-out user does not match expected pattern");
    }

    /**
     * Test method to perform a valid login with a problem user and verify successful login.
     */
    @Test
    public void problemUserLoginTest() {
        // Perform valid login
        loginPage.login(ApplicationConfigReader.getProblemUser(), ApplicationConfigReader.getPassword());

        // Verify successful login by checking for the presence of an element on the next page
        Assert.assertTrue(driver.findElement(By.cssSelector(".inventory_container")).isDisplayed(), "Login successful");
    }

    /**
     * Test method to perform a valid login with a performance glitch user and verify successful login.
     */
    @Test
    public void performanceGlitchUserLoginTest() {
        // Perform valid login
        loginPage.login(ApplicationConfigReader.getPerformanceGlitchUser(), ApplicationConfigReader.getPassword());

        // Verify successful login by checking for the presence of an element on the next page
        Assert.assertTrue(driver.findElement(By.cssSelector(".inventory_container")).isDisplayed(), "Login successful");
    }

    /**
     * Test method to perform a valid login with an error user and verify the product description with error.
     */
    @Test
    public void errorUserLoginTest() {
        // Perform valid login
        loginPage.login(ApplicationConfigReader.getErrorUser(), ApplicationConfigReader.getPassword());

        // Verify product description on the next page
        WebElement productDescription = driver.findElement(By.xpath("//div[@class='inventory_item_desc']"));
        String actualProductDescription = productDescription.getText();

        // Verify successful login by checking for the presence of an element on the next page
        Assert.assertTrue(actualProductDescription.contains("carry.allTheThings()"));
    }

    /**
     * Test method to perform login with a visual user and verify the visual failure element.
     */
    @Test
    public void visualUserLoginTest() {
        // Perform invalid login
        loginPage.login(ApplicationConfigReader.getVisualUser(), ApplicationConfigReader.getPassword());

        // Verify the presence of the visual failure element
        WebElement shoppingCartContainer = driver.findElement(By.cssSelector(".shopping_cart_container.visual_failure"));
        Assert.assertTrue(shoppingCartContainer.isDisplayed());
    }
}