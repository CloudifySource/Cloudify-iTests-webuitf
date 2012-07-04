package com.gigaspaces.webuitf;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.thoughtworks.selenium.Selenium;

/**
 * represents the login page of the web-ui
 * @author elip
 *
 */
public class LoginPage {
	
	private Logger _logger = Logger.getLogger(LoginPage.class.getName());
	
	private Selenium selenium;
	private WebDriver driver;
	
	private String username;
	private String password;
	private String jiniGroup;
	
	WebElement logginButton;
	
	/**
	 * constructs an instance with no login parameters
	 * @param selenium
	 * @param driver
	 */
	public LoginPage(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
	}
	
	/**
	 * constructs an instance with a specific lookup group
	 */
	public LoginPage(Selenium selenium, WebDriver driver, String lookupGroups) {
		this.driver = driver;
		this.selenium = selenium;
		this.jiniGroup = lookupGroups;
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
	public void inputUsernameAndPassword() {
		WebElement annon = driver.findElement(By.xpath(WebConstants.Xpath.annonymusCheckbox));
		annon.click();
		WebElement usernameEl = driver.findElement(By.id(WebConstants.ID.usernameLogginInput));
		usernameEl.sendKeys(username);
		WebElement passwordEl = driver.findElement(By.id(WebConstants.ID.passwordLogginInput));
		passwordEl.sendKeys(password);
	}
	
	/**
	 * logs in to the system
	 * @return
	 * @throws InterruptedException 
	 */
	public MainNavigation login() throws InterruptedException {
		selenium.click(WebConstants.Xpath.loginButton);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("return this.GigaSpaces.Util.Flags.isUnderTest=true");
		return new MainNavigation(selenium, driver);
	}
	
	public void assertLoginPerformed() throws InterruptedException {
		int attempts = 0;
		while (attempts < 10) {
			int seconds = 0;
			while (seconds <= 5) {
				try {
					if (seconds != 5) {
						driver.findElement(By.xpath(WebConstants.Xpath.dashBoardButton));
						Thread.sleep(1000);
						seconds++;
					}
					else {
						return;
					}
				}
				catch (NoSuchElementException e) {
					_logger.info("Unable to login, retrying...Attempt number " + (seconds + 1));
					if (selenium.isElementPresent(WebConstants.Xpath.loginButton)) {
						login();
						break;
					}
					else {
						throw new IllegalStateException("Unable to login to page because login page wasnt found");
					}


				}
			}
			attempts++;
		}
    	if (attempts == 10) {
    		throw new IllegalStateException("Test Failed because it was login because login is not stable");
    	}
	}
}
