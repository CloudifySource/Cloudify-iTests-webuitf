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

	private static final int WAIT_TIMEOUT_IN_MS = 5000;

	public HealthPanel(Selenium selenium, WebDriver driver) {
		super(selenium, driver);
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
			element = helper.waitForElement(By.className(name), WAIT_TIMEOUT_IN_MS );
			metric = new Metric(element, driver);
		}
		catch (WebDriverException e) {
			System.out.println("didn't find class " + name);
			return null;
		}
		return metric;
	}
	
/*	public boolean isMasked() {
		WebElement el = driver.findElement(By.id(WebConstants.ID.healthPanel));
		return helper.isElementMasked(el);
	}
*/	
}
