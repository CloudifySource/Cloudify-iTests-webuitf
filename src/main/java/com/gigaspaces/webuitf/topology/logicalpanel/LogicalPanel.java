package com.gigaspaces.webuitf.topology.logicalpanel;

import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.topology.TopologySubPanel;
import com.thoughtworks.selenium.Selenium;

public class LogicalPanel extends TopologySubPanel {
	
	public LogicalPanel(Selenium selenium, WebDriver driver) {
		this.driver = driver;
		this.selenium = selenium;
	}
	
	public WebUIProcessingUnitInstance getProcessingUnitInstance(String instanceName) {
		WebUIProcessingUnitInstance instance = new WebUIProcessingUnitInstance(instanceName, driver, selenium);
		if (instance.getName() != null) {
			return instance;
		}
		return null;
	}
}
