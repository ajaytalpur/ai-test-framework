package web.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Reporter;
import utilities.Util;
import web.dataManager.context.ContextManager;
import web.pages.SignUpPage;

/**
 * @author Ajay Talpur
 */
public class SignUpPageSteps {
    private final SignUpPage signUpPage;

    public SignUpPageSteps() {
        signUpPage = new SignUpPage();
    }

    @When("user {} completes signup")
    public void performNewUserSignup(String user) {

        String userName = user + "_" + Util.getTimeStamp();
        String password = "Pass_" + Util.getTimeStamp();

        ContextManager.getContext().setUserName(userName);
        ContextManager.getContext().setPassword(password);
        signUpPage.userSignup(userName, password);
    }

    @Then("signup should be successful")
    public void signupShouldBeSuccessful() {
        signUpPage.validateSignUpMessage();
        Reporter.log("Successfully Signed up with User: " + ContextManager.getContext().getUserName());
    }
}
