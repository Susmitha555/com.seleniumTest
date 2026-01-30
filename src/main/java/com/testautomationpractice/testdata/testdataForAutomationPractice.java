package com.testautomationpractice.testdata;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class testdataForAutomationPractice {
	static String excelFilePath = "C:\\Users\\HP\\eclipse-workspace\\SeleniumTest\\src\\main\\java\\com\\testautomationpractice\\testdata\\TestDataExcel.xlsx";
	

	@DataProvider
	public Object[][] getTestData() throws IOException {
		System.out.println("This is a sample dataprovider test class");

		FileInputStream fis = new FileInputStream(excelFilePath);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Object[][] getTestData = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();

		DataFormatter format = new DataFormatter();

		for (int i =0; i <rowCount; i++) {
			for (int k = 0; k < columnCount; k++) {
				getTestData[i][k] = format.formatCellValue(sheet.getRow(i+1).getCell(k));
				 
			}
			

		}
		workbook.close();

		return getTestData;

	}

}
