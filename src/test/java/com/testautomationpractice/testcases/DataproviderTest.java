package com.testautomationpractice.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import com.testautomationpractice.base.TestBase;
import com.testautomationpractice.qa.pages.AutomationPracticeData;

public class DataproviderTest extends TestBase {
	AutomationPracticeData apd;

	public DataproviderTest() {
		super();
	}

	@BeforeMethod
	public void setUp()  {
		initialization();
		apd = new AutomationPracticeData();

	}

	@Test(dataProvider = "getTestData",dataProviderClass=com.testautomationpractice.testdata.testdataForAutomationPractice.class)
	public void dataproviderTest(String Name, String Emalil, String Phone, String Address) {
		apd.enterData(Name, Emalil, Phone, Address);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
