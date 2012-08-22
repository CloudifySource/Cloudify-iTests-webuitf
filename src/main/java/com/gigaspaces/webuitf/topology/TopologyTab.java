package com.gigaspaces.webuitf.topology;

import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.BaseApplicationContextPanel;
import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.topology.applicationmap.ApplicationMap;
import com.gigaspaces.webuitf.topology.sidepanel.TopologySidePanel;
import com.thoughtworks.selenium.Selenium;

public class TopologyTab extends BaseApplicationContextPanel {

	public TopologyTab(WebDriver driver) {
		super(driver);
	}
	
	public TopologyTab(Selenium selenium, WebDriver driver) {
		this(driver);
		this.selenium = selenium;
	}

	public ApplicationMap getApplicationMap() {
		return new ApplicationMap(driver, selenium);
	}
	
	public TopologySubPanel getTopologySubPanel() {
		return new TopologySubPanel(selenium, driver);
	}
	
	public TopologySidePanel getDetailsPanel() {
		return new TopologySidePanel(driver, selenium);
	}
	
	public void selectApplication(String applicationName) {
		super.selectApplication(applicationName, WebConstants.ID.topologyCombobox);
	}

}
