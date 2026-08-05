package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigReader {
    private static final Logger logger =
            LoggerFactory.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    // Default environment is QA if not provided
    private static final String ENV =
            System.getProperty("env", "qa");

    // Dynamic file path
    private static final String FILE_NAME =
            "config/" + ENV + ".properties";

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(FILE_NAME)) {

            if (input == null) {
                throw new RuntimeException(
                        "Configuration file not found : " + FILE_NAME);
            }

            properties.load(input);

            // Console logs (helpful while learning)
            logger.info("====================================");
            logger.info("Environment : {}", ENV);
            logger.info("Configuration : {}", FILE_NAME);
            logger.info("Browser : {}", get("browser"));
            logger.info("Base URL : {}", get("base.url"));
            logger.info("====================================");

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to load configuration file : " + FILE_NAME,
                    e);
        }
    }

    /**
     * Returns property value as String
     */
    public static String get(String key) {

        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException(
                    "Property '" + key + "' not found in "
                            + FILE_NAME);
        }

        return value.trim();
    }

    /**
     * Returns property value as Integer
     */
    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    /**
     * Returns property value as Boolean
     */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}