package web.pages;

import driverConfig.Actions;
import enums.Timeouts;
import org.openqa.selenium.By;
import utilities.Reporter;
import web.components.ConfirmOrderPanel;
import web.components.PlaceOrderPanel;
import web.dataManager.pojos.Address;
import web.dataManager.pojos.Cards;

/**
 * Page object for the Cart page on demoblaze.com.
 * Handles waiting for cart items to load, placing an order, and confirming the purchase.
 *
 * @author Ajay Talpur
 */
public class CartPage {

    private final By placeOrderButton  = By.cssSelector("button[data-target='#orderModal']");
    private final By cartProductDelete = By.linkText("Delete");

    private final PlaceOrderPanel  placeOrderPanel  = new PlaceOrderPanel();
    private final ConfirmOrderPanel confirmOrderPanel = new ConfirmOrderPanel();

    /**
     * Waits until at least one product with a Delete link is visible in the cart.
     * This confirms the cart has loaded the selected product.
     */
    public void waitForCartToLoad() {
        Actions.waitTillVisible(cartProductDelete, Timeouts.TEN_SECONDS.getDuration());
        Reporter.log("Cart loaded with product");
    }

    /**
     * Clicks the Place Order button to open the Place Order modal.
     */
    public void clickPlaceOrder() {
        Actions.click(placeOrderButton, "Place Order button");
        Reporter.log("Clicked Place Order");
    }

    /**
     * Fills in the Place Order form using Address and Card data, then clicks Purchase.
     *
     * @param address  Address POJO
     * @param card     Cards POJO
     */
    public void fillOrderDetailsAndPurchase(Address address, Cards card) {
        placeOrderPanel.fillOrderDetailsAndPurchase(address, card);
    }

    /**
     * Validates the purchase confirmation panel and returns the captured order Id.
     *
     * @param expectedCardNumber  partial card number for validation
     * @param expectedName        name used in the order for validation
     * @return  the order Id extracted from the confirmation panel
     */
    public String validatePurchaseConfirmation(String expectedCardNumber, String expectedName) {
        return confirmOrderPanel.validateAndGetOrderId(expectedCardNumber, expectedName);
    }

    /**
     * Clicks the OK button on the confirmation panel to close it.
     */
    public void confirmAndClose() {
        confirmOrderPanel.clickOk();
        confirmOrderPanel.waitUntilOrderIdPanelClosed();
        Reporter.log("Purchase confirmation dismissed");
    }
}
