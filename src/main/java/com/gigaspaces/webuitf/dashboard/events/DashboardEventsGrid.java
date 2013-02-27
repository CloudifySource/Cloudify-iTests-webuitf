package com.gigaspaces.webuitf.dashboard.events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.events.AbstractEventsGrid;
import com.gigaspaces.webuitf.events.WebUIAdminEvent;

public class DashboardEventsGrid extends AbstractEventsGrid{

	private static final int WAIT_TIMEOUT_IN_SECONDS = 1;
	
	public DashboardEventsGrid(WebDriver driver) {
		super(driver);
	}
	
	public WebUIAdminEvent getEvent(int index) {
		
		WebElement eventElement = helper.waitForElement(By.xpath(WebConstants.Xpath.getPathToEventInDashboardEventsGrid(index)), WAIT_TIMEOUT_IN_SECONDS);		
		return prepareEvent(eventElement);
	}
	
	public void switchToLastMinuteEvents(){
		
		WebElement element = helper.waitForElement(By.id(WebConstants.ID.dashboardLastMinuteEventsButton), WAIT_TIMEOUT_IN_SECONDS);
		element.click();
	}
	
	public void switchToLastHourEvents(){
		
		WebElement element = helper.waitForElement(By.id(WebConstants.ID.dashboardLastHourEventsButton), WAIT_TIMEOUT_IN_SECONDS);
		element.click();
	}
	
	public void switchToLastDayeEvents(){
		
		WebElement element = helper.waitForElement(By.id(WebConstants.ID.dashboardLastDayEventsButton), WAIT_TIMEOUT_IN_SECONDS);
		element.click();
	}
	
	public void switchToAllEvents(){
		
		WebElement element = helper.waitForElement(By.id(WebConstants.ID.dashboardAllEventsButton), WAIT_TIMEOUT_IN_SECONDS);
		element.click();
	}
	
	public void enterTextInEventsFilter(String text) throws InterruptedException{
		
		WebElement filterInputElement = helper.waitForElement(By.id(WebConstants.ID.dashboardEventsFilterInput), WAIT_TIMEOUT_IN_SECONDS);
		filterInputElement.sendKeys(text);
		Thread.sleep(1000);
	}
	
	public void clearTextInEventsFilter(){
		
		WebElement eventsFilter = helper.waitForElement(By.id(WebConstants.ID.dashboardEventsFilter), WAIT_TIMEOUT_IN_SECONDS);
		WebElement eventsFilterClearButton = eventsFilter.findElement(By.className(WebConstants.ClassNames.EventsGridClearFilter));
		eventsFilterClearButton.click();
	}
	
	
}
