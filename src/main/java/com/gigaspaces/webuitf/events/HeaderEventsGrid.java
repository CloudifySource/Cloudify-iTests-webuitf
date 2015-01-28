package com.gigaspaces.webuitf.events;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderEventsGrid extends AbstractEventsGrid {

	public HeaderEventsGrid(WebDriver driver, AjaxUtils helper) {
		super(driver, helper);
	}
	
	@Override
	public WebUIAdminEvent getEvent(int index) {
        logger.info( ">>> getEvent(), index=" + index );
		WebElement eventElement = helper.waitForElement(By.xpath(WebConstants.Xpath.getPathToHeaderEventInDashboardEventsGrid(index)), WAIT_TIMEOUT_IN_SECONDS);
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
	public void enterTextInEventsFilter(String text) throws InterruptedException {
		
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