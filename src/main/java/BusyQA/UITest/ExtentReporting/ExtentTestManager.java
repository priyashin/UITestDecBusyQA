package BusyQA.UITest.ExtentReporting;;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;

public class ExtentTestManager {

    static HashMap<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.getInstance();
    static ExtentTest test;

    public static synchronized void startTest(String testName){ // each method

        test = extent.createTest(testName);
        extentTestMap.put((int) Thread.currentThread().getId(), test);

    }


    public static synchronized ExtentTest getTest(){ // each method

        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void endTest(){ // each method

        extent.flush();

    }

}
