package com.gigaspaces.webuitf.datagrid;

import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.BaseApplicationContextPanel;
import com.gigaspaces.webuitf.WebConstants;
import com.thoughtworks.selenium.Selenium;

public class DataGridTab extends BaseApplicationContextPanel {

	private WebDriver driver;
	
	public DataGridTab(Selenium selenium, WebDriver driver) {
		super(selenium, driver);
	}
	
	public DataGridSubPanel getSubPanel() {
		return new DataGridSubPanel(driver);
	}
	
	public ContextPager getContextPager() {
		return new ContextPager(driver);
	}
	
	public void selectApplication(String applicationName) {
		super.selectApplication(applicationName, WebConstants.ID.dataGridCombobox);
	}
	
}
