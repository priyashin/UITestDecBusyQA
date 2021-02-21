package BusyQA.UITest.Screenshot;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureScreenshot {

    String dest = "";

    public String getScreenShot(WebDriver driver, String scenario_name){

        try {

            TakesScreenshot ts=(TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE); // screenshot is taken as a FILE output

            SimpleDateFormat s=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");

            String d = s.format(new Date());

            dest = System.getProperty("user.dir")+"\\ScreenShots\\/"+d+"-"+scenario_name+".png"; // save at  this place with the name mentioned

            File destination = new File(dest);

            FileHandler.copy(source, destination);

            System.out.println("Screenshot taken");
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dest;
    }
}
