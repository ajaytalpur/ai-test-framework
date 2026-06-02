package web.components;

import driverConfig.Actions;
import org.openqa.selenium.By;

public class NavBar {

    private final By demoLogo = By.cssSelector("a[id=nava] img");
    private final By home = By.xpath("//a[contains(text(),'Home')]");
    private final By signUp = By.cssSelector("#signin2");
    private final By logIn = By.cssSelector("#login2");
    private final By logOut = By.cssSelector("#logout2");
    private final By cart = By.cssSelector("#cartur");
    private final By loggedInUser = By.cssSelector("#nameofuser");


    public void waitForNavBarToLoad() {
        Actions.waitTillClickable(home,"Home Link");
    }

    public void clickMainLogo() {
        Actions.click(demoLogo, "Main Logo");
    }

    public void selectHome() {
        Actions.click(home, "Home link");
    }

    public void logOut() {
        Actions.click(logOut, "Log Out");
    }

    public void selectCart() {
        Actions.click(cart, "Cart link");
    }

    public void selectLogIn() {
        Actions.click(logIn, "Login link");
    }

    public void selectSignUp() {
        Actions.click(signUp, "SignUp link");
    }

    public String getUserNameFromNavBar() {
        Actions.waitTillClickable(logOut,"Logout Link");
        return Actions.getText(loggedInUser, "logged in user");
    }
}
