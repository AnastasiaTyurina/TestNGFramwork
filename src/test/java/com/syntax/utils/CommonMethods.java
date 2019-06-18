package com.syntax.utils;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends BaseClass {
	

	/**
	 *@author Anastasia
	 * This method will select a specified value from a drop down
	 * @param element and text
	*/
	public static void selectValueFromDD(WebElement element, String text) {
		Select select = new Select(element);
		List <WebElement> options=select.getOptions();
		boolean isSelected=false;
		for(WebElement option:options) {
			String optionText=option.getText();
			if(optionText.equals(text)) {
				select.selectByVisibleText(text);
				isSelected=true;
				break;
			}
		}
		if(!isSelected) {
			System.out.println("Option with text "+text+" is not available");
		}
	}
	public static void selectValueFromDD(WebElement element, int index) {
		Select select=new Select(element);
		List<WebElement> options=select.getOptions();
		if(options.size()>index) {
			select.deselectByIndex(index);
		}else {
			System.out.println("Invalid entry");
		}
	}
	public static void sendText(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
		String text=element.getAttribute("value");
		
	}
	
	/**
	 * Method will accept alert
	 * @throws NoAlertPresentException if alert is not present
	*/
	public static void acceptAlert() {
		try {
		Alert alert=driver.switchTo().alert();
		alert.accept();
		}catch(NoAlertPresentException e) {
			System.out.println("Alert is not present");
		}
	}
	
	/**
	 * Method will dismiss alert
	 * @throws NoAlertPresentException if alert is not present
	*/
	public static void dismissAlert() {
		try {
			Alert alert=driver.switchTo().alert();
			alert.dismiss();
		}catch(NoAlertPresentException e){
			System.out.println("Alert is not present");
		}
	}
	
	/**
	 * Method will get text of an alert
	 * @throws NoAlertPresentException if alert is not present
	 * @return String text
	*/
	public static String getAlertText() {
		try {
			Alert alert=driver.switchTo().alert();
			return alert.getText();
		}catch(NoAlertPresentException e){
			System.out.println("Alert is not present");
			return null;
		}
	}
	
	/**
	 * Method will switch control to a specified frame
	 * @param frame id or frame name
	*/
	public static void switchToFrame(String idOrName) {
		try {
		driver.switchTo().frame(idOrName);
		}catch(NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}
	
	/**
	 * Method will switch control to a specified frame
	 * @param frame element
	*/
	public static void switchToFrame(WebElement element) {
		try {
		driver.switchTo().frame(element);
		}catch(NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}
	
	/**
	 * Method will switch control to a specified frame
	 * @param frame index
	*/
	public static void switchToFrame(int index) {
		try {
		driver.switchTo().frame(index);
		}catch(NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}
	
	public static void selectRadioButtonOrCheckBox(WebElement element) {
		element.click();
		System.out.println(element.isDisplayed());
		System.out.println(element.isEnabled());
		System.out.println(element.isSelected());
	}
	
	public static void verifyEntry(WebElement element, String expectedText) {
		String text=element.getAttribute("value");
		if(text.equals(expectedText)) {
			System.out.println("Valid entry");
		}else {
			System.out.println("Invalid entry");
		}
	}
	
	/**
	 * Method that waits for the element to become visible
	 * @param element
	 * @param time
	 */
	
	public static void waitForElementToBeVisible(WebElement element, int time) {
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForElementToBeVisible (By locator, int time) {
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * Method that waits for the elements to be clickable
	 * @param element
	 * @param time
	 */
	
	public static void waitForELementToBeClickable(WebElement element, int time) {
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitForELementToBeClickable(By locator, int time) {
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static String takeScreenshot(String fileName) {
		TakesScreenshot ts=(TakesScreenshot)driver;
        File scr=ts.getScreenshotAs(OutputType.FILE);
        String destination=System.getProperty("user.dir")+"/target/screenshots/"+fileName+".png";
        try {
			FileUtils.copyFile(scr, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to take screesnhot");
		}
        return destination;
	}
	
	public static void scroolDown(int pixels) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,"+pixels+")");
	}
	
	public static void scroolUp(int pixels) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-"+pixels+")");
	}
	
	public static void jsClick(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",element);
	}
	
	public static void click(WebElement element) {
		element.click();
	}
	
	public static void selectList(WebElement element, String text) {
		List<WebElement> listLocations=element.findElements(By.tagName("li"));
		
		for(WebElement li:listLocations) {
			String liText=li.getAttribute("innerHTML");
			
			if(liText.contains(text)) {
				li.click();
				break;
			}
		}
	}
}
