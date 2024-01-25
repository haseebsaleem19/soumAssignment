package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance(ITestContext context) {
        if (extent == null) {
            // Retrieve the test name from the TestNG context
            String testName = context.getCurrentXmlTest().getName();

            // Use the test name and current date/time to generate a unique file name
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportFileName = "test-output/" + testName + "_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFileName);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}