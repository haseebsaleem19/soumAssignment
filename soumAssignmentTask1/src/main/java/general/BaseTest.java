/**
 * BaseTest class provides the base setup and teardown methods for WebDriver tests.
 * It includes methods for initializing WebDriver, setting up implicit wait, opening the application URL,
 * maximizing the browser window, and tearing down the WebDriver instance after the test execution.
 *
 * This class serves as the foundation for other test classes in the project.
 */
package general;

import config.ApplicationConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    /**
     Setup method to initialize WebDriver, set implicit wait, open the application URL, and maximize the window.
     */
    @BeforeMethod
    public void setup() {
        driver = WebDriverFactory.createWebDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ApplicationConfigReader.getUrl());
        driver.manage().window().maximize();
    }

    /**
     Teardown method to quit the WebDriver instance after the test execution.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}