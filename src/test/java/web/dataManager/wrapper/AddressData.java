package web.dataManager.wrapper;

import web.dataManager.TestDataManager;
import web.dataManager.pojos.Address;

/**
 * @author Ajay Talpur
 */
public final class AddressData {

    private AddressData() {
    }

    public static Address getAddress(String addressType) {

        return TestDataManager.getObject(
                "address",
                Address.class,
                addressType.toLowerCase());
    }

    public static Address home() {
        return getAddress("home");
    }

    public static Address office() {
        return getAddress("office");
    }
}