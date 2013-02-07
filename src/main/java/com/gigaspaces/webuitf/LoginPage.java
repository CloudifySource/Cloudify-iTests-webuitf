package com.gigaspaces.webuitf;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.SharedContextConstants;
import com.thoughtworks.selenium.Selenium;

/**
 * represents the login page of the web-ui
 * @author elip
 *
 */
public class LoginPage {
	
	private Logger logger = Logger.getLogger(LoginPage.class.getName());
	
	private Selenium selenium;
	private WebDriver driver;
	
	private String username;
	private String password;
	
	private AjaxUtils helper;
	
	WebElement logginButton;
	private static int TIMEOUT_IN_SECONDS = 40;
	
	/**
	 * constructs an instance with no login parameters
	 * @param selenium
	 * @param driver
	 */
	public LoginPage(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.helper = new AjaxUtils(driver, selenium);
	}
	
	/**
	 * constructs an instance for a certain user
	 * @param selenium
	 * @param driver
	 * @param username
	 * @param password
	 */
	public LoginPage(Selenium selenium, WebDriver driver, 
			String username , String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * writes the user parameters in the text edits of the ui
	 */
	public LoginPage inputUsernameAndPassword() {
		AjaxUtils helper = new AjaxUtils(driver, selenium);
		
		WebElement usernameEl = helper.waitForElement(By.id(WebConstants.ID.usernameLogginInput), TIMEOUT_IN_SECONDS );
		usernameEl.sendKeys(username);
		WebElement passwordEl = helper.waitForElement(By.id(WebConstants.ID.passwordLogginInput), TIMEOUT_IN_SECONDS);
		passwordEl.sendKeys(password);
		
		return this;
	}
	
	public LoginPage inputUsernameAndPassword(String username , String password) {
		this.username = username;
		this.password = password;
		
		inputUsernameAndPassword();
		
		return this;
	}
	
	public LoginPage inputLookupGroup(String lookupGroup) {
		
		WebElement lookupGroupInput = helper.waitForElement(By.id(WebConstants.ID.jiniGroupInput), TIMEOUT_IN_SECONDS );
		lookupGroupInput.clear();
		lookupGroupInput.sendKeys(lookupGroup);
		
		return this;
	}
	
	/**
	 * logs in to the system
	 * @return
	 * @throws InterruptedException 
	 */
	public MainNavigation login() throws InterruptedException {
				
		helper.clickWhenPossible(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS,
				By.xpath(WebConstants.Xpath.loginButton));	
		logger.info("clicked login button");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("return this." + SharedContextConstants.NS_IS_UNDER_TEST + "=true");
				
		return new MainNavigation(selenium, driver);
	}
	
}
