package BusyQA.UITest.ListenUtil;

import BusyQA.UITest.ExtentReporting.ExtentTestManager;
import BusyQA.UITest.TestBase.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ListenerUtilTest extends BaseTest implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTestManager.startTest(result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable()); // print the error message in the report
        try {
            String destination = failed(result.getMethod().getMethodName());
            ExtentTestManager.getTest().addScreenCaptureFromPath(destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

        ExtentTestManager.endTest();



    }
}
