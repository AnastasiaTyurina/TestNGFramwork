package com.syntax.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	/**
	 * Method to open specified Excel file and sheet
	 * @param xlFilePath
	 * @param sheetName
	 */
	
	public void openExcel(String xlFilePath, String sheetName) {
		try {
			fis=new FileInputStream(xlFilePath);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(sheetName);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
		
	}
	
	/**
	 * Method will return String value of a specified cell
	 * @param rowIndex
	 * @param cellIndex
	 * @return String
	 */
	public String getCellData(int rowIndex, int cellIndex) {
		return sheet.getRow(rowIndex).getCell(cellIndex).toString();
	}
	
	/**
	 * Method to return number of rows used
	 * @return int
	 */
	public int getRowNum() {
	
		return sheet.getPhysicalNumberOfRows();
	}
	
	/**
	 * Method to return number of columns used
	 * @param rowIndex
	 * @return int
	 */
	public int getColNum(int rowIndex) {
		return sheet.getRow(rowIndex).getLastCellNum();
	}
}
