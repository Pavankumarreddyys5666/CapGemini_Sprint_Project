
package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;

	// WebElement declarations
	private WebElement usernameTextField;
	private WebElement passwordTextField;
	private WebElement loginButton;

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;

		usernameTextField = driver.findElement(By.id("user-name"));
		passwordTextField = driver.findElement(By.id("password"));
		loginButton = driver.findElement(By.id("login-button"));
	}

	// Enter username
	public LoginPage enterUsername(String username) {
		usernameTextField.clear();
		usernameTextField.sendKeys(username);
		return this;
	}

	// Enter password
	public LoginPage enterPassword(String password) {
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
		return this;
	}

	// Click login button
	public void clickLogin() {
		loginButton.click();
	}

	// Get login error message
	public String getError() {
		return driver.findElement(By.cssSelector("[data-test='error']")).getText();
	}

	// Check whether login button is displayed
	public boolean isLoginButtonDisplayed() {
		return driver.findElement(By.id("login-button")).isDisplayed();
	}
}
