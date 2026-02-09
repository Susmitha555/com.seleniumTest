package com.testautomationpractice.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testautomationpractice.base.TestBase;

public class loginPage extends TestBase {
	// Page Factory-OR Object Repository

	@FindBy(name = "user-name")
	WebElement userName;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;

	@FindBy(xpath = "//div[@id='login_credentials']")
	WebElement validUserNames;

	@FindBy(xpath = "//div[@data-test='login-password']")
	WebElement validPassword;

	
	// Initializing the Page Objects
	public loginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String validLoginPageTitle() {
		return driver.getTitle();

	}

	public HomePage loginIntoApp(String userNamee, String passworrd) {
		userName.sendKeys(userNamee);
		password.sendKeys(passworrd);
		loginBtn.click();

		validPassword.sendKeys(Keys.CONTROL + "a");
		return new HomePage();
		

	}

}
