package assertions;

import driverConfig.Actions;
import org.testng.Assert;

/**
 * @author Ajay Talpur
 */
public final class AssertUtils {

    private AssertUtils() {
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            throw Actions.stopTestException(message, e);
        }
    }

    public static void assertNotEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertNotEquals(actual, expected, message);
        } catch (AssertionError e) {
            throw Actions.stopTestException(message, e);
        }
    }

    public static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
        } catch (AssertionError e) {
            throw Actions.stopTestException(message, e);
        }
    }

    public static void assertFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
        } catch (AssertionError e) {
            throw Actions.stopTestException(message, e);
        }
    }

    public static void assertNull(Object object, String message) {
        try {
            Assert.assertNull(object, message);
        } catch (AssertionError e) {
            throw Actions.stopTestException(message, e);
        }
    }

    public static void assertNotNull(Object object, String message) {
        try {
            Assert.assertNotNull(object, message);
        } catch (AssertionError e) {
            throw Actions.stopTestException(message, e);
        }
    }
}