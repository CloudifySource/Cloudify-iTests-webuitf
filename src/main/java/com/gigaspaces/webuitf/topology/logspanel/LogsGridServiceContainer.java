package com.gigaspaces.webuitf.topology.logspanel;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;


public class LogsGridServiceContainer {

	@SuppressWarnings("unused")
	private String contianerId;
	private String xpath;
	@SuppressWarnings("unused")
	private String puName;
	private WebDriver driver;
	
	public LogsGridServiceContainer(String id, String contianerId ,String puName, WebDriver driver) {
		this.driver = driver;
		this.contianerId = contianerId;
		this.xpath = "//div[@id='" + id + "']";
		this.puName = puName;
	}
	
	public List<String> getPuInstances() {	
		Exception exception = null;
		List<String> services = new ArrayList<String>();
		int i = 1;
		while (exception == null) {
			try {
				WebElement serviceElement = driver.findElement(By.
						xpath(xpath + WebConstants.Xpath.getPathToLogsContianerPuInstanceByIndex(i)));
				String service = serviceElement.getText();
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
}
