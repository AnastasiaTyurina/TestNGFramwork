package com.syntax.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.syntax.pages.HomePage;
import com.syntax.pages.LoginPage;
import com.syntax.pages.LoginPageWithPageFactory;
import com.syntax.utils.BaseClass;
import com.syntax.utils.CommonMethods;
import com.syntax.utils.ConfigsReader;

public class LoginPageTest extends BaseClass {

	@Test(groups = "smoke")
	public void loginToOrangeHRM() {

		LoginPage login = new LoginPage();
		logger.info("Logging in with valid credentials");
		CommonMethods.sendText(login.username, "Admin");
		CommonMethods.sendText(login.password, "L0qKj@3ANi");
		CommonMethods.click(login.button);
		logger.info("Verifying dashboard text is displayed");
		HomePage home = new HomePage();
		boolean isDisplayed = home.dashboardText.isDisplayed();
		Assert.assertTrue(isDisplayed);
		logger.pass("Successfully logged in");
	}

	@Test(groups = "smoke")
	public void doLogin() {

		LoginPageWithPageFactory login = new LoginPageWithPageFactory();

		CommonMethods.sendText(login.userName, ConfigsReader.getProperty("username"));
		CommonMethods.sendText(login.password, ConfigsReader.getProperty("password"));
		CommonMethods.click(login.button);

		HomePage home = new HomePage();
		boolean isDisplayed = home.dashboardText.isDisplayed();
		// Assert.assertEquals(isDisplayed, true);
		Assert.assertTrue(isDisplayed);
		logger.pass("Login verification passed");
	}

	@Test(groups = "regression")
	public void loginFailedPassword() {
		LoginPageWithPageFactory login = new LoginPageWithPageFactory();
		CommonMethods.sendText(login.userName, ConfigsReader.getProperty("username"));
		CommonMethods.sendText(login.password, "1234567");
		CommonMethods.click(login.button);
		boolean isDisplayed = login.error.isDisplayed();
		Assert.assertTrue(isDisplayed);
	}

	@Test(groups = "regression")
	public void loginFailedUsername() {
		LoginPageWithPageFactory login = new LoginPageWithPageFactory();
		CommonMethods.sendText(login.userName, "Admin123");
		CommonMethods.sendText(login.password, ConfigsReader.getProperty("password"));
		CommonMethods.click(login.button);
		boolean isDisplayed = login.error.isDisplayed();
		Assert.assertTrue(isDisplayed);
	}

	@Test(groups = "smoke")
	public void loginFailedEmptyCredentials() {
		LoginPageWithPageFactory login = new LoginPageWithPageFactory();
		CommonMethods.sendText(login.userName, "");
		CommonMethods.sendText(login.password, "");
		CommonMethods.click(login.button);
		boolean isDisplayed = login.emptyCredentials.isDisplayed();
		Assert.assertTrue(isDisplayed);
	}
}
