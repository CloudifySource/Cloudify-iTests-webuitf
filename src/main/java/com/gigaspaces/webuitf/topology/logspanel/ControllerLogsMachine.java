package com.gigaspaces.webuitf.topology.logspanel;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.thoughtworks.selenium.Selenium;

public class ControllerLogsMachine {

	protected String machineName;
	protected String xpath;
	protected Selenium selenium;
	protected WebDriver driver;

	public ControllerLogsMachine(String id, String machineName, Selenium selenium, WebDriver driver) {
		this.driver = driver;
		this.selenium = selenium;
		this.machineName = machineName;
		this.xpath = "//div[@id='" + id + "']";
	}

	public List<String> getServices() {

		Exception exception = null;
		List<String> services = new ArrayList<String>();
		int i = 1;
		while (exception == null) {
			try {
				WebElement serviceElement = driver.findElement(By.
						xpath(xpath + WebConstants.Xpath.getPathToLogsMachineServiceByIndex(i)));
				String service = serviceElement.getAttribute("id").substring(34);
				services.add(service);
				i++;
			}
			catch (NoSuchElementException e) {
				exception = e;
			}
			catch (WebDriverException e) {
				exception = e;
			}
		}
		return services;

	}

	public void selectService(String serviceName) {
		selenium.click(WebConstants.ID.getControllerLogServiceId(serviceName));
	}

	public void collapseOrExpandMachine() {
		selenium.click(xpath + WebConstants.Xpath.pathToLogsMachineExpandButton);

	}
}
