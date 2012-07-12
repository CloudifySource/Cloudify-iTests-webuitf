package com.gigaspaces.webuitf.topology.healthpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.topology.Metric;
import com.gigaspaces.webuitf.topology.TopologySubPanel;
import com.thoughtworks.selenium.Selenium;

public class HealthPanel extends TopologySubPanel {

	public HealthPanel(Selenium selenium, WebDriver driver) {
		this.driver = driver;
		this.selenium = selenium;
	}
	
	public Metric getMetric(int index) {
		
		Metric metric = null;
		String classMetricNameByIndex = null;
		WebElement element;
		try {
			classMetricNameByIndex = WebConstants.ClassNames.getMetricClassNameByIndex(index);
			element = driver.findElement(By.className(classMetricNameByIndex));
			metric = new Metric(element, driver);
		}
		catch (WebDriverException e) {
			return null;
		}
		return metric;
	}
	
	public int getNumberOfMetrics() {
		return 0;
	}
	
	/**
	 * note - if there are two metrics with the same name this will return the first one
	 * @param type
	 * @return
	 */
	public Metric getMetric(String name) {
		
		name = name.replaceAll(" ", "_");
		Metric metric = null;
		WebElement element;
		try {
			element = driver.findElement(By.className(name));
			metric = new Metric(element, driver);
		}
		catch (WebDriverException e) {
			return null;
		}
		return metric;
	}
	
}
