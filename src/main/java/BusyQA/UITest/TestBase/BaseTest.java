package BusyQA.UITest.TestBase;

import BusyQA.UITest.Screenshot.CaptureScreenshot;
import configReader.ConfigReader;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class BaseTest {

    public static WebDriver driver;
    CaptureScreenshot screen;

    public static void init(String browser, String env){

        String log4jconfigfilepath = System.getProperty("user.dir")+"\\Resources\\ConfigFiles\\log4j.properties";
        PropertyConfigurator.configure(log4jconfigfilepath);
        getBrowser(browser);
        getURL(env);
    }

    public static void getBrowser(String browser){

        if(browser.equalsIgnoreCase("Chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ ConfigReader.getValue("Chrome_Path"));
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("Firefox")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ ConfigReader.getValue("Firefox_Path"));
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("Edge")){

        }
        driver.manage().window().maximize();

    }

    public static void getURL(String url){

        if(url.equalsIgnoreCase("QA")){

            driver.get(ConfigReader.getValue("QAUrl"));
        }
        else if(url.equalsIgnoreCase("Dev")){

            driver.get(ConfigReader.getValue("DevUrl"));
        }
        else if(url.equalsIgnoreCase("Prod")){

            driver.get(ConfigReader.getValue("ProdUrl"));
        }
    }


    public String failed(String testname) throws IOException { // testname is test method name
        screen = new CaptureScreenshot();
        String destination = screen.getScreenShot(driver, testname); // testname will be scenario_name
        return destination;
    }

}
