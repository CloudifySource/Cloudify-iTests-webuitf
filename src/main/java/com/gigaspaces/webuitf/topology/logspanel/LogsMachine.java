package com.gigaspaces.webuitf.topology.logspanel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openspaces.admin.gsc.GridServiceContainer;
import org.openspaces.admin.gsm.GridServiceManager;

import com.gigaspaces.webuitf.WebConstants;
import com.thoughtworks.selenium.Selenium;

public class LogsMachine extends ControllerLogsMachine{
	
	private String puName;
	private String id;
	
	public LogsMachine(String id, String machineName, String puName, Selenium selenium, WebDriver driver) {
		super(id,machineName, selenium, driver);
		this.driver = driver;
		this.selenium = selenium;
		this.machineName = machineName;
		this.id = id;
		this.puName = puName;
		this.xpath = "//div[@id='" + id + "']";
	}
	
	public LogsGridServiceContainer getContianer(GridServiceContainer contianer) {

		String contianerPId = contianer.getVirtualMachine().getStatistics().getDetails().getPid() + "";

		String realId = null;
		
		List<WebElement> elements = driver.findElement(By.id(WebConstants.ID.logsPanel)).findElements(By.className("x-tree3-node"));
		for (WebElement el : elements) {
			String id = el.getAttribute("id");
			if ((id != null) && (id.contains(contianerPId))) {
				realId = id;
				break;
			}
		}		
	
		if (realId != null) {
			return new LogsGridServiceContainer(realId, contianerPId,puName, driver);
		}
		else return null;
		
	}
	
	public void selectService(String serviceName) {
		selenium.click(WebConstants.ID.getLogServiceId(serviceName));
		
	}
	
	public boolean containsGridServiceContainer(GridServiceContainer gsc) {
		
		List<String> services = getServices();
		String pid = "" + gsc.getVirtualMachine().getDetails().getPid() + "";
		for (String s : services) {	
			if (s.contains("gsc") && s.contains(pid)) return true;
		}
		return false;
		
	}
	
	public boolean containsGridServiceManager(GridServiceManager gsm) {
		
		List<String> services = getServices();
		String pid = "" + gsm.getVirtualMachine().getDetails().getPid() + "";
		for (String s : services) {	
			if (s.contains("gsm") && s.contains(pid)) return true;
		}
		return false;
		
	}
	
	public void collapseOrExpandMachine() {
		selenium.click(xpath + WebConstants.Xpath.pathToLogsMachineExpandButton);
		
	}
}
