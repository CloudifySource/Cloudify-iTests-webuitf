package com.gigaspaces.webuitf.topology.logspanel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openspaces.admin.machine.Machine;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.topology.TopologySubPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;
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

	/**
	 * only for cloudify webui
	 * @param machine
	 * @return
	 */
	public ControllerLogsMachine getControllerLogsMachine(Machine machine){
		
		String id;
		String realId = null;
		String machineName = null;
		AjaxUtils helper = new AjaxUtils();
		WebElement controllerLogs = driver.findElement(By.id(WebConstants.ID.controllerLogs));
		List<WebElement> elements = controllerLogs.findElements(By.className("x-tree3-node"));
	
		for (WebElement el : elements) {
			id = helper.retrieveAttribute(el, "id");
			
			if ((id != null)) {								
				if(id.contains(machine.getHostName())){
					machineName = machine.getHostName();
					realId = id;
					break;
				}
				else if(id.contains(machine.getHostAddress())){
					machineName = machine.getHostAddress();
					realId = id;
					break;
				}				
			}
		}		
		if (realId != null) {
			return new ControllerLogsMachine(realId, machineName, selenium, driver);
		}
		
		else return null;
	}
	
}
