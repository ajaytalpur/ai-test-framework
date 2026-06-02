package web.pages;

import assertions.AssertUtils;
import web.components.SignUpPanel;

/**
 * @author Ajay Talpur
 */
public class SignUpPage {
    private SignUpPanel signUpPanel;

    public SignUpPage() {
        signUpPanel = new SignUpPanel();
    }

    public void userSignup(String userName, String password) {
        signUpPanel.signUp(userName, password);
    }

    public void validateSignUpMessage() {
        String expectedMessage = signUpPanel.getSignUpSuccessMessage();
        String actualMessage = "Sign up successful";
        AssertUtils.assertTrue(expectedMessage.toLowerCase().trim().contains(actualMessage.toLowerCase()),
                "Expected: "+expectedMessage+" Actual: "+actualMessage);

    }
}
