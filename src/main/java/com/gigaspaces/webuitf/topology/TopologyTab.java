package com.gigaspaces.webuitf.topology;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.BaseApplicationContextPanel;
import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.topology.applicationmap.ApplicationMap;
import com.gigaspaces.webuitf.topology.sidepanel.TopologySidePanel;
import com.thoughtworks.selenium.Selenium;

public class TopologyTab extends BaseApplicationContextPanel {

	public TopologyTab(Selenium selenium, WebDriver driver) {
		super(selenium, driver);
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
	
	public boolean doesApplicationExist(String applicationName) {
		
		try{
			super.selectApplication(applicationName, WebConstants.ID.topologyCombobox);			
		} 
		catch(TimeoutException toe){
			return false;
		}
		return true;
	}

	public String getSelectedApplication() {
		return super.getSelectedApplication(WebConstants.ID.topologyCombobox);
	}
	
	public boolean isMaskedNoContext() {
		return super.isMaskedNoContext(WebConstants.ID.topologyPanel);
	}
	
	public boolean isMaskedLoading() {
		String maskMessage = "Loading application map...";
		return super.isMaskedLoading(WebConstants.ID.topologyPanel, maskMessage);
	}
}
