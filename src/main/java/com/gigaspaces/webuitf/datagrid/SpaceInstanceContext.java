package com.gigaspaces.webuitf.datagrid;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class SpaceInstanceContext {
	
	private static final String PU_INSTANCES_NAME_CLASS = "x-grid3-col-pu_instance_name";
	private static final String SPACE_INSTANCE_NAME_CLASS = "x-grid3-td-slider_model_name";
	
	private static final String MEMORY_CLASS = "x-grid3-td-total_memory";
	
	private String spaceInstanceName;
	private String puInstanceName;
	
	private String id;
	private WebDriver driver;
	
	private AjaxUtils helper;
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	public SpaceInstanceContext(String id, WebDriver driver) {
		this.id = id;
		this.driver = driver;
		this.helper = new AjaxUtils(driver);
		this.helper.setDriver(driver);
		this.puInstanceName = helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.id(id),By.className(PU_INSTANCES_NAME_CLASS));
		this.spaceInstanceName = helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.id(id),By.className(SPACE_INSTANCE_NAME_CLASS));
	}
	public String getSpaceInstanceName() {
		return spaceInstanceName;
	}

	public String getPuName() {
		return puInstanceName;
	}

	
	public void select() {
		for (int i = 0 ; i < 3 ; i++) {
			helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(id),By.className(SPACE_INSTANCE_NAME_CLASS));
		}
	}
	
	public int getRowUpdateCount(long pollingIntervalInMillis, int timeRangeInSeconds) {
		
		int numberOfUpdates = 0;
		int numberOfSuccesfullClicks = 0;
		int numberOfClickAttempts = 0;
		double seconds = 0;
		while (seconds < timeRangeInSeconds) {	
			try {
				logger.info("attempting to click the element");
				numberOfClickAttempts++;
				driver.findElement(By.id(id)).findElement(By.className(MEMORY_CLASS)).click();
				logger.info("click was succesfull");
				numberOfSuccesfullClicks++;
			}
			catch (NoSuchElementException e) {
				try {
					logger.severe("caugh exception due to row update");
					Thread.sleep(pollingIntervalInMillis);
				} catch (InterruptedException e1) {
				}
				numberOfUpdates++;
				
			}
			catch (StaleElementReferenceException e) {
			}
			catch (WebDriverException e){
			}
			seconds += pollingIntervalInMillis / 1000.0;
			
		}
		logger.info("Number of click attempts : " + numberOfClickAttempts);
		logger.info("Number of succesfull clicks : " + numberOfSuccesfullClicks);
		logger.info("Number of failed clicks due to row updates : " + numberOfUpdates);
		return numberOfUpdates;
		
	}
	
	public boolean isSelected() {
		String className = helper.retrieveAttribute(By.id(id), "class", driver);
		if (className.contains("selected")) {
			return true;
		}
		return false;
	}
	
	public boolean isPrimary(){
		
		WebElement spaceModeElement = helper.waitForElement(TimeUnit.SECONDS, 5, By.id(id) , 
				By.className(WebConstants.ClassNames.dataGridSpaceMode), By.xpath(WebConstants.Xpath.relativePathToSpaceModeImage));
		
		String spaceMode = helper.retrieveAttribute(spaceModeElement, "qtip");
		
		if(spaceMode.equalsIgnoreCase("primary"))
			return true;
		
		return false;
		
	}

}
