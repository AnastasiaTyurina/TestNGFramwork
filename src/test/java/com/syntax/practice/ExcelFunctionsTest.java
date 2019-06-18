package com.syntax.practice;

import org.testng.annotations.Test;

import com.syntax.utils.Constants;
import com.syntax.utils.ExcelUtility;

public class ExcelFunctionsTest {

	@Test
	public void excelTest() {
		ExcelUtility obj=new ExcelUtility();
		obj.openExcel(Constants.XL_FILEPATH, "Sheet1");
		//String value=obj.getCellData(2, 3);
		//System.out.println(value);
		
		//retrieve all values from Excel and store them into Data Provider (2D object array)
		int row=obj.getRowNum();
		int cell=obj.getColNum(0);
		Object[] [] data=new Object[row] [cell];
		System.out.println(row);
		System.out.println(cell);
		
		for(int i=1; i<row; i++) {
			for(int j=0; j<cell; j++) {
				String value=obj.getCellData(i, j);
				data[i][j]=value;
			}	
		}
		System.out.println(data.length);
	}
	
}
