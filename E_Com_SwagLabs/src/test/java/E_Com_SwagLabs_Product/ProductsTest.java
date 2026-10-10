
package E_Com_SwagLabs_Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import ObjectRepository.ProductPage;

public class ProductsTest extends BaseClass {

	// Test 1: Verify Products page
	@Test(groups = { "smoke", "system" })
	public void verifyProductsPage() throws Exception {

		login();

		ProductPage productPage = new ProductPage(driver);

		Assert.assertEquals(productPage.getTitle(), "Products", "Products page was not displayed");

		Reporter.log("Products page displayed successfully", true);
	}

	// Test 2: Verify product can be added to cart
	@Test(groups = { "functional", "integration" })
	public void verifyProductCanBeAddedToCart() throws Exception {

		login();

		ProductPage productPage = new ProductPage(driver);

		productPage.addBackpack();

		Assert.assertEquals(productPage.getCartCount(), 1, "Product was not added to the cart");

		Reporter.log("Product added to cart successfully", true);
	}

	// Test 3: Verify product can be removed from cart
	@Test(groups = { "functional" })
	public void verifyProductCanBeRemovedFromCart() throws Exception {

		login();

		ProductPage productPage = new ProductPage(driver);

		productPage.addBackpack();
		productPage.removeBackpack();

		Assert.assertEquals(productPage.getCartCount(), 0, "Product was not removed from the cart");

		Reporter.log("Product removed from cart successfully", true);
	}

	// Test 4: Sort products by price (low to high)
	@Test(groups = { "functional" })
	public void verifyProductsSortByPriceLowToHigh() throws Exception {

		login();

		ProductPage productPage = new ProductPage(driver);

		productPage.sortLowToHigh();

		List<Double> actualPrices = productPage.getPrices();

		List<Double> expectedPrices = new ArrayList<>(actualPrices);

		Collections.sort(expectedPrices);

		Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted by price low to high");

		Reporter.log("Price low-to-high sorting verified", true);
	}

	// Test 5: Sort products by price (high to low)
	@Test(groups = { "functional" })
	public void verifyProductsSortByPriceHighToLow() throws Exception {

		login();

		ProductPage productPage = new ProductPage(driver);

		productPage.sortHighToLow();

		List<Double> actualPrices = productPage.getPrices();

		List<Double> expectedPrices = new ArrayList<>(actualPrices);

		expectedPrices.sort(Collections.reverseOrder());

		Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted by price high to low");

		Reporter.log("Price high-to-low sorting verified", true);
	}
}
