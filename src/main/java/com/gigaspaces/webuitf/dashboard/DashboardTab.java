package com.gigaspaces.webuitf.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.MainNavigation;
import com.thoughtworks.selenium.Selenium;

/**
 * represents a Dashboard tab of the ui
 * @author elip
 *
 */
public class DashboardTab extends MainNavigation {
	
	/**
	 * constructs an instance. you should not explicitly use this constructor.
	 * in order to switch between tabs use the various switchTo methods found in
	 * the tab classes
	 * @param selenium
	 * @param driver
	 */
	public DashboardTab(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
	}
		
	
	public ServicesGrid getServicesGrid() {
		return ServicesGrid.getInstance(selenium, driver);
	}
	
	public StatusGrid getStatusGrid() {
		return StatusGrid.getInstance(selenium, driver);
	}
	
	public boolean isXap() {
		System.out.println("checking if this is xap");
		driver.findElement(By.id("gs-about-button")).click();
		return selenium.isTextPresent("XAP Premium");
	}
	
	public DashboardSubPanel getDashboardSubPanel() {
		return new DashboardSubPanel(selenium,driver);
	}
}
