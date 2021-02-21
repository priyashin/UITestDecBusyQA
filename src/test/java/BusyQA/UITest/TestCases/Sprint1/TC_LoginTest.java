package BusyQA.UITest.TestCases.Sprint1;

import BusyQA.UITest.Screenshot.CaptureScreenshot;
import BusyQA.UITest.TestBase.BaseTest;
import BusyQA.UITest.ExcelReader.ExcelReaderTest;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pageClasses.LoginPage;
import pageClasses.LoginPage1;

import java.util.ArrayList;

@Listeners(BusyQA.UITest.ListenUtil.ListenerUtilTest.class)
public class TC_LoginTest extends BaseTest {

    public static final Logger log = Logger.getLogger(TC_LoginTest.class.getName());

    String filepath = System.getProperty("user.dir")+"\\Resources\\DataFiles\\";
    ExcelReaderTest excel;
    CaptureScreenshot screen;
    LoginPage login;
    LoginPage1 login1;
    ArrayList<String> loginVal = new ArrayList<>();

    @Parameters({"browser", "env"})
    @BeforeTest
    public void setup(String browsername, String env) {
        init(browsername, env);
        excel = new ExcelReaderTest();
        screen = new CaptureScreenshot();
        login = new LoginPage(driver);
        login1 = new LoginPage1(driver);
    }

    @Test
    public void TC01_FailedLoginTest(){

        loginVal = new ArrayList<>();
        log.info(loginVal);
        loginVal = excel.getData(filepath, "loginData.xlsx", "login", 1);
        log.info(loginVal);
        String username = loginVal.get(0);
        String password = loginVal.get(1);

        String actualmessage  = login.checkInvalidLogin(username, password);

        log.debug("Debug message"); // this should not be printed/included because of the log level = INFO
        log.info("Username: " + username);

        screen.getScreenShot(driver, "TC01_FailedLogin_Negative");

        Assert.assertEquals(actualmessage, "Invalid credentials", "Not matched");

        // excel.updateData(filepath, "loginData.xlsx", "login", 1, message);
        //  excel.newSheet(filepath, "loginData.xlsx", "newTestSheet", "DummyTest");
        //  excel.newExcel(filepath, "NewExcel.xlsx", "NewSheet");
    }

    @Test
    public void TC02_FailedLoginTest(){
        loginVal = new ArrayList<>(); // flushing of old values
        log.info(loginVal);
        loginVal = excel.getData(filepath, "loginData.xlsx", "login", 2);
        log.info(loginVal);
        String username = loginVal.get(0);
        String password = loginVal.get(1);

        String message = login1.checkInvalidLogin(username, password);

        Assert.assertEquals(message, "Invalid", "Not matched");
    }

    @Test
    public void TC03_SuccessLoginTest(){

        loginVal = new ArrayList<>(); // flushing of old values
        log.info(loginVal);
        loginVal = excel.getData(filepath, "loginData.xlsx", "login", 4);
        log.info(loginVal);
        String username = loginVal.get(0);
        String password = loginVal.get(1);

        String message = login1.checkValidLogin(username, password);
        screen.getScreenShot(driver, "TC03_SuccessLogin_Positive");
        Assert.assertEquals(message, "Success", "Not matched");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }
}
