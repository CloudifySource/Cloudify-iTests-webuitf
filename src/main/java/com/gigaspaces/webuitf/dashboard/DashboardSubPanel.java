package com.gigaspaces.webuitf.dashboard;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.dashboard.alerts.AlertsPanel;
import com.gigaspaces.webuitf.dashboard.events.EventsPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;

public class DashboardSubPanel {
	
	protected Selenium selenium;
	protected WebDriver driver;
	private AjaxUtils helper = new AjaxUtils();
	
	public DashboardSubPanel(Selenium selenium, WebDriver driver) {
		this.driver = driver;
		this.selenium = selenium;
		this.helper.setDriver(driver);
		this.helper.setSelenium(selenium);
	}

	public EventsPanel switchToEventsPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.dashboardeventsPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new EventsPanel(driver);
	}


	public AlertsPanel switchToAlertsPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.dashboardAlertsPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AlertsPanel(selenium, driver);
	}

}