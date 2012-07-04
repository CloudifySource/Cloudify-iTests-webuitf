package com.gigaspaces.webuitf.topology.healthpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.WebConstants;

public class ExtremeMetricCases {
	
	private WebDriver driver;
	private boolean low;
	
	public ExtremeMetricCases(WebDriver driver, boolean low) {
		this.driver = driver;
		this.low = low;
	}
	
	public ExtremeMetricCasesEntry getEntry(int index) {
		
		String basePath = null;
		if (low) {
			basePath = WebConstants.Xpath.getPathToMetricExtremeLowCasesRow(index);
		}
		else {
			basePath = WebConstants.Xpath.getPathToMetricExtremeHighCasesRow(index);
		}
		
		String instanceName = driver.findElement(By.xpath(basePath + WebConstants.Xpath.pathToInstanceNameInExtremeMetricCases)).getText();
		String hostName = driver.findElement(By.xpath(basePath + WebConstants.Xpath.pathToHostNameInExtremeMetricCases)).getText();
		double value = Double.valueOf(driver.findElement(By.xpath(basePath + WebConstants.Xpath.pathToMetricValueInExtremeMetricCases)).getText());
		
		ExtremeMetricCasesEntry entry = new ExtremeMetricCasesEntry();
		entry.setHostName(hostName);
		entry.setInstnaceName(instanceName);
		entry.setMetricValue(value);
		return entry;
	}
}
