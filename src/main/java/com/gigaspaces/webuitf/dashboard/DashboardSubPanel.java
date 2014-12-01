package com.gigaspaces.webuitf.dashboard;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.dashboard.alerts.AlertsPanel;
import com.gigaspaces.webuitf.dashboard.events.DashboardEventsGrid;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardSubPanel {
	
	protected Selenium selenium;
	protected WebDriver driver;
	
	public DashboardSubPanel(Selenium selenium, WebDriver driver) {
		this.driver = driver;
		this.selenium = selenium;
	}

	public DashboardEventsGrid switchToEventsGrid() {
		WebElement healthButton = driver.findElement(By.id(WebConstants.ID.dashboardeventsGridToggle));
		healthButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new DashboardEventsGrid(driver);
	}


	public AlertsPanel switchToAlertsPanel() {
		WebElement healthButton = driver.findElement(By.id(WebConstants.ID.dashboardAlertsPanelToggle));
		healthButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AlertsPanel(selenium, driver);
	}

}
