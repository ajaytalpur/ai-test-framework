package web.steps;

import io.cucumber.java.en.Then;
import web.dataManager.context.ContextManager;
import web.pages.SignInPage;

/**
 * @author Ajay Talpur
 */
public class LoginSteps {
    private SignInPage signInPage;

    public LoginSteps() {
        signInPage = new SignInPage();
    }

    @Then("Registered user should sign in")
    public void signupShouldBeSuccessful() {
        String user = ContextManager.getContext().getUserName();
        String password = ContextManager.getContext().getPassword();
        signInPage.signIn(user, password);
    }
}
