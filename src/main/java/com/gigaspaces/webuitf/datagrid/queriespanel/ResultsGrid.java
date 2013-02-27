package com.gigaspaces.webuitf.datagrid.queriespanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class ResultsGrid {
	
	private static final String NEXT_BUTTON_ID = "query-results-navigation-next";
	private static final String BACK_BUTTON_ID = "query-results-navigation-previous";
	private static final String FIRST_PAGE_BUTTON_ID = "query-results-navigation-first";
	private static final String LAST_PAGE_BUTTON_ID = "query-results-navigation-last";
	private static final String RESULTS_GRID_ID = "gs-query-results-grid";
	private static final String PATH_TO_RESULTS_PROPS = "//div[@id='" + RESULTS_GRID_ID + "']/div/div/div[1]";
	private static final String PATH_TO_RESULTS = "//div[@id='" + RESULTS_GRID_ID + "']/div/div/div[2]/div";
	
	private WebDriver driver;
	
	public ResultsGrid(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean areResultsAvailable() {
		try {
			driver.findElement(By.id(RESULTS_GRID_ID));
			return true;
		}
		catch (WebDriverException e) {
			return false;
		}
	}
	
	public List<ResultRow> getResults() {
		
		try {
			int propsSize = driver.findElement(By.xpath(PATH_TO_RESULTS_PROPS)).findElements(By.tagName("td")).size();	
			int resultsSize = driver.findElement(By.xpath(PATH_TO_RESULTS)).findElements(By.tagName("table")).size();
			List<ResultRow> results = new ArrayList<ResultRow>();
			for (int i = 0 ; i < resultsSize ; i++) {
				String relativePathToResultRow = "/div[" + (i +1) + "]";
				Map<String,String> resultRowMap = new HashMap<String,String>();
				for (int j = 0 ; j < propsSize ; j++) {
					String relativePathToPropValue = "/table/tbody/tr/td[" + (j + 1) + "]";
					WebElement resultRowColumn = driver.findElement(By.xpath(PATH_TO_RESULTS + relativePathToResultRow + relativePathToPropValue));
					String prop = resultRowColumn.getAttribute("class").split(" ")[2].split("-")[3];
					String value = resultRowColumn.getText();
					resultRowMap.put(prop.toLowerCase().trim(), value.toLowerCase().trim());
				}
				ResultRow row = new ResultRow(resultRowMap);
				results.add(row);
			}
			return results;
		}
		catch (WebDriverException e) {
			return null;
		}
		
	}
	
	public void next() {
		driver.findElement(By.id(NEXT_BUTTON_ID)).click();
	}
	
	public void previous() {
		driver.findElement(By.id(BACK_BUTTON_ID)).click();
	}
	
	public void last() {
		driver.findElement(By.id(LAST_PAGE_BUTTON_ID)).click();
	}
	
	public void first() {
		driver.findElement(By.id(FIRST_PAGE_BUTTON_ID)).click();
	}
}
