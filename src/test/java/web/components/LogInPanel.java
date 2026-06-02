package web.components;

import driverConfig.Actions;
import enums.Timeouts;
import org.openqa.selenium.By;

public class LogInPanel {

    private By logInHeading = By.cssSelector("#logInModalLabel");
    private By userNameInput = By.cssSelector("#loginusername");
    private By passwordInput = By.cssSelector("#loginpassword");
    private By logInButton = By.cssSelector("button[onclick='logIn()']");


    public void logIn(String user,String userPassword){
        Actions.waitTillVisible(logInHeading, Timeouts.FIVE_SECONDS.getDuration());
        Actions.type(userNameInput,user,"Username input");
        Actions.type(passwordInput,userPassword,"Password input");
        Actions.click(logInButton,"Login button");
    }

}
