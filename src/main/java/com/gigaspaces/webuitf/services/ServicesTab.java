package com.gigaspaces.webuitf.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.MainNavigation;
import com.gigaspaces.webuitf.ProcessingUnitDeployWindow;
import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * represents the Services tab of thw web-ui
 * @author elip
 *
 */
public class ServicesTab extends MainNavigation {

	/**
	 * constructs an empty instance
	 * @param selenium
	 * @param driver
	 */
	public ServicesTab(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.helper = new AjaxUtils(driver, selenium);
	}

	/**
	 * opens the EDG deployment menu with certain deployment parameters
	 * @param dataGridName
	 * @param isSecured
	 * @param userName
	 * @param password
	 * @param numberOfInstances
	 * @param numberOfBackups
	 * @param clusterSchema
	 * @param maxInstPerVM
	 * @param maxInstPerMachine
	 * @return a DeployWindow object representing a specific deployment window
	 */
	public IDeployWindow openEDGDeployWindow(String dataGridName, String isSecured,
			String userName, String password, String numberOfInstances,
			String numberOfBackups, String clusterSchema, String maxInstPerVM,
			String maxInstPerMachine) {
		selenium.click(WebConstants.Xpath.deployMenuButton);
		selenium.click(WebConstants.ID.deployEDGOption);
		return new DataGridDeployWindow(selenium, driver, dataGridName, isSecured, 
				userName, password, numberOfInstances, numberOfBackups, 
				clusterSchema, maxInstPerVM, maxInstPerMachine);
	}
	
	public IDeployWindow openMemcachedDeployWindow(String spaceUrl, String isSecured,
			String userName, String password, String numberOfInstances,
			String numberOfBackups, String clusterSchema, String maxInstPerVM,
			String maxInstPerMachine) {
		selenium.click(WebConstants.Xpath.deployMenuButton);
		selenium.click(WebConstants.ID.deployMemcachedOption);
		return new MemcachedDeployWindow(selenium, driver, spaceUrl, isSecured, 
				userName, password, numberOfInstances, numberOfBackups, 
				clusterSchema, maxInstPerVM, maxInstPerMachine);
	}
	
	public IDeployWindow openProcessingUnitDeployWindow(String puName, String isSecured,
			String userName, String password, String numberOfInstances,
			String numberOfBackups, String clusterSchema, String maxInstPerVM,
			String maxInstPerMachine) {
		selenium.click(WebConstants.Xpath.deployMenuButton);
		selenium.click(WebConstants.ID.deployProcessingUnitOption);
		return new ProcessingUnitDeployWindow(selenium, driver, puName, isSecured, 
				userName, password, numberOfInstances, numberOfBackups, 
				clusterSchema, maxInstPerVM, maxInstPerMachine);
	}
	
	/**
	 * retrieve the Host and Services grid from the Topology tab
	 * @return the Host and Services singelton
	 */
	public HostsAndServicesGrid getHostAndServicesGrid() {
		return HostsAndServicesGrid.getInstance(selenium, driver);
	}

    public void clickOnSummaryButton(){
        helper.clickWhenPossible(3, TimeUnit.SECONDS, By.id( WebConstants.ID.hostsSummaryButtonId ) );
    }

    public void clickOnLogsButton(){
        helper.clickWhenPossible(3, TimeUnit.SECONDS, By.id( WebConstants.ID.hostsLogsButtonId ) );
    }

    public String getSummaryText(){
        WebElement webElement = helper.waitForElement(TimeUnit.SECONDS, 3, By.className("gs-config-panel-header"));
        return webElement.getText();
    }

    public String getLogsTitle(){
        WebElement webElement = helper.waitForElement(TimeUnit.SECONDS, 3, By.className("gs-logs-service-name"));
        return webElement.getText();
    }
}