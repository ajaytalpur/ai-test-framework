package web.pages;

import driverConfig.Actions;
import enums.Timeouts;
import org.openqa.selenium.By;
import utilities.Reporter;

/**
 * Page object for the Products listing and Product detail page on demoblaze.com.
 * Handles product selection, Add to Cart, and the add-to-cart alert confirmation.
 *
 * @author Ajay Talpur
 */
public class ProductsPage {

    private final By addToCartButton = By.cssSelector("a[onclick='addToCart(1)']");

    /**
     * Selects a product by its visible link text from the product listing.
     *
     * @param productName  exact visible name of the product (e.g. "Samsung galaxy s6")
     */
    public void selectProduct(String productName) {
        By productLink = By.linkText(productName);
        Actions.waitTillClickable(productLink, "Product: " + productName);
        Actions.click(productLink, "Product: " + productName);
        Reporter.log("Selected product: " + productName);
    }

    /**
     * Clicks the Add to Cart button on the product detail page.
     */
    public void addToCart() {
        Actions.waitTillClickable(addToCartButton, "Add to Cart button");
        Actions.click(addToCartButton, "Add to Cart button");
    }

    /**
     * Validates the "Product added." alert message and accepts it.
     */
    public void validateProductAddedAlert() {
        String alertText = Actions.getAlertTextAndAccept();
        Reporter.log("Add to cart alert: " + alertText);
        assertions.AssertUtils.assertTrue(
                alertText.toLowerCase().contains("product added"),
                "Expected 'Product added.' alert. Actual: " + alertText);
    }
}
