package com.testautomationpractice.testcases;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ibm.icu.text.SimpleDateFormat;

public class ExtentReportWithScreenShotTest extends com.testautomationpractice.base.TestBase {

	static WebDriver driver;
	static ExtentReports extent;
	static ExtentTest test;

	@BeforeTest
	public void setUpExtent() {
		extent = new ExtentReports();
		extent.setSystemInfo("Host Name", "LocalHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "TestAutomationPractice");
		ExtentSparkReporter spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "//reports//ExtentReport.html");
		extent.attachReporter(spark);

	}

	@Test
	public void GoogleTest() throws IOException {
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		String title = driver.getTitle();

		Assert.assertEquals(title, "Google123");

	}

	public String takeScreenshot(WebDriver driver) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File screnehsotFolder = new File(System.getProperty("user.dir") + "/reports/screenshots/");
		if (!screnehsotFolder.exists()) {
			screnehsotFolder.mkdir();
		}
		
		String dateName=new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
		String filename="ScreenshotTest_" + dateName + ".png";

		FileUtils.copyFile(src, new File(screnehsotFolder+"/"+filename));
		return "screenshots/"+filename;
	}

	public String takeScreenshotWithBase64(WebDriver driver) throws IOException {

		String srcImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

		return srcImage;
	}

	@AfterTest
	public void tearDown() {
		extent.flush();

	}

	@AfterMethod
	public void testResults(ITestResult result) throws IOException {
		test=extent.createTest(result.getName()).log(Status.INFO, "Navigated to google.com");
		
		
		if (result.getStatus() == ITestResult.FAILURE) {
			
			test.log(Status.FAIL, " Test Case Failed" +result.getThrowable());
			test.log(Status.FAIL, "" + result.getName() + " Test Case Failed",
					MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotWithBase64(driver)).build());
			
			test.log(Status.FAIL, "" + result.getName() + " Test Case Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(driver)).build());
			
			
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "" + result.getName() + " Test Case Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(driver)).build());
			test.log(Status.PASS, "" + result.getName() + " Test Case Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(driver)).build());
			
			test.log(Status.PASS,result.getName()+test.addVideoFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64), "Test Video"));
		}

		else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "" + result.getName() + " Test Case Skipped");
		}

	}
	
	@AfterTest
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
	

}
