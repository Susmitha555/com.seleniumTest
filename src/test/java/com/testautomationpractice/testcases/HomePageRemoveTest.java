package com.testautomationpractice.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testautomationpractice.base.TestBase;
import com.testautomationpractice.qa.pages.HomePage;
import com.testautomationpractice.qa.pages.HomePageRemove;
import com.testautomationpractice.qa.pages.loginPage;

public class HomePageRemoveTest extends TestBase {
   loginPage lp;
   HomePage hp;
   HomePageRemove hpRemove;
	 
	public HomePageRemoveTest() {
		super();

	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		
		initialization();
		lp=new loginPage();
		hp=lp.loginIntoApp(prop.getProperty("username"), prop.getProperty("password"));
		hpRemove=hp.clickAddtoCart("Sauce Labs Backpack");
	}
	
	@Test
	public void clickAddtoCartTest() throws InterruptedException {
		hpRemove.clickRemoveCart("Sauce Labs Backpack");
		

	}

	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
