package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

	private WebDriver driver;

	private By cartTitle = By.cssSelector("[data-test='title']");

	private By checkoutButton = By.id("checkout");

	private By cartItems = By.className("cart_item");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitle() {
		return driver.findElement(cartTitle).getText();
	}

	public int getItemCount() {
		return driver.findElements(cartItems).size();
	}

	public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}