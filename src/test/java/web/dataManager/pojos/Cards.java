package web.dataManager.pojos;

/**
 * @author Ajay Talpur
 */
public class Cards {
    private String cardNumber;
    private String month;
    private String year;

    public Cards() {
    }

    public Cards(String cardNumber, String month, String year) {
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
