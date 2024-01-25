package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
    private ExtentReports extent;
    private ThreadLocal < ExtentTest > test = new ThreadLocal < > ();

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentManager.createInstance(context);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
        // Add more details as needed
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
        test.get().getStatus();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}