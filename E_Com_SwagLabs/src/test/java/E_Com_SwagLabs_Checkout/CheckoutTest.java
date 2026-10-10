package E_Com_SwagLabs_Checkout;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import ObjectRepository.ProductPage;
import ObjectRepository.CartPage;
import ObjectRepository.CheckOutPage1;
import ObjectRepository.CheckOutPage2;
import ObjectRepository.CheckOutPage3;

public class CheckoutTest extends BaseClass {

	// Navigate to Checkout Information page
	private CheckOutPage1 openCheckoutPage() throws Exception {

		// Login
		login();

		// Add product
		ProductPage productPage = new ProductPage(driver);
		productPage.addBackpack();

		// Open cart
		productPage.openCart();

		// Create CartPage object
		CartPage cartPage = new CartPage(driver);

		// Verify cart page
		Assert.assertEquals(cartPage.getTitle(), "Your Cart", "Cart page was not displayed");

		Assert.assertEquals(cartPage.getItemCount(), 1, "Product was not added to cart");

		// Click checkout
		cartPage.clickCheckout();

		// Return checkout information page
		return new CheckOutPage1(driver);
	}

	// Test 1: Verify Checkout Information page
	@Test(groups = { "smoke", "system" })
	public void verifyCheckoutInformationPage() throws Exception {

		CheckOutPage1 checkoutPage = openCheckoutPage();

		Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information",
				"Checkout Information page was not displayed");

		Reporter.log("Checkout Information page displayed successfully", true);
	}

	// Test 2: Verify checkout with valid information
	@Test(groups = { "functional", "positive" })
	public void verifyCheckoutWithValidData() throws Exception {

		CheckOutPage1 checkoutPage = openCheckoutPage();

		checkoutPage.enterFirstName(pu.readDataFromPropertyFile("firstName"))
				.enterLastName(pu.readDataFromPropertyFile("lastName"))
				.enterPostalCode(pu.readDataFromPropertyFile("postalCode")).clickContinue();

		CheckOutPage2 overviewPage = new CheckOutPage2(driver);

		Assert.assertEquals(overviewPage.getTitle(), "Checkout: Overview", "Checkout Overview page was not displayed");

		Reporter.log("Valid checkout information accepted successfully", true);
	}

	// Test 3: Verify checkout without first name
	@Test(groups = { "negative" })
	public void verifyCheckoutWithoutFirstName() throws Exception {

		CheckOutPage1 checkoutPage = openCheckoutPage();

		checkoutPage.enterLastName(pu.readDataFromPropertyFile("lastName"))
				.enterPostalCode(pu.readDataFromPropertyFile("postalCode")).clickContinue();

		Assert.assertTrue(checkoutPage.getError().contains("First Name is required"),
				"First Name validation error was not displayed");

		Reporter.log("Missing first name correctly rejected", true);
	}

	// Test 4: Verify checkout without last name
	@Test(groups = { "negative" })
	public void verifyCheckoutWithoutLastName() throws Exception {

		CheckOutPage1 checkoutPage = openCheckoutPage();

		checkoutPage.enterFirstName(pu.readDataFromPropertyFile("firstName"))
				.enterPostalCode(pu.readDataFromPropertyFile("postalCode")).clickContinue();

		Assert.assertTrue(checkoutPage.getError().contains("Last Name is required"),
				"Last Name validation error was not displayed");

		Reporter.log("Missing last name correctly rejected", true);
	}

	// Test 5: Verify checkout without postal code
	@Test(groups = { "negative" })
	public void verifyCheckoutWithoutPostalCode() throws Exception {

		CheckOutPage1 checkoutPage = openCheckoutPage();

		checkoutPage.enterFirstName(pu.readDataFromPropertyFile("firstName"))
				.enterLastName(pu.readDataFromPropertyFile("lastName")).clickContinue();

		Assert.assertTrue(checkoutPage.getError().contains("Postal Code is required"),
				"Postal Code validation error was not displayed");

		Reporter.log("Missing postal code correctly rejected", true);
	}

	// Test 6: Verify complete order
	@Test(groups = { "functional", "integration" })
	public void verifyOrderCompletion() throws Exception {

		CheckOutPage1 checkoutPage = openCheckoutPage();

		checkoutPage.enterFirstName(pu.readDataFromPropertyFile("firstName"))
				.enterLastName(pu.readDataFromPropertyFile("lastName"))
				.enterPostalCode(pu.readDataFromPropertyFile("postalCode")).clickContinue();

		CheckOutPage2 overviewPage = new CheckOutPage2(driver);

		Assert.assertEquals(overviewPage.getTitle(), "Checkout: Overview", "Checkout Overview page was not displayed");

		overviewPage.clickFinish();

		CheckOutPage3 completePage = new CheckOutPage3(driver);

		Assert.assertEquals(completePage.getCompleteHeader(), "Thank you for your order!",
				"Order completion message was not displayed");

		Reporter.log("Order completed successfully", true);
	}
}
