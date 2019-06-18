package com.syntax.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelWriteDemo {

	@Test
	public void writeExcel() throws Exception {
		String xlPath = "src/test/resources/testdata/OrangeHRMData.xlsx";
		FileInputStream fis = new FileInputStream(xlPath);
		XSSFWorkbook wbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=wbook.getSheet("Sheet1");
		sheet.getRow(0).createCell(4).setCellValue("Result");
		sheet.getRow(1).createCell(4).setCellValue("Pass");
		sheet.getRow(1).getCell(4).setCellValue("Fail");
		sheet.createRow(11).createCell(0).setCellValue("Mehmet");
		FileOutputStream fos=new FileOutputStream(xlPath);
		wbook.write(fos);
		
		fos.close();
		wbook.close();
		fis.close();
	}
	
}
