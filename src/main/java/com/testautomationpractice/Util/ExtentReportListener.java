package com.testautomationpractice.Util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.reports.ExtentManager;

public class ExtentReportListener implements ITestListener {

	public static WebDriver driver;

	ExtentReports extent = ExtentManager.getExtent();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {

		ITestListener.super.onTestStart(result);
		System.out.println("Test Started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ITestListener.super.onTestSuccess(result);
		System.out.println("Test Started: " + result.getName());
		test.pass("Test Successed: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		try {
			ITestListener.super.onTestFailure(result);
			System.out.println("Test Failed: " + result.getName());
			
			String path = takeScreenshot(result.getName());
			System.out.println("Screenshot taken at: " + path);
			test.addScreenCaptureFromPath(path, "Failed Screenshot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.fail("Test Failed: " + result.getName());
		test.fail(result.getThrowable());

	}

	private String takeScreenshot(String name) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./Screenshots/" + name + "_" + System.currentTimeMillis() + ".png"));
		return "./Screenshots/" + name + "_" + System.currentTimeMillis() + ".png";

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ITestListener.super.onTestSkipped(result);
		System.out.println("Test Skipped: " + result.getName());
		test.skip("Test Skipped: " + result.getName());
	}

	public void onFinish(ITestContext result) {
		ITestListener.super.onFinish(result);
		extent.flush();
	}

	public void onConfigurationFailure(ITestResult result) {
		System.out.println("Config Failed: " + result.getName());

		try {
			takeScreenshot("CONFIG_FAIL_" + result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
