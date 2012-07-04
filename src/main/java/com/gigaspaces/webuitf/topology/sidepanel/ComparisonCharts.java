package com.gigaspaces.webuitf.topology.sidepanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.topology.Metric;

public class ComparisonCharts {
	
	private WebDriver driver;
	
	public ComparisonCharts(WebDriver driver) {
		this.driver = driver;
	}
	
	public Metric getTopMetric() {
		WebElement metricElement = driver.findElement(By.id(WebConstants.ID.comparisonMetricTop));
		return new Metric(metricElement, driver);
		
	}
	
	public Metric getBottomMetric() {
		WebElement metricElement = driver.findElement(By.id(WebConstants.ID.comparisonMetricBottom));
		return new Metric(metricElement, driver);
	}


}
