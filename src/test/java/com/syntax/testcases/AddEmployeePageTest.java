package com.syntax.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.syntax.pages.AddEmployeePage;
import com.syntax.pages.HomePage;
import com.syntax.pages.LoginPageWithPageFactory;
import com.syntax.utils.BaseClass;
import com.syntax.utils.CommonMethods;
import com.syntax.utils.ConfigsReader;
import com.syntax.utils.Constants;
import com.syntax.utils.ExcelUtility;

public class AddEmployeePageTest extends BaseClass {
	
	@Test(dataProvider="Sheet1", groups="regression")
	public void addEmployee(String fName, String mName, String lName, String location) throws InterruptedException {

	LoginPageWithPageFactory login=new LoginPageWithPageFactory();
	HomePage home=new HomePage();
	AddEmployeePage addEmp=new AddEmployeePage();
	
	login.login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
	
	CommonMethods.click(home.PIM);
	CommonMethods.click(home.addEmployee);
	
	CommonMethods.sendText(addEmp.firstName, fName);
	CommonMethods.sendText(addEmp.middleName, mName);
	CommonMethods.sendText(addEmp.lastName, lName);
	
	CommonMethods.click(addEmp.location);
	
	CommonMethods.selectList(addEmp.locationList, location);
	
	CommonMethods.click(addEmp.saveBtn);
	Thread.sleep(1000);
	CommonMethods.waitForELementToBeClickable(addEmp.empCheck, 20);
	String verifText=addEmp.empCheck.getText();
	System.out.println(verifText);
	Assert.assertEquals(verifText, fName+" "+lName);
	
	Thread.sleep(9000);
	}
	
	@DataProvider(name="employee details")
	public Object [] [] getData(){
		Object[] [] data=new Object [3] [4];
		data[0][0]="John";
		data[0][1]="D";
		data[0][2]="Doe";
		data[0][3]="HQ";
		
		data[1][0]="Jane";
		data[1][1]="C";
		data[1][2]="Brown";
		data[1][3]="HQ";
		
		data[2][0]="Karen";
		data[2][1]="L";
		data[2][2]="Smith";
		data[2][3]="HQ";
		
		return data;
	}
	
	@DataProvider(name="Sheet1")
	public Object [] [] getEmpData() {
		ExcelUtility obj=new ExcelUtility();
		obj.openExcel(Constants.XL_FILEPATH, "Sheet1");
		
		int rows=obj.getRowNum();
		int cols=obj.getColNum(0);
		
		Object[] [] data=new Object[rows-1] [cols];
		for(int i=1; i<rows; i++) {
			for( int j=0; j<cols; j++) {
				String value=obj.getCellData(i, j);
				data[i-1][j]=value;
			}
		}
		return data;
	}
}
