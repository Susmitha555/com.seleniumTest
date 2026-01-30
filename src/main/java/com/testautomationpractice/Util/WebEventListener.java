package com.testautomationpractice.Util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import com.testautomationpractice.base.TestBase;


public class WebEventListener extends TestBase implements WebDriverListener  {

	@Override
	public void beforeGetText(org.openqa.selenium.WebElement element) {
		System.out.println("Getting text from element: " + element);
	}
	
	@Override
	public void afterGetText(org.openqa.selenium.WebElement element, String result) {
		System.out.println("Got text: " + result + " from element: " + element);
	}
	
	@Override
	public void beforeClick(org.openqa.selenium.WebElement element) {
		System.out.println("Attempting to click on element: " + element);
	}
	
	@Override
	public void afterClick(org.openqa.selenium.WebElement element) {
		System.out.println("Clicked on element: " + element);
	}
	
	@Override
	public void beforeSendKeys(org.openqa.selenium.WebElement element, CharSequence... keysToSend) {
		System.out.println("Sending keys: " + String.join("", keysToSend) + " to element: " + element);
	}
	
	@Override
	public void afterSendKeys(org.openqa.selenium.WebElement element, CharSequence... keysToSend) {
		System.out.println("Sent keys: " + String.join("", keysToSend) + " to element: " + element);
	}
	
	@Override
	public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
	    if (target instanceof WebDriver) {
	        WebDriver driver = (WebDriver) target;
	        takeScreenshot(driver);
	    }
	}

	private void takeScreenshot(WebDriver driver) {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir=System.getProperty("user.dir");
		
		File ff=new File(currentDir + "/screenshots/");
		if(ff.exists()) {
			System.out.println("Screenshot directory exists.");
		} else {
			ff.mkdir();
			System.out.println("Screenshot directory created.");
		}
		try {
			FileUtils.copyFile(src, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}	

