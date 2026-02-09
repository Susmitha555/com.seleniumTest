package com.testautomationpractice.testcases;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testautomationpractice.base.TestBase;
import com.testautomationpractice.qa.pages.HomePage;
import com.testautomationpractice.qa.pages.loginPage;

public class WritewebtableintoExcel extends TestBase {
	loginPage lp;
	HomePage hm;
	 static Workbook wb;
	 Sheet sheet;
	public WritewebtableintoExcel() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		
	}
	
	@Test
	public void writeWebTableIntoExcel() throws IOException {
		sheet=createExcel("C:\\Users\\HP\\eclipse-workspace\\SeleniumTest\\WebTableData.xlsx", "WebTableData");
	
		List<WebElement> rows=driver.findElements(By.xpath("//table[@id='customers']//tr"));
		List<WebElement> headers=driver.findElements(By.xpath("//table[@id='customers']//tr[1]//th"));
		Row headerRow=sheet.createRow(0);
		
		for(int i=0;i<headers.size();i++) {
			headerRow.createCell(i).setCellValue(driver.findElement(By.xpath("//table[@id='customers']//tr[1]//th["+(i+1)+"]")).getText());
			
		}
		
		
		  for(int j=2;j<=rows.size();j++) { 
			  Row row=sheet.createRow(j-1);
		  List<WebElement> totalColumns = driver
		  .findElements(By.xpath("//table[@id='customers']//tr[" + j + "]//td"));
		  
		  for (int k = 0; k < totalColumns.size(); k++) { 
			  String cellvalue=totalColumns.get(k).getText() ;
			  row.createCell(k).setCellValue(cellvalue);
		  
		  }
		  
		  
		  
		  }
		 
		FileOutputStream fos=new FileOutputStream("C:\\Users\\HP\\eclipse-workspace\\SeleniumTest\\WebTableData.xlsx");
		wb.write(fos);
		wb.close();
		fos.close();
		
		System.out.println("Web table data has been written into Excel successfully.");
	
	}
	
	public static Sheet createExcel(String pathOfTheFile,String SheetName) throws IOException {
		 
		FileOutputStream fos=new FileOutputStream(pathOfTheFile);
		wb=new XSSFWorkbook();
		
		 Sheet sheet=wb.createSheet(SheetName);
		 return sheet;
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
		
	
}
