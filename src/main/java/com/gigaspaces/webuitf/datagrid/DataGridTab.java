package com.gigaspaces.webuitf.datagrid;

import com.gigaspaces.webuitf.MainNavigation;
import com.gigaspaces.webuitf.WebConstants;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DataGridTab extends MainNavigation {
	
	public DataGridTab(Selenium selenium, WebDriver driver) {
		super(selenium, driver);
	}
	
	public DataGridSubPanel getSubPanel() {
		return new DataGridSubPanel( helper );
	}
	
	public ContextPager getContextPager() {
		return new ContextPager(driver);
	}
	
	public boolean isMaskedNoContext() {
		return isMaskedNoContext(WebConstants.ID.dataGridPanel);
	}
	
	public boolean isMaskedLoading() {
		String maskMessage = "Loading data grid information...";
		return isMaskedLoading(WebConstants.ID.dataGridPanel, maskMessage);
	}

    private boolean isMaskedLoading(final String tabItemId, String maskMessage) {

        return helper.isElementMaskedWithMessage(maskMessage, true,
                By.id(tabItemId),
                By.className(WebConstants.ClassNames.applicationContextPanel));
    }

    protected boolean isMaskedNoContext(final String tabItemId) {

        String maskMessage = "No processing units are deployed";

        return helper.isElementMaskedWithMessage(maskMessage, true,
                By.id(tabItemId),
                By.className(WebConstants.ClassNames.applicationContextPanel));
    }
}