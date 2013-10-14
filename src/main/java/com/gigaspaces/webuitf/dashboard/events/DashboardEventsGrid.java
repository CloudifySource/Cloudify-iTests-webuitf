package com.gigaspaces.webuitf.dashboard.events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.events.AbstractEventsGrid;
import com.gigaspaces.webuitf.events.WebUIAdminEvent;

public class DashboardEventsGrid extends AbstractEventsGrid{

	public DashboardEventsGrid(WebDriver driver) {
		super(driver);
	}
	
	@Override
	public WebUIAdminEvent getEvent(int index) {
		
		WebElement eventElement = helper.waitForElement(By.xpath(WebConstants.Xpath.getPathToEventInDashboardEventsGrid(index)), WAIT_TIMEOUT_IN_SECONDS);		
		return prepareEvent(eventElement);
	}
	
	@Override
	public void switchToLastMinuteEvents(){
		
		switchToTab( WebConstants.ID.dashboardLastMinuteEventsButton );
	}
	
	@Override
	public void switchToLastHourEvents(){
		
		switchToTab( WebConstants.ID.dashboardLastHourEventsButton );		
	}
	
	@Override
	public void switchToLastDayeEvents(){
		
		switchToTab( WebConstants.ID.dashboardLastDayEventsButton );
	}
	
	@Override
	public void switchToAllEvents(){
		
		switchToTab( WebConstants.ID.dashboardAllEventsButton );
	}
	
	@Override
	public void enterTextInEventsFilter(String text) throws InterruptedException{
		
		WebElement filterInputElement = helper.waitForElement(By.id(WebConstants.ID.dashboardEventsFilterInput), WAIT_TIMEOUT_IN_SECONDS);
		filterInputElement.sendKeys(text);
		Thread.sleep(1000);
	}

	@Override
	public void clearTextInEventsFilter(){
		
		WebElement eventsFilter = helper.waitForElement(By.id(WebConstants.ID.dashboardEventsFilter), WAIT_TIMEOUT_IN_SECONDS);
		WebElement eventsFilterClearButton = eventsFilter.findElement(By.className(WebConstants.ClassNames.EventsGridClearFilter));
		eventsFilterClearButton.click();
	}
}