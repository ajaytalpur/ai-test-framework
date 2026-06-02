package driverConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ajay Talpur
 */
public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    private WebDriverFactory() {
    }

    public static void createDriver() {
        String executionType = RuntimeConfig.getExecutionType();
        String browser = RuntimeConfig.getBrowser();

        switch (executionType.toLowerCase()) {
            case "local":
                initializeLocalDriver(browser);
                break;
            case "browserstack":
                initializeBrowserStackDriver(browser);
                break;
            default:
                throw new RuntimeException(
                        "Unsupported execution type : "
                                + executionType);
        }
    }

    public static WebDriver getDriver() {

        if (driver.get() == null) {
            throw new RuntimeException(
                    "Driver not initialized. Call initDriver() first.");
        }

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            driver.remove();
        }
    }

    private static void initializeLocalDriver(String browser) {

        switch (browser.toLowerCase()) {
            case "chrome":
                driver.set(new ChromeDriver(setChromeOptions()));
                break;

            case "firefox":
                driver.set(new FirefoxDriver());
                break;

            case "safari":
                driver.set(new SafariDriver());
                break;

            default:
                throw new RuntimeException(
                        "Unsupported browser : "
                                + browser);
        }
    }

    private static ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-password-generation");
        options.addArguments("--disable-features=PasswordLeakDetection");

        options.setExperimentalOption("prefs", prefs);

        return options;
    }

    private static void initializeBrowserStackDriver(
            String browser) {

        /*
         BrowserStack implementation goes here

         Example:

         ChromeOptions options = new ChromeOptions();

         options.setCapability(
                 "browserName",
                 browser);

         driver.set(
             new RemoteWebDriver(
                 new URL(BROWSERSTACK_URL),
                 options));
        */

        throw new UnsupportedOperationException(
                "BrowserStack implementation pending");
    }
}