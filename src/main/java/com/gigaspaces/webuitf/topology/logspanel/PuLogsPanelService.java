package com.gigaspaces.webuitf.topology.logspanel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openspaces.admin.machine.Machine;
import org.openspaces.admin.pu.ProcessingUnit;

import com.gigaspaces.webuitf.WebConstants;
import com.thoughtworks.selenium.Selenium;

public class PuLogsPanelService {
	
	private WebDriver driver;
	private Selenium selenium;
	private String puName;
	
	public PuLogsPanelService(WebDriver driver, Selenium selenium, String puName) {
		this.driver = driver;
		this.selenium = selenium;
		this.puName = puName;
	}

	public LogsMachine getMachine(Machine machine, ProcessingUnit processingUnit) {

		
		String realId = null;
		
		List<WebElement> elements = driver.findElement(By.id(WebConstants.ID.logsPanel)).findElements(By.className("x-tree3-node"));
		for (WebElement el : elements) {
			String id = el.getAttribute("id");
			if ((id != null) && (id.contains(machine.getHostName())) && (id.contains(processingUnit.getName()))) {
				realId = id;
				break;
			}
		}		

		
		if (realId != null) {
			return new LogsMachine(realId,machine.getHostName(), puName, selenium, driver);			
		}
		else return null;
		
	}
	
	public void collapseOrExpandPu() {
		selenium.click(WebConstants.Xpath.getPathToPuLogsExapndButton(puName));
		
	}
}
