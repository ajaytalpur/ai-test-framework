package driverConfig;

import utilities.ConfigReader;

/**
 * @author Ajay Talpur
 */
public class RuntimeConfig {
    public RuntimeConfig() {

    }

    private static final ConfigReader CONFIG = new ConfigReader("config");

    public static String getBrowser() {
        return System.getProperty("browser", CONFIG.getProperty("browser"));
    }

    public static String getExecutionType(){
        return System.getProperty("executionType", CONFIG.getProperty("executionType"));
    }

    public static String getURL(){
        return System.getProperty( "url", CONFIG.getProperty("url"));
    }
}
