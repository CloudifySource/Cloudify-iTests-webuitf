package com.gigaspaces.webuitf.datagrid;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.MainNavigation;
import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;

public class DataGridTab extends MainNavigation {

	private WebDriver driver;
	
	public DataGridTab(WebDriver driver) {
		this.driver = driver;
	}
	
	public DataGridSubPanel getSubPanel() {
		return new DataGridSubPanel(driver);
	}
	
	public ContextPager getContextPager() {
		return new ContextPager(driver);
	}
	
	public void selectApplication(final String applicationName) {
		
		RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {			
			public boolean getCondition() {
				WebElement arrowDown = driver.findElement(By.id(WebConstants.ID.dataGridCombobox)).findElement(By.className("icon"));
				arrowDown.click();
				List<WebElement> allApps = driver.findElement(By.id(WebConstants.ID.dataGridCombobox)).findElements(By.className("visible"));
				WebElement app = null;
				for (WebElement e : allApps) {
					if (e.getText().equals(applicationName)) app = e;
				}
				if ((app != null) && app.isDisplayed()) {
					app.click();
					return true;
				}
				else {
					return false;
				}
			}
		};

		AjaxUtils.repetitiveAssertTrue("Application is not present in the applications menu panel", condition,10000);
		
	}
	

}
