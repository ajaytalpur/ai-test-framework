package utilities;

import driverConfig.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Ajay Talpur
 */
public class Util {
    public Util() {
    }

    public static boolean isLocalMachine() {
        ConfigReader ConfigReader = new ConfigReader("config");
        return ConfigReader.getProperty("executionType").equalsIgnoreCase("local");
    }

    public static String getTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

    public static byte[] attachScreenshot() {
        byte[] screenshot =
                ((TakesScreenshot)
                        WebDriverFactory.getDriver())
                        .getScreenshotAs(
                                OutputType.BYTES);

        ScenarioManager.getScenario().attach(
                        screenshot,
                        "image/png",
                        "Fail_" + Util.getTimeStamp());

        return screenshot;
    }
}
