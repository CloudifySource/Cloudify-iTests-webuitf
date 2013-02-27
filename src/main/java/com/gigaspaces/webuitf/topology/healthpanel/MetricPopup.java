package com.gigaspaces.webuitf.topology.healthpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.WebConstants;

public class MetricPopup {
	
	private WebDriver driver;
	
	private String title;
	private double averageValue;
	
	public MetricPopup(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getAverageValue() {
		return averageValue;
	}

	public void setAverageValue(double averageValue) {
		this.averageValue = averageValue;
	}
	
	public void close() {
		driver.findElement(By.id(WebConstants.ID.metricPopup)).findElement(By.tagName("button")).click();
	}

	public ExtremeMetricCases getExtremeHighCases() {
		return new ExtremeMetricCases(driver, false);		
	}
	
	public ExtremeMetricCases getExtremeLowCases() {
		return new ExtremeMetricCases(driver, true);
	}
}
