package web.pages;

import assertions.AssertUtils;
import web.components.LogInPanel;
import web.components.NavBar;

/**
 * @author Ajay Talpur
 */
public class HomePage {

    private LogInPanel logInPanel;
    private NavBar navBar;

    public HomePage() {
        logInPanel = new LogInPanel();
        navBar = new NavBar();

    }

    public void selectSignUp() {
        navBar.selectSignUp();
    }

    public void selectSignIn() {
        navBar.selectLogIn();
    }

    public void userLogIn(String userName, String password) {
        navBar.selectLogIn();
        logInPanel.logIn(userName, password);
    }

    public void validateLoggedInUser(String expectedUser) {
        navBar.waitForNavBarToLoad();
        String displayedText = navBar.getUserNameFromNavBar();
        AssertUtils.assertTrue(displayedText.toLowerCase().trim().contains(expectedUser.toLowerCase().trim()), "Expected: "+expectedUser+" Actual: "+displayedText);
    }

    public void logOut(){
        navBar.logOut();
    }

}
