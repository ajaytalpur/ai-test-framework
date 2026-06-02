package web.components;

import assertions.AssertUtils;
import driverConfig.Actions;
import enums.Timeouts;
import org.openqa.selenium.By;
import utilities.Reporter;

/**
 * Component representing the Order Confirmation modal panel shown after a successful purchase.
 *
 * @author Ajay Talpur
 */
public class ConfirmOrderPanel {

    private final By confirmationPanel = By.cssSelector("div.sweet-alert");
    private final By thankYouHeading   = By.cssSelector("div.sweet-alert h2");
    private final By confirmationBody  = By.cssSelector("div.sweet-alert p.lead");
    private final By okButton          = By.cssSelector("button[class='confirm btn btn-lg btn-primary']");

    /**
     * Waits for the confirmation panel to appear.
     */
    public void waitForPanelToLoad() {
        Actions.waitTillVisible(confirmationPanel, Timeouts.TEN_SECONDS.getDuration());
    }

    /**
     * Returns the thank-you heading text (e.g. "Thank you for your purchase!").
     */
    public String getThankYouText() {
        return Actions.getText(thankYouHeading, "Thank you heading");
    }

    /**
     * Returns the full confirmation body text containing Id, Amount, Card Number, Name.
     */
    public String getConfirmationBodyText() {
        return Actions.getText(confirmationBody, "Confirmation body");
    }

    /**
     * Validates all expected fields in the confirmation panel.
     *
     * @param expectedCardNumber  partial card number expected in the confirmation text
     * @param expectedName        name expected in the confirmation text
     */
    public String validateAndGetOrderId(String expectedCardNumber, String expectedName) {
        waitForPanelToLoad();

        String heading = getThankYouText();
        AssertUtils.assertTrue(
                heading.toLowerCase().contains("thank you for your purchase"),
                "Expected 'Thank you for your purchase!' heading. Actual: " + heading);
        Reporter.log("Confirmed heading: " + heading);

        String body = getConfirmationBodyText();

        AssertUtils.assertTrue(
                body.contains("Id:"),
                "Order Id not found in confirmation. Body: " + body);

        AssertUtils.assertTrue(
                body.toLowerCase().contains("amount:"),
                "Amount not found in confirmation. Body: " + body);

        AssertUtils.assertTrue(
                body.contains(expectedCardNumber),
                "Card number [" + expectedCardNumber + "] not found in confirmation. Body: " + body);

        AssertUtils.assertTrue(
                body.toLowerCase().contains(expectedName.toLowerCase()),
                "Name [" + expectedName + "] not found in confirmation. Body: " + body);

        Reporter.log("Order confirmation validated. Body: " + body);

        // Extract and return the order Id for context storage
        String orderId = extractOrderId(body);
        Reporter.log("Order Id: " + orderId);
        return orderId;
    }

    /**
     * Clicks the OK button to dismiss the confirmation panel.
     */
    public void clickOk() {
        Actions.jsClick(okButton, "Confirmation OK button");
    }

    public void waitUntilOrderIdPanelClosed() {
        Actions.waitTillNotClickable(okButton, "OK button");
        Actions.waitUntilTextNotToDisplay(thankYouHeading, "Thank you for your purchase!","OK button");
        Actions.refreshPage();
    }

    /**
     * Extracts the order Id value from the confirmation body text.
     * Expected format: "Id: 1234567"
     */
    private String extractOrderId(String bodyText) {
        try {
            String idLine = bodyText.lines()
                    .filter(line -> line.trim().startsWith("Id:"))
                    .findFirst()
                    .orElse("");
            return idLine.replace("Id:", "").trim();
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }
}
