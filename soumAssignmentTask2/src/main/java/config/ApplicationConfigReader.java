package config;

import java.io.InputStream;
import java.util.Properties;

/**
 * ApplicationConfigReader class reads configuration properties from the
 * "ApplicationConfig.properties" file.
 */
public class ApplicationConfigReader {
    // Properties object to store configuration properties
    private static final Properties properties = new Properties();

    static {
        // Load configuration properties when the class is initialized
        try (InputStream input = ApplicationConfigReader.class.getClassLoader().getResourceAsStream("ApplicationConfig.properties")) {
            properties.load(input);
        } catch (Exception e) {
            // Handle any exception that might occur during the loading of properties
            e.printStackTrace();
        }
    }

    /**
     * Get the protocol from the configuration properties.
     * @return The protocol value.
     */
    public static String getProtocol() {
        return properties.getProperty("protocol");
    }

    /**
     * Get the base URL from the configuration properties.
     * @return The base URL value.
     */
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    /**
     * Get the path from the configuration properties.
     * @return The path value.
     */
    public static String getPath() {
        return properties.getProperty("path");
    }

    /**
     * Get the API version from the configuration properties.
     * @return The API version value.
     */
    public static String getApiVersion() {
        return properties.getProperty("apiVersion");
    }

    /**
     * Get the size of the product from the configuration properties.
     * @return The size of the product value.
     */
    public static String getSizeOfProduct() {
        return properties.getProperty("sizeOfProduct");
    }
}