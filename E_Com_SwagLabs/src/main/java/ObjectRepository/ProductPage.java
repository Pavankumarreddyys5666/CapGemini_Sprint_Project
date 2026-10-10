package ObjectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

	private WebDriver driver;

	// Constructor
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(xpath = "//span[@class='title']")
	private WebElement productsTitle;

	@FindBy(xpath = "//select[@class='product_sort_container']")
	private WebElement sort;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement addBackpackButton;

	@FindBy(id = "remove-sauce-labs-backpack")
	private WebElement removeBackpackButton;

	@FindBy(className = "shopping_cart_link")
	private WebElement cartLink;

	@FindBy(className = "shopping_cart_badge")
	private WebElement cartBadge;

	@FindBy(className = "inventory_item_price")
	private List<WebElement> productPrices;

	// Getters
	public WebElement getProductsTitle() {
		return productsTitle;
	}

	public WebElement getSort() {
		return sort;
	}

	public WebElement getAddBackpackButton() {
		return addBackpackButton;
	}

	public WebElement getRemoveBackpackButton() {
		return removeBackpackButton;
	}

	public WebElement getCartLink() {
		return cartLink;
	}

	// Get products page title
	public String getTitle() {
		return productsTitle.getText();
	}

	// Select sorting option
	public void selectingSorting(String sortVText) {
		Select select = new Select(sort);
		select.selectByVisibleText(sortVText);
	}

	// Sort products: low to high
	public void sortLowToHigh() {
		selectingSorting("Price (low to high)");
	}

	// Sort products: high to low
	public void sortHighToLow() {
		selectingSorting("Price (high to low)");
	}

	// Add backpack to cart
	public void addBackpack() {
		addBackpackButton.click();
	}

	// Remove backpack from cart
	public void removeBackpack() {
		removeBackpackButton.click();
	}

	// Open cart page
	public void openCart() {
		cartLink.click();
	}

	// Get cart item count
	public int getCartCount() {
		if (driver.findElements(org.openqa.selenium.By.className("shopping_cart_badge")).isEmpty()) {
			return 0;
		}

		return Integer.parseInt(driver.findElement(org.openqa.selenium.By.className("shopping_cart_badge")).getText());
	}

	// Get product prices
	public List<Double> getPrices() {
		return productPrices.stream().map(element -> Double.parseDouble(element.getText().replace("$", "").trim()))
				.toList();
	}
}
