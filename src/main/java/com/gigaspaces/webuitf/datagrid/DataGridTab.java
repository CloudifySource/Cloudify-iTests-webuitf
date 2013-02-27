package com.gigaspaces.webuitf.datagrid;

import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.BaseApplicationContextPanel;
import com.gigaspaces.webuitf.WebConstants;
import com.thoughtworks.selenium.Selenium;

public class DataGridTab extends BaseApplicationContextPanel {
	
	public DataGridTab(Selenium selenium, WebDriver driver) {
		super(selenium, driver);
	}
	
	public DataGridSubPanel getSubPanel() {
		return new DataGridSubPanel( helper );
	}
	
	public ContextPager getContextPager() {
		return new ContextPager(driver);
	}
	
	public void selectApplication(String applicationName) {
		super.selectApplication(applicationName, WebConstants.ID.dataGridCombobox);
	}
	
	public String getSelectedApplication() {
		return super.getSelectedApplication(WebConstants.ID.dataGridCombobox);
	}

	public boolean isMaskedNoContext() {
		return super.isMaskedNoContext(WebConstants.ID.dataGridPanel);
	}
	
	public boolean isMaskedLoading() {
		String maskMessage = "Loading data grid information...";
		return super.isMaskedLoading(WebConstants.ID.dataGridPanel, maskMessage);
	}
}