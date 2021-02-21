package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage1 {

    // Second Approach > POM with Page Factory

    String msg;

    @FindBy(id="txtUsername")
    WebElement user;

    @FindBy(id="txtPassword")
    WebElement pass;

    @FindBy(how = How.ID, using = "btnLogin") // @FindBy(id="loginBtn")
    WebElement login;

    @FindBy(xpath="//span[text()='Invalid credentials']")
    WebElement errormsg;

    public LoginPage1(WebDriver driver){
        PageFactory.initElements(driver, this); // this always refer to the current class object
    }

    public String checkInvalidLogin(String username, String password){

        user.sendKeys(username);
        pass.sendKeys(password);
        login.click();
        msg = errormsg.getText();

        return msg;
    }

    public String checkValidLogin(String username, String password){

        user.sendKeys(username);
        pass.sendKeys(password);
        login.click();

        return "Success";
    }



}
