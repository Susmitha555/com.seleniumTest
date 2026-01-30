package com.testautomationpractice.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.testautomationpractice.base.TestBase;
import com.testautomationpractice.qa.pages.HomePage;
import com.testautomationpractice.qa.pages.loginPage;

@Listeners(com.testautomationpractice.Util.ExtentReportListener.class)
public class HomepageTest extends TestBase {
	loginPage lp;
	HomePage hp;
	public HomepageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		lp=new loginPage();
		hp=lp.loginIntoApp(prop.getProperty("username"), prop.getProperty("password"));
		// Add any necessary setup steps here
	}
	@Test
	public void verifyLoggedInTest() {
		hp.verifyLoginSuccessText();
		System.out.println(hp.verifyLoginSuccessText());
		assertSoft.assertEquals(hp.verifyLoginSuccessText(), "Swag Labs","Login not successful");
	}

	@Test
	public void clickAddtoCartTest() throws InterruptedException {
		hp.clickAddtoCart("Sauce Labs Backpack");
		hp.clickAddtoCart("Sauce Labs Bike Light");
		hp.clickAddtoCart("Sauce Labs Bolt T-Shirt");
		hp.clickAddtoCart("Sauce Labs Fleece Jacket");
		hp.clickAddtoCart("Sauce Labs Onesie");
		hp.clickAddtoCart("Test.allTheThings() T-Shirt (Red)");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
