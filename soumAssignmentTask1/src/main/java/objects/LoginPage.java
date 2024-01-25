package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the page where users can log in to the application.
 */
public class LoginPage {

    private final WebDriver driver;

    /**
     * Initializes a new instance of the LoginPage class.
     * @param driver The WebDriver instance to interact with the browser.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Logs in to the application with the provided username and password.
     * @param username The username used for login.
     * @param password The password associated with the username.
     */
    public void login(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }
}