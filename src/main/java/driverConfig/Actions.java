package driverConfig;


import enums.Timeouts;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Util;

import java.time.Duration;
import java.util.List;

/**
 * @author Ajay Talpur
 */
public class Actions {

    public static void click(By locator, String message) {
        try {
            waitTillClickable(locator, Timeouts.FIVE_SECONDS.getDuration());
            getWebElement(locator).click();
        } catch (Exception e) {
            throw stopTestException("Unable to click [" + locator + "] - " + message, e);
        }
    }

    public static String getText(By locator, String message) {
        try {
            waitTillVisible(locator, Timeouts.FIVE_SECONDS.getDuration());
            return getWebElement(locator).getText();
        } catch (Exception e) {
            throw stopTestException("Unable to getText from [" + locator + "] - " + message, e);
        }
    }

    public static String getAlertTextAndAccept() {

        try {

            Alert alert = new WebDriverWait(
                    WebDriverFactory.getDriver(),
                    Timeouts.TEN_SECONDS.getDuration())
                    .until(ExpectedConditions.alertIsPresent());

            String text = alert.getText();

            alert.accept();

            return text;

        } catch (Exception e) {

            throw stopTestException(
                    "Unable to read alert",
                    e);
        }
    }

    public static void type(By locator, String value, String message) {
        try {
            waitTillClickable(locator, Timeouts.FIVE_SECONDS.getDuration());
            WebElement element = getWebElement(locator);
            element.clear();
            element.sendKeys(value);

        } catch (Exception e) {
            throw stopTestException("Unable to type [" + locator + "] - " + message, e);
        }
    }

    public static List<WebElement> getWebElements(By locator) {
        return WebDriverFactory.getDriver().findElements(locator);
    }

    public static WebElement getWebElement(By locator) {
        return WebDriverFactory.getDriver()
                .findElement(locator);
    }


    public static void waitTillClickable(By locator, String message) {
        try {
            waitTillClickable(locator, Timeouts.FIVE_SECONDS.getDuration());
        } catch (Exception e) {
            throw stopTestException("Unable to wait for [" + locator + "] - " + message, e);
        }
    }

    public static void waitTillNotClickable(By locator, String message) {
        try {
            waitTillNotClickable(locator, Timeouts.FIVE_SECONDS.getDuration());
        } catch (Exception e) {
            throw stopTestException("Unable to wait for [" + locator + "] - " + message, e);
        }
    }

    public static void waitUntilTextToDisplay(By locator, String textToDisplay,String message) {
        try {
            waitUntilTextToDisplay(locator, textToDisplay, Timeouts.FIVE_SECONDS.getDuration());
        } catch (Exception e) {
            throw stopTestException("Unable to wait for [" + locator + "] - " + message, e);
        }
    }

    public static void waitTillNotClickable(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), timeout);
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)));
    }

    public static void waitTillClickable(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitUntilTextToDisplay(By locator, String text, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), timeout);
        wait.until(ExpectedConditions.textToBePresentInElement(getWebElement(locator), text));
    }

    public static void waitTillVisible(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static RuntimeException stopTestException(String message, Throwable e) {
        Util.attachScreenshot();
        return new RuntimeException(message, e);
    }

}
