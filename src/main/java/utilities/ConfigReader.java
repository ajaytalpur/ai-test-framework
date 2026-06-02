package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Ajay Talpur
 */
public class ConfigReader {

    private final Properties properties;

    public ConfigReader(String propertiesFileName) {
        properties = new Properties();
        String filePath = System.getProperty("user.dir")
                        + "/"
                        + propertiesFileName
                        + ".properties";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load file : " + propertiesFileName, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}