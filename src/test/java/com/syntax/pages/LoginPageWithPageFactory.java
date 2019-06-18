package com.syntax.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.syntax.utils.BaseClass;
import com.syntax.utils.CommonMethods;
import com.syntax.utils.ConfigsReader;

public class LoginPageWithPageFactory extends BaseClass {

	@FindBy(id="txtUsername")
	public WebElement userName;
	
	@FindBy(name="txtPassword")
	public WebElement password;
	
	@FindBy(id="btnLogin")
	public WebElement button;
	
	@FindBy(css="img[src*='logo.png']")
	public WebElement logo;
	
	//initializing all of our variables
	
	public LoginPageWithPageFactory() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='toast-message']")
	public WebElement error;
	
	@FindBy(css="span[id='spanMessage']")
	public WebElement emptyCredentials;
	
	public void login(String uname, String pwd) {
		CommonMethods.sendText(userName, uname);
		CommonMethods.sendText(password, pwd);
		CommonMethods.click(button);
	}
	
}
