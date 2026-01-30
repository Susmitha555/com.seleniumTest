package com.testautomationpractice.qa.pages;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testautomationpractice.base.TestBase;

public class HomePageRemove extends TestBase {
	JavascriptExecutor js= ((JavascriptExecutor)driver);
	@FindBy(xpath = "//div[contains(text(),'Swag Labs')]")
	WebElement loginSucess;
	
	@FindBy(xpath="(//div[contains(@class,'inventory_item_name')])")
	List<WebElement> itemNames;
	
	
	public HomePageRemove() {
		PageFactory.initElements(driver, this);
	}

	public String verifyLoginSuccessText() {
		return loginSucess.getText();
	}
	
	public void clickRemoveCart(String itemName) throws InterruptedException {

		for (WebElement v : itemNames) {
			
			if (v.getText().equals(itemName)) {
				System.out.println("Found the item: " + itemName);
				scrollIntoView(driver
						.findElement(By.xpath("(//div[contains(text(),'" + itemName + "')]//following::button)[1]")));
				driver.findElement(By.xpath("(//div[contains(text(),'" + itemName + "')]//following::button)[1]"))
						.click();
				System.out.println("clicked on remove cart for item: " + itemName);
				Thread.sleep(7000);
				break;

			} 

		}
		

	}
	
	public void scrollBy() {
		js.executeScript("window.scrollBy(0,500)");
	}
	
	public void scrollTo() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollIntoView(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
}
