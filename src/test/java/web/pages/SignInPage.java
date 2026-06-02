package web.pages;

import web.components.LogInPanel;

/**
 * @author Ajay Talpur
 */
public class SignInPage {
    private LogInPanel logInPanel ;

    public SignInPage() {
        logInPanel = new LogInPanel();
    }

    public void signIn(String userName, String password){
        logInPanel.logIn(userName,password);
    }
}
