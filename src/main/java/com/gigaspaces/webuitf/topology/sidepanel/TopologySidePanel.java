package com.gigaspaces.webuitf.topology.sidepanel;

import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.WebConstants;
import com.thoughtworks.selenium.Selenium;

public class TopologySidePanel {
	
	WebDriver driver;
	Selenium selenium;
	
	public TopologySidePanel(WebDriver driver, Selenium selenium) {
		this.driver = driver;
		this.selenium = selenium;
	}
	
	public void toggleShowHide() {
		// TODO implement
	}
	
	public ComparisonCharts switchToComparisonCharts() {
		selenium.click(WebConstants.Xpath.pathToComparisonCharts);
		return new ComparisonCharts(driver);
	}
	
	public ComparisonCharts getComparisonCharts() {
		return new ComparisonCharts(driver);
	}
	
	
	public WebUIServiceDetails switchToServiceDetails() {
		selenium.click(WebConstants.Xpath.pathToServiceDetails);
		return new WebUIServiceDetails(selenium);
	}
	
}
