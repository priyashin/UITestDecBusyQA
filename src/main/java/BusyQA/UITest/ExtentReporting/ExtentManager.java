package BusyQA.UITest.ExtentReporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;
    private static SimpleDateFormat s=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
    private static String date = s.format(new Date());
    private static String reportFilepath = System.getProperty("user.dir") + "\\TestReport";
    private static String reportFileLocation =  reportFilepath +"\\Test-Automaton-Report_"+ date + ".html";


    public static ExtentReports getInstance() {
        if (extent == null) // to check if first test case is triggered. You haven't created the object if it is null
            createInstance();
        return extent; // returning existing extent object
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        String fileName = reportFileLocation;

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

        // Setup report template definition
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Test");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("ReportName");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter); // integrating the ExtentReports with ExtentHTMLReporter
        //Set environment details
        extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("AUT", "UAT");
        extent.setSystemInfo("QA Name", "Simran Raina");

        return extent;
    }
}
