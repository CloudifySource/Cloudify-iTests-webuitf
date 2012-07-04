package com.gigaspaces.webuitf.topology.logicalpanel;

import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.topology.TopologySubPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;

public class LogicalPanel extends TopologySubPanel {
	
	private AjaxUtils helper = new AjaxUtils();
	
	public LogicalPanel(Selenium selenium, WebDriver driver) {
		this.driver = driver;
		this.selenium = selenium;
		helper.setDriver(driver);
	}
	
	public WebUIProcessingUnitInstance getProcessingUnitInstance(String instanceName) {
		WebUIProcessingUnitInstance instance = new WebUIProcessingUnitInstance(instanceName, driver, selenium);
		if (instance.getName() != null) {
			return instance;
		}
		return null;
	}
}
