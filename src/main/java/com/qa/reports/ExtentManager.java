package com.qa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager  {
	
	static ExtentReports extent;
	
	public static ExtentReports getExtent() {
		
		if(extent==null) {
			ExtentSparkReporter spark=new ExtentSparkReporter(System.getProperty("user.dir")+"//test-output//Myreport.html");
			extent=new ExtentReports();
			extent.attachReporter(spark);
				
		}
		return extent;
	}
	

}
