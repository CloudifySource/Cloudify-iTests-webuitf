package com.gigaspaces.webuitf.topology.logspanel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.topology.TopologySubPanel;
import com.thoughtworks.selenium.Selenium;

public class LogsPanel extends TopologySubPanel {
	
	public LogsPanel(WebDriver driver, Selenium selenium) {
		this.driver = driver;
		this.selenium = selenium;
	}
	
	public void toggleSampling() {
		selenium.click(WebConstants.Xpath.pathToLogsSamplingButton);
	}
	
	public String getCurrentLog() {
		WebElement logsPanel = driver.findElement(By.id(WebConstants.ID.logsPanel));
		try {
			WebElement selectedItem  = logsPanel.findElement(By.className(WebConstants.ClassNames.selectedItemInLogsTree));
			return selectedItem.getText();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public PuLogsPanelService getPuLogsPanelService(String puName) {
		String realId = null;
		
		List<WebElement> elements = driver.findElement(By.id(WebConstants.ID.logsPanel)).findElements(By.className("x-tree3-node"));
		for (WebElement el : elements) {
			String id = el.getAttribute("id");
			if ((id != null) && (id.contains(puName))) {
				if (id.contains("gsa")) continue;
				if (id.contains("gsc")) continue;
				if (id.contains("gsm")) continue;
				realId = id;
				break;
			}
		}		
		if (realId != null) {
			return new PuLogsPanelService(driver, selenium, puName);
		}
		else return null;
	}
	
	public String getApplicationName() {
		WebElement element = driver.findElement(By.xpath(WebConstants.Xpath.pathToApplicationNameInLogsPanel));
		return element.getText();
	}

}
