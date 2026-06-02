package web.dataManager.wrapper;


import web.dataManager.TestDataManager;
import web.dataManager.pojos.Cards;

/**
 * @author Ajay Talpur
 */
public final class CardData {

    private CardData() {
    }

    public static Cards getCard(String cardType) {
        return TestDataManager.getObject(
                "cards",
                Cards.class,
                cardType.toLowerCase());
    }

    public static Cards visa() {
        return getCard("visa");
    }

    public static Cards masterCard() {
        return getCard("mastercard");
    }
}