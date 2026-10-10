package ObjectRepository;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage3 {

	private WebDriver driver;
	private WebDriverWait wait;

	// Locators
	private By completeHeader = By.cssSelector("[data-test='complete-header']");

	private By backHomeButton = By.id("back-to-products");

	// Constructor
	public CheckOutPage3(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Get order completion message
	public String getCompleteHeader() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
	}

	// Click Back Home button
	public void clickBackHome() {
		wait.until(ExpectedConditions.elementToBeClickable(backHomeButton)).click();
	}
}
