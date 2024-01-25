package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the page where the user can interact with the left drawer menu.
 */
public class DrawerPage {

    private final WebDriver driver;

    /**
     * Initializes a new instance of the DrawerPage class.
     * @param driver The WebDriver instance to interact with the browser.
     */
    public DrawerPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks the button to open the left drawer menu.
     */
    public void clickOpenLeftDrawer() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }

    /**
     * Clicks the "Logout" button in the left drawer menu.
     */
    public void clickLogoutButtonLeftDrawer() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    /**
     * Clicks the "About" button in the left drawer menu.
     */
    public void clickAboutButtonLeftDrawer() {
        driver.findElement(By.id("about_sidebar_link")).click();
    }

    /**
     * Clicks the "Reset" button in the left drawer menu.
     */
    public void clickResetButtonLeftDrawer() {
        driver.findElement(By.id("reset_sidebar_link")).click();
    }
}
