package web.components;

import driverConfig.Actions;
import enums.Timeouts;
import org.openqa.selenium.By;

public class SignUpPanel {

    private By signUpHeading = By.cssSelector("#signInModalLabel");
    private By signUpUserNameInput = By.cssSelector("#sign-username");
    private By signUpPasswordInput = By.cssSelector("#sign-password");
    private By signUpButton = By.cssSelector("button[onclick='register()']");


    public void signUp(String user, String userPassword) {
        Actions.waitTillVisible(signUpHeading, Timeouts.FIVE_SECONDS.getDuration());
        Actions.type(signUpUserNameInput, user, "Sign Up Username input");
        Actions.type(signUpPasswordInput, userPassword, "Sign Up Password input");
        Actions.click(signUpButton, "Sign Up button");

    }

    public String getSignUpSuccessMessage() {
        return Actions.getAlertTextAndAccept();
    }
}
