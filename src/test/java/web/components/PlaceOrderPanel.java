package web.components;

import assertions.AssertUtils;
import driverConfig.Actions;
import enums.Timeouts;
import org.openqa.selenium.By;
import utilities.Reporter;
import web.dataManager.pojos.Address;
import web.dataManager.pojos.Cards;

/**
 * Component representing the Place Order modal panel on the Cart page.
 *
 * @author Ajay Talpur
 */
public class PlaceOrderPanel {

    private final By placeOrderHeading = By.cssSelector("#orderModalLabel");
    private final By nameInput         = By.cssSelector("#name");
    private final By countryInput      = By.cssSelector("#country");
    private final By cityInput         = By.cssSelector("#city");
    private final By cardInput         = By.cssSelector("#card");
    private final By monthInput        = By.cssSelector("#month");
    private final By yearInput         = By.cssSelector("#year");
    private final By purchaseButton    = By.cssSelector("button[onclick='purchaseOrder()']");

    /**
     * Waits for the Place Order panel to be visible.
     */
    public void waitForPanelToLoad() {
        Actions.waitTillVisible(placeOrderHeading, Timeouts.TEN_SECONDS.getDuration());
    }

    /**
     * Fills in the order form using Address and Cards data objects, then clicks Purchase.
     *
     * @param address  Address POJO (name, country, city)
     * @param card     Cards POJO (cardNumber, month, year)
     */
    public void fillOrderDetailsAndPurchase(Address address, Cards card) {
        waitForPanelToLoad();
        Actions.type(nameInput,    address.getName(),    "Name input");
        Actions.type(countryInput, address.getCountry(), "Country input");
        Actions.type(cityInput,    address.getCity(),    "City input");
        Actions.type(cardInput,    card.getCardNumber(), "Credit card input");
        Actions.type(monthInput,   card.getMonth(),      "Month input");
        Actions.type(yearInput,    card.getYear(),       "Year input");
        Reporter.log("Order details filled - Name: " + address.getName()
                + " | Country: " + address.getCountry()
                + " | City: " + address.getCity()
                + " | Card: " + card.getCardNumber());
        Actions.click(purchaseButton, "Purchase button");
    }
}
