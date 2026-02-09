package com.testautomationpractice.testcases;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class ReadingBarcodeUsingZxing {
	WebDriver driver =null;
	@Test
	public void readBarCode() throws IOException, NotFoundException {
		driver = new ChromeDriver();
		driver.get("https://barcode1.in/sample-barcode-images/");
		driver.manage().window().maximize();
		
		String imgeBarcode=driver.findElement(By.xpath("//img[@title='EAN-13 barcode image']")).getAttribute("src");
		
		System.out.println(imgeBarcode);
		URL url=new URL(imgeBarcode);
		
		BufferedImage buffimage=ImageIO.read(url);	
		
		LuminanceSource lumncace=new BufferedImageLuminanceSource(buffimage);
		BinaryBitmap bitmap=new BinaryBitmap(new HybridBinarizer(lumncace));
		
		Result result=new MultiFormatReader().decode(bitmap);
		
		System.out.println("Barcode value is: "+result.getText());
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
