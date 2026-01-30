package com.testautomationpractice.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.asserts.SoftAssert;

import com.testautomationpractice.Util.ExtentReportListener;
import com.testautomationpractice.Util.TestUtil;
import com.testautomationpractice.Util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	FileInputStream fis;
	public SoftAssert assertSoft = new SoftAssert();

	public TestBase() {

		try {
			fis = new FileInputStream(
					"C:\\Users\\HP\\eclipse-workspace\\SeleniumTest\\src\\main\\java\\com\\testautomationpractice\\configuration\\config.properties");

			prop = new Properties();
			prop.load(fis);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void initialization() {
		String browserName = prop.getProperty("browserName");

		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<String, Object>();

		// Disable password leak detection + safe browsing
		prefs.put("safebrowsing.enabled", true);
		prefs.put("profile.password_manager_leak_detection", false);

		options.setExperimentalOption("prefs", prefs);


		if (browserName.equals("chrome")) {
			driver = new ChromeDriver(options);
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new InternetExplorerDriver();

		}   
		
		 WebEventListener interfaceEvents=new WebEventListener();
		 EventFiringDecorator driverEvent=new EventFiringDecorator(interfaceEvents);
		 driver=driverEvent.decorate(driver);
		 try {
			ExtentReportListener.driver=driver;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		driver.get(prop.getProperty("Url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies(); 
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

	}

}
