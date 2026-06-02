package web.steps;

import utilities.Reporter;
import web.dataManager.context.ContextManager;
import io.cucumber.java.en.When;
import web.pages.HomePage;

/**
 * @author Ajay Talpur
 */
public class HomePageSteps {
    private final HomePage homePage;
    public HomePageSteps() {
        homePage = new HomePage();
    }


    @When("user selects signup")
    public void selectSignupOption() {
        homePage.selectSignUp();
    }

    @When("user selects login")
    public void selectLoginOption() {
        homePage.selectSignIn();
    }

    @When("user should sign in successfully")
    public void successfulSignIn() {
        String user = ContextManager.getContext().getUserName();
        homePage.validateLoggedInUser(user);
        Reporter.log("Successfully Logged in with User: "+ContextManager.getContext().getUserName());
    }

    @When("user should Logout")
    public void userLogout() {
        homePage.logOut();
        Reporter.log("Successfully Logged out with User: "+ContextManager.getContext().getUserName());
    }
}
