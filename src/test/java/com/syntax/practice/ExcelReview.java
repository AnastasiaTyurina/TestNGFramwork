package com.syntax.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelReview  {

	@Test
	public void readExcel() throws IOException {
		String xlPath = "src/test/resources/testdata/OrangeHRMData.xlsx";
		FileInputStream fis = new FileInputStream(xlPath);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		String value=sheet.getRow(6).getCell(0).toString();
		System.out.println(value);
		int numRows=sheet.getPhysicalNumberOfRows();
		int numCols=sheet.getRow(0).getLastCellNum();
		System.out.println("The number of rows is "+numRows+" and the number of columns is "+numCols);
		for(int a=0; a<numRows; a++) {
			for(int b=0; b<numCols; b++) {
				String cellValue=sheet.getRow(a).getCell(b).toString();
				System.out.print(cellValue+" ");
			}
			System.out.println();
		}
		workbook.close();
		fis.close();
	}
}
