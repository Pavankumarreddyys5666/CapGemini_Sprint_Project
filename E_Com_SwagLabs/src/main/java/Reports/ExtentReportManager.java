package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extentReports;

    public static ExtentReports getExtentReports() {

        if (extentReports == null) {

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/SwagLabsReport.html");

            sparkReporter.config().setDocumentTitle("Swag Labs Automation Report");

            sparkReporter.config().setReportName("Swag Labs Test Execution");

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo("Application", "SauceDemo");

            extentReports.setSystemInfo("Framework", "Selenium + TestNG");

            extentReports.setSystemInfo("Language", "Java");

            extentReports.setSystemInfo("Browser", "Chrome");
        }

        return extentReports;
    }
}