package BaseClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import GenericUtilities.PropertyUtility;
import GenericUtilities.ScreenshotUtility;
import ObjectRepository.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public PropertyUtility pu;

	// Launch browser before each test
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void launchBrowser(@Optional("chrome") String browser) throws Exception {

		pu = new PropertyUtility();

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get(pu.readDataFromPropertyFile("url"));
	}

	// Login using valid credentials
	protected void login() throws Exception {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.enterUsername(pu.readDataFromPropertyFile("standardUsername"));

		loginPage.enterPassword(pu.readDataFromPropertyFile("standardPassword"));

		loginPage.clickLogin();
	}

	// Capture screenshot when a test fails
	@AfterMethod(alwaysRun = true)
    public void closeBrowser(ITestResult result) {

        try {
            if (driver != null
                    && result.getStatus() == ITestResult.FAILURE) {

                String packageName = result.getTestClass()
                        .getRealClass().getPackageName();

                String moduleName;

                if (packageName.contains("Login")) {
                    moduleName = "Login";
                } else if (packageName.contains("Product")) {
                    moduleName = "Product";
                } else if (packageName.contains("Checkout")) {
                    moduleName = "Checkout";
                } else {
                    moduleName = "Other";
                }

                String screenshotName = moduleName + "_"
                        + result.getMethod().getMethodName();

                ScreenshotUtility.captureScreenshot(
                        driver, screenshotName);
            }

        } catch (Exception e) {
            System.out.println(
                    "Unable to capture screenshot: "
                    + e.getMessage());

        } finally {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    }
}