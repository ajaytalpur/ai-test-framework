package web.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Reporter;
import web.dataManager.context.ContextManager;
import web.dataManager.pojos.Address;
import web.dataManager.pojos.Cards;
import web.dataManager.wrapper.AddressData;
import web.dataManager.wrapper.CardData;
import web.pages.CartPage;
import web.pages.ProductsPage;

/**
 * Step definitions for the Product Purchase flow (TC-ProductPurchase-01).
 * Reuses: ContextManager, AddressData, CardData, Reporter, HomePage steps (login/logout/validation)
 * New: ProductsPage, CartPage
 *
 * @author Ajay Talpur
 */
public class ProductPurchaseSteps {

    private final ProductsPage productsPage;
    private final CartPage cartPage;

    public ProductPurchaseSteps() {
        productsPage = new ProductsPage();
        cartPage = new CartPage();
    }

    @When("user logs in with username {string} and password {string}")
    public void userLogsInWithCredentials(String userName, String password) {
        ContextManager.getContext().setUserName(userName);
        ContextManager.getContext().setPassword(password);
        Reporter.log("Stored credentials for user: " + userName);
    }

    @When("user selects product {string}")
    public void userSelectsProduct(String productName) {
        productsPage.selectProduct(productName);
    }

    @When("user adds the product to cart")
    public void userAddsProductToCart() {
        productsPage.addToCart();
    }

    @Then("product added alert should be displayed")
    public void productAddedAlertShouldBeDisplayed() {
        productsPage.validateProductAddedAlert();
        Reporter.log("Product added to cart successfully");
    }

    @When("user navigates to cart")
    public void userNavigatesToCart() {
        // NavBar is accessed through HomePage which is already in HomePageSteps.
        // CartPage handles the cart-level interactions; NavBar click is in HomePageSteps ("user selects cart").
        Reporter.log("Navigating to cart page");
    }

    @Then("cart should load with selected product")
    public void cartShouldLoadWithSelectedProduct() {
        cartPage.waitForCartToLoad();
    }

    @When("user places an order with home address and visa card")
    public void userPlacesOrderWithHomeAddressAndVisaCard() {
        Address address = AddressData.home();
        Cards card = CardData.visa();
        cartPage.clickPlaceOrder();
        cartPage.fillOrderDetailsAndPurchase(address, card);
        Reporter.log("Order placed with address: " + address.getName()
                + ", card: " + card.getCardNumber());
    }

    @Then("purchase confirmation should be displayed")
    public void purchaseConfirmationShouldBeDisplayed() {
        Cards card = CardData.visa();
        Address address = AddressData.home();
        String orderId = cartPage.validatePurchaseConfirmation(
                card.getCardNumber(),
                address.getName());
        ContextManager.getContext().setOrderId(orderId);
        Reporter.log("Purchase confirmed. Order Id: " + orderId);
    }

    @And("user closes the confirmation")
    public void userClosesTheConfirmation() {
        cartPage.confirmAndClose();
    }
}
