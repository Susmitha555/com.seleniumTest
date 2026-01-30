package com.testautomationpractice.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.testautomationpractice.base.TestBase;
import com.testautomationpractice.qa.pages.HomePage;
import com.testautomationpractice.qa.pages.loginPage;

public class loginPageTest extends TestBase {
	loginPage lp ;
	HomePage hm;
	
	public loginPageTest() {
		super();
	}
	
	
	@BeforeMethod
		public void setUp() {
		initialization();
		lp= new loginPage();
	}
	
	@Test(priority=0)
	public void loginPageTitleTest() {
		lp.validLoginPageTitle();
		assertSoft.assertEquals(lp.validLoginPageTitle(), "Swag Labs","Login Page title not matched");
	}
	
	
	@Test(priority=1)
	public void login() {
		hm=lp.loginIntoApp(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}