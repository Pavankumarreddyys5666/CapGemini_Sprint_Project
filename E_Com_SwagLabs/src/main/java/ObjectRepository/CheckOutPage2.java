package ObjectRepository;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage2 {

	private WebDriver driver;
	private WebDriverWait wait;

	// Locators
	private By pageTitle = By.cssSelector("[data-test='title']");

	private By finishButton = By.id("finish");

	// Constructor
	public CheckOutPage2(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Get checkout overview page title
	public String getTitle() {
		return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(pageTitle))
				.getText();
	}

	// Click Finish button
	public void clickFinish() {
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(finishButton)).click();
	}
}
