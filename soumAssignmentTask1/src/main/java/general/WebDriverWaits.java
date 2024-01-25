package general;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class for handling WebDriver waits and sleep.
 */
public class WebDriverWaits {

    /**
     * Static instance of WebDriverWait.
     */
    public static WebDriverWait wait;

    /**
     * Pauses the execution for the specified time.
     * @param time Time to sleep in milliseconds.
     */
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits for the specified ExpectedCondition to be true for a maximum timeout.
     * @param webElementExpectedCondition The condition to wait for.
     * @return WebElement once the condition is met.
     */
    public static WebElement waitFor(ExpectedCondition <
            WebElement > webElementExpectedCondition) {
        // Using a default timeout of 10 seconds
        wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(10));
        return wait.until(webElementExpectedCondition);
    }
}