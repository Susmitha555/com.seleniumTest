package com.testautomationpractice.qa.pages;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testautomationpractice.base.TestBase;

public class AutomationPracticeData extends TestBase {
	
	@FindBy(id="name")
	WebElement name;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="phone")
	WebElement phone;
	
	
	@FindBy(id="textarea")
	WebElement address;
	
	
	public AutomationPracticeData() {
		PageFactory.initElements(driver, this);
	}
	
	public void enterData(String namee, String emaill, String phonee, String addresss) {
		name.sendKeys(namee);
		email.sendKeys(emaill);
		phone.sendKeys(phonee);
		address.sendKeys(addresss);
		
	}
}
