/**
 * ApplicationConfigReader class is responsible for reading configuration properties from the "ApplicationConfig.properties" file.
 * It uses a static initialization block to load the properties from the file when the class is first loaded.
 * The class provides methods to retrieve various configuration values such as URL, username, password, browser, inventory URL,
 * user details (first name, last name, zip code), and specific user names for testing scenarios.
 *
 * Usage Example:
 * - String url = ApplicationConfigReader.getUrl();
 * - String username = ApplicationConfigReader.getUsername();
 * - String password = ApplicationConfigReader.getPassword();
 * - String browser = ApplicationConfigReader.getBrowser();
 * - String inventoryUrl = ApplicationConfigReader.getInventoryUrl();
 * - String firstName = ApplicationConfigReader.getFirstName();
 * - String lastName = ApplicationConfigReader.getLastName();
 * - String zipCode = ApplicationConfigReader.getZipCode();
 * - String lockedOutUser = ApplicationConfigReader.getLockedOutUser();
 * - String problemUser = ApplicationConfigReader.getProblemUser();
 * - String performanceGlitchUser = ApplicationConfigReader.getPerformanceGlitchUser();
 * - String errorUser = ApplicationConfigReader.getErrorUser();
 * - String visualUser = ApplicationConfigReader.getVisualUser();
 */
package config;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ApplicationConfigReader.class.getClassLoader().getResourceAsStream("ApplicationConfig.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Methods for retrieving configuration values

    public static String getUrl() {
        return properties.getProperty("url");
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static String getInventoryUrl() {
        return properties.getProperty("inventoryUrl");
    }

    public static String getFirstName() {
        return properties.getProperty("firstName");
    }

    public static String getLastName() {
        return properties.getProperty("lastName");
    }

    public static String getZipCode() {
        return properties.getProperty("zipCode");
    }

    public static String getLockedOutUser() {
        return properties.getProperty("lockedOutUser");
    }

    public static String getProblemUser() {
        return properties.getProperty("problemUser");
    }

    public static String getPerformanceGlitchUser() {
        return properties.getProperty("performanceGlitchUser");
    }

    public static String getErrorUser() {
        return properties.getProperty("errorUser");
    }

    public static String getVisualUser() {
        return properties.getProperty("visualUser");
    }
}