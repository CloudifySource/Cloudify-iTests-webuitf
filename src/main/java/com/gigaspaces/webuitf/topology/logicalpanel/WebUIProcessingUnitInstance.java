package com.gigaspaces.webuitf.topology.logicalpanel;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.Icon;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebUIProcessingUnitInstance {
	
	private Logger _logger = Logger.getLogger(WebUIProcessingUnitInstance.class.getName());
	
	private String name;
	private WebDriver driver;
	private Selenium selenium;
	
	private AjaxUtils helper;
	
	public WebUIProcessingUnitInstance(String name, WebDriver driver, Selenium selenium) {
		
		try {
			this.name = name;
            this.driver = driver;
            this.selenium = selenium;
            this.helper = new AjaxUtils(driver);

            String id = WebConstants.ID.getPuInstanceId(name);
            @SuppressWarnings("unused")
            WebElement hostElement = helper.waitForElement(By.id(id), AjaxUtils.ajaxWaitingTime);
        }
		catch (NoSuchElementException e) {
		}
		catch (WebDriverException e) {
		}
	}
	
	public boolean select() {
		String id = WebConstants.ID.getPuInstanceId(name);
		try {
			helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(id));
		}
		catch (TimeoutException e) {
			return false;
		}
		return true;
	}
	
	public String getName() {
		return name;
	}
	
	public Icon getIcon() {
		
		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				String id = WebConstants.ID.getPuInstanceId(name);
				WebElement hostElement = driver.findElement(By.id(id));
				WebElement icon = hostElement.findElement(By.className("x-grid3-td-status")).findElement(By.tagName("span"));
				String type = icon.getAttribute("class").trim();
				if (type.equals(WebConstants.ID.okIcon)) return Icon.OK;
				if (type.equals(WebConstants.ID.criticalIcon)) return Icon.CRITICAL;
				if (type.equals(WebConstants.ID.warningIcon)) return Icon.ALERT;
				if (type.equals(WebConstants.ID.naIcon)) return Icon.NA;
				return null;
			}
			catch (StaleElementReferenceException e) {
				_logger.info("Failed to discover element due to statistics update, retyring...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				seconds++;
			}
		}
		return null;
		
	}
	
	public String getHostName() {
		
		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				String id = WebConstants.ID.getPuInstanceId(name);
				WebElement hostElement = driver.findElement(By.id(id));
				WebElement cores = hostElement.findElement(By.className("x-grid3-td-hostName"));
				return cores.getText();
			}
			catch (StaleElementReferenceException e) {
				_logger.info("Failed to discover element due to statistics update, retyring...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				seconds++;
			}
		}
		return null;
		
	}
	
	public boolean isPrimary(){
		
		String id = WebConstants.ID.getPuInstanceId(name);
		WebElement spaceModeElement = helper.waitForElement(TimeUnit.SECONDS, AjaxUtils.ajaxWaitingTime, By.id(id) , 
				By.className(WebConstants.ClassNames.topologySpaceMode), By.xpath(WebConstants.Xpath.relativePathToSpaceModeImage));
		
		String spaceMode = helper.retrieveAttribute(spaceModeElement, "qtip");
		
		if(spaceMode != null && spaceMode.equalsIgnoreCase("primary"))
			return true;
		return false;		
	}

	public void goToLogs() {
		
		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				String id = WebConstants.ID.getPuInstanceId(name);
				WebElement hostElement = driver.findElement(By.id(id));
				WebElement actions = hostElement.findElement(By.className("x-grid3-td-actions")).findElement(By.tagName("button"));
				actions.click();
				WebElement goToLogsItem = driver.findElement(By.id(WebConstants.ID.goToLogsItemID));
				goToLogsItem.click();
				break;
			}
			catch (StaleElementReferenceException e) {
				_logger.info("Failed to discover element due to statistics update, retyring...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				seconds++;
			}
		}
		
	}
	
	public void restart() {
		
		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				String id = WebConstants.ID.getPuInstanceId(name);
				WebElement hostElement = driver.findElement(By.id(id));
				WebElement actions = hostElement.findElement(By.className("x-grid3-td-actions")).findElement(By.tagName("button"));
				actions.click();
				WebElement restartItem = driver.findElement(By.id(WebConstants.ID.restartPuInstanceItem));
				restartItem.click();
				selenium.click(WebConstants.Xpath.acceptAlert);
				break;
			}
			catch (StaleElementReferenceException e) {
				_logger.info("Failed to discover element due to statistics update, retyring...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				seconds++;
			}
		}
		
	}


}
