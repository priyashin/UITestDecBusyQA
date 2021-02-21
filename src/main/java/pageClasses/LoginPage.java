package pageClasses;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    // First Approach> Page Object Model
    WebDriver driver;
    String errormsg;

    By user = By.id("txtUsername");
    By pass = By.id("txtPassword");
    By login = By.id("btnLogin");
    By message = By.xpath("//span[text()='Invalid credentials']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // page methods that define the actions (click(), sendKeys(), etc.)

    public String checkInvalidLogin(String username, String password){

        driver.findElement(user).sendKeys(username);
        driver.findElement(pass).sendKeys(password);
        driver.findElement(login).click();
        errormsg = driver.findElement(message).getText();

        return errormsg;
    }


}
