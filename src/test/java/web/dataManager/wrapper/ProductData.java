package web.dataManager.wrapper;

import web.dataManager.TestDataManager;
import io.cucumber.messages.types.Product;

/**
 * @author Ajay Talpur
 */
public class ProductData {

    public static Product getLaptop(String brand) {
        return TestDataManager.getObject("products", Product.class, "laptops", brand);
    }

    public static Product getPhones(String brand) {
        return TestDataManager.getObject("products", Product.class, "phones", brand);
    }
}
