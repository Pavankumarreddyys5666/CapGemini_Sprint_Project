
package E_Com_SwagLabs_Login;

import java.io.IOException;

import org.testng.Assert;

import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.BaseClass;

import GenericUtilities.ScreenshotUtility;
import ObjectRepository.LoginPage;

public class LoginTest extends BaseClass {

	@Test(groups = { "functional", "negative" })
	public void verifyLoginWithInvalidCredentials() throws IOException {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.enterUsername("invalid_user");
		loginPage.enterPassword("invalid_password");
		loginPage.clickLogin();

		Assert.assertTrue(loginPage.getError().contains("Username and password do not match"),
				"Expected invalid-login error was not displayed");

		ScreenshotUtility.captureScreenshot(driver, "InvalidCredentials");

		Reporter.log("Invalid login correctly rejected", true);
	}

	@Test(groups = { "functional", "negative" })
	public void verifyLoginWithEmptyCredentials() throws IOException {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.enterUsername("");
		loginPage.enterPassword("");
		loginPage.clickLogin();

		Assert.assertTrue(loginPage.getError().contains("Username is required"),
				"Expected empty-username error was not displayed");

		ScreenshotUtility.captureScreenshot(driver, "EmptyCredentials");

		Reporter.log("Empty credentials correctly rejected", true);
	}
}
