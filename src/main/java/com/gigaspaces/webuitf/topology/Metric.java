package com.gigaspaces.webuitf.topology;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.topology.healthpanel.MetricPopup;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class Metric {
	
	private WebElement metric;
	private WebDriver driver;
	private AjaxUtils helper;
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	public Metric(WebElement metric, WebDriver driver) {
		this.driver = driver;
		this.metric = metric;
		this.helper = new AjaxUtils(driver);
	}

	public boolean isDisplayed() {
		RemoteWebElement node = (RemoteWebElement) metric;
		return node.isDisplayed();
	}
	
	public boolean hasBalanceGuage() {
		
		try {
			@SuppressWarnings("unused")
			WebElement element = metric.findElement(By.className(WebConstants.ClassNames.balanceGauge));
			return true;
			
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean hasBarLineChart() {
		
		try {
			@SuppressWarnings("unused")
			WebElement element = metric.findElement(By.className(WebConstants.ClassNames.barLineChartContainer));
			return true;
			
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String getName() {

		WebElement element = metric.findElement(By.tagName("button"));
		return element.getText();

	}
	
	public void swithToMetric(final MetricType metricType) {
		
		int timeout = 10;
		
		final String name = metricType.getName();
		final String type = metricType.getType();
		final String dis = metricType.getDistrib();
		
		logger.info("Clicking on metric button");
		WebElement element = metric.findElement(By.tagName("button"));
		element.click();
		
		Actions builder = new Actions(driver);
		WebElement webElement = helper.waitForElement(By.xpath(WebConstants.Xpath.getPathToMetricHoverMenuOption(type)), timeout);
		builder.moveToElement(webElement).build().perform();
		
		if (dis != null) {
			webElement = helper.waitForElement(By.xpath(WebConstants.Xpath.getPathToMetricHoverMenuOption(dis)), timeout);
			builder.moveToElement(webElement).build().perform();
		}
		
		By by = By.xpath(WebConstants.Xpath.getPathToMetricHoverMenuOption(name));
		By[] bys = {by};
		helper.clickWhenPossible(timeout, TimeUnit.SECONDS, bys);		
	}
	
	public Double getBalance() {
		WebElement balance = metric.findElement(By.cssSelector("div[data-widget='balance-gauge']"));
		return Double.parseDouble(balance.getAttribute("data-value"));
	}
	
	/**
	 * return the number of lines currently in the Metric
	 * note - if this is an instance of the metric in health panel, do not use this method
	 * @return
	 */
	public int getNumberOfGraphs() {		
		double seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				WebElement metricElement = driver.findElement(By.id(WebConstants.ID.comparisonMetricTop));
				WebElement highChartsTracker = metricElement.findElement(By.tagName("svg")).findElement(By.className("highcharts-tracker"));
				List<WebElement> paths = highChartsTracker.findElements(By.tagName("path"));
				int count = 0;
				for (WebElement element : paths) {
					if (!element.getAttribute("d").equals("M 0 0")) count++;
				}
				return count;
			}
			catch (StaleElementReferenceException e) {
				logger.severe("Failed to discover element due to statistics update, retyring...");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				seconds = seconds + 0.1;
			}
		}
		return 0;
	}

	public MetricPopup getPopup() {
		WebElement balance = metric.findElement(By.cssSelector("div[data-widget='balance-gauge']"));
		balance.click();
		MetricPopup metricPopup = new MetricPopup(driver);
		String title = null;
		String averageValue = null;
		try {
			logger.info("waiting for popup to appear");
			title = helper.waitForElement(By.xpath(WebConstants.Xpath.pathToMetricPopupTitle), 10).getText();
			averageValue = helper.waitForElement(By.xpath(WebConstants.Xpath.pathToMetricPopupAverageValue), 3).getText();
		}
		catch (WebDriverException e) {
			logger.severe("timeout waiting for pop to appear");
			return null;
		}
		String temp = averageValue.split(" ")[2];
		
		double val = Double.valueOf(temp.substring(0, temp.length() - 1));
		metricPopup.setAverageValue(val);
		metricPopup.setTitle(title);
		return metricPopup;
	}

}
