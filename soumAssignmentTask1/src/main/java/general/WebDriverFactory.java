package general;

import config.ApplicationConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    static WebDriver driver;

    public static WebDriver createWebDriver() {
        String browser = ApplicationConfigReader.getBrowser().toLowerCase();

        switch (browser) {
            case "chrome":
                return createChromeDriver();
            case "firefox":
                return createFirefoxDriver();
            default:
                throw new IllegalArgumentException("Invalid browser specified in the configuration: " + browser);
        }
    }

    private static WebDriver createChromeDriver() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        return new ChromeDriver();
    }

    private static WebDriver createFirefoxDriver() {
        // Set the path to your GeckoDriver executable
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        return new FirefoxDriver();
    }
    public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        else
        {
            throw new IllegalStateException("Driver has not been initialized");
        }
    }
}
