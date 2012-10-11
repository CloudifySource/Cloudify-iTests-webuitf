package com.gigaspaces.webuitf.datagrid.queriespanel;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.SharedContextConstants;

public class QueryNavigator {
	
	private WebDriver driver;
	
	private AjaxUtils helper;
	
	public QueryNavigator(WebDriver driver) {
		this.driver = driver;
		this.helper = new AjaxUtils(driver);
	}

	/**
	 * navigates to the next query in the editor. assuming there is one.
	 */
	public void next() {
		String path = WebConstants.Xpath.pathToQueryNavigatorNextButton;
		driver.findElement(By.xpath(path)).click();
	}
	
	/**
	 * navigates to the previous query in the editor. assuming there is one.
	 */
	public void previous() {
		String path = WebConstants.Xpath.pathToQueryNavigatorPrevButton;
		driver.findElement(By.xpath(path)).click();
	}
	
	/**
	 * executes the query.
	 * @param sqlQuery - the query to execute on the selected space.
	 * @return the query saved as the current query in the JavaScript structure on the page.
	 */
	public void runQuery(String sqlQuery) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("return this." + SharedContextConstants.NS_CODE_MIRROR_QUERIES + ".setValue(\"" + sqlQuery + "\")");
		driver.findElement(By.id(WebConstants.ID.queryNavigatorRun)).click();
	}
	
	public boolean waitForResults(int timeoutInSeconds) {
		try {
			helper.waitForElement(By.id(WebConstants.ID.queryResultsGrid), timeoutInSeconds);
			return true;
		}
		catch (TimeoutException e) {
			return false;
		}
	}
	
	/**
	 * @return the currently displayed query in the editor.
	 */
	public String getDisplayedQuery() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String text = (String)js.executeScript("return this." + SharedContextConstants.NS_CODE_MIRROR_QUERIES + ".getValue()");
		return text;
	}
	
	/**
	 * @return a list of all previously entered queries. up to the set threshold.
	 */
	public List<String> getPreviuosQueries() {
		String path = WebConstants.Xpath.pathToQueryNavigatorShowPrevButton;
		driver.findElement(By.xpath(path)).click();
		List<WebElement> queries = driver.findElements(By.tagName("a"));
		List<String> result = new ArrayList<String>();
		for (WebElement webElement : queries) {
			result.add(webElement.getText());
		}
		return result;
	}
	
	public List<String> getNextQueries() {
		String path = WebConstants.Xpath.pathToQueryNavigatorShowNextButton;
		driver.findElement(By.xpath(path)).click();
		List<WebElement> queries = driver.findElements(By.tagName("a"));
		List<String> result = new ArrayList<String>();
		for (WebElement webElement : queries) {
			result.add(webElement.getText());
		}
		return result;
	}
}
