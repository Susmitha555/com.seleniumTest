package com.testautomationpractice.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class retryFailedTest {
	
	@Test()
	public void sampleTest() {
		System.out.println("This is a sample test for retrying failed tests.");
		Assert.assertEquals(1, 2); // This will fail
	}

}
