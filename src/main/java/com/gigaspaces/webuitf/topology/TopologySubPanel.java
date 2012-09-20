package com.gigaspaces.webuitf.topology;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.dashboard.events.EventsPanel;
import com.gigaspaces.webuitf.topology.events.TopologyEventsGrid;
import com.gigaspaces.webuitf.topology.healthpanel.HealthPanel;
import com.gigaspaces.webuitf.topology.logicalpanel.LogicalPanel;
import com.gigaspaces.webuitf.topology.logspanel.LogsPanel;
import com.gigaspaces.webuitf.topology.physicalpanel.PhysicalPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;

public class TopologySubPanel {
	
	protected Selenium selenium;
	protected WebDriver driver;
	protected AjaxUtils helper;
	
	public TopologySubPanel() {}
	
	public TopologySubPanel(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.helper = new AjaxUtils(driver);
	}
	
	public LogsPanel switchToLogsPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.logsPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new LogsPanel(driver, selenium);
	}
	
	public HealthPanel switchToHealthPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.healthPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new HealthPanel(selenium, driver);
	}
	
	public PhysicalPanel switchToPhysicalPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.physicalPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new PhysicalPanel(selenium, driver);
	}
	
	public LogicalPanel switchToLogicalPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.logicalPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new LogicalPanel(selenium, driver);
	}
	
	public EventsPanel switchToEventsPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.topologyEventsPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new EventsPanel(driver);
	}
	
	public TopologyEventsGrid switchToEventsGrid() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.topologyEventsGridToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new TopologyEventsGrid(driver);
	}
}