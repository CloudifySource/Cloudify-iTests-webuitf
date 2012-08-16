package com.gigaspaces.webuitf.topology;

import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.MainNavigation;
import com.gigaspaces.webuitf.topology.applicationmap.ApplicationMap;
import com.gigaspaces.webuitf.topology.sidepanel.TopologySidePanel;
import com.thoughtworks.selenium.Selenium;

public class TopologyTab extends MainNavigation {

	public TopologyTab(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
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
}
