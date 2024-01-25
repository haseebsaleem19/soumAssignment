package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the page where the user can proceed to checkout and fill out checkout information.
 */
public class CheckoutPage {

    private final WebDriver driver;

    /**
     * Initializes a new instance of the CheckoutPage class.
     * @param driver The WebDriver instance to interact with the browser.
     */
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks the shopping cart container to view the items in the cart before checkout.
     */
    public void clickShoppingCartContainer() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    /**
     * Clicks the "Checkout" button to initiate the checkout process.
     */
    public void clickCheckoutButton() {
        driver.findElement(By.id("checkout")).click();
    }

    /**
     * Fills out checkout information with the provided first name, last name, and zip code.
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param zipCode   The zip code of the user.
     */
    public void fillCheckOutInformation(String firstName, String lastName, String zipCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(zipCode);
        driver.findElement(By.id("continue")).click();
    }

    /**
     * Clicks the "Finish" button to complete the checkout process.
     */
    public void clickFinishButton() {
        driver.findElement(By.id("finish")).click();
    }

}