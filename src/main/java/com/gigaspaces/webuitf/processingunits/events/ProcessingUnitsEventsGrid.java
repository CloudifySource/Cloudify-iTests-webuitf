package com.gigaspaces.webuitf.processingunits.events;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.components.events.AbstractEventsGrid;
import com.gigaspaces.webuitf.util.components.events.WebUIAdminEvent;
import com.gigaspaces.webuitf.util.AjaxUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProcessingUnitsEventsGrid extends AbstractEventsGrid{

	public ProcessingUnitsEventsGrid(WebDriver driver, AjaxUtils helper) {
		super(driver, helper);
	}
	
	@Override
	public WebUIAdminEvent getEvent(int index) {
		
		WebElement eventElement = helper.waitForElement(By.xpath(WebConstants.Xpath.getPathToEventInTopologyEventsGrid(index)), WAIT_TIMEOUT_IN_SECONDS);		
		return prepareEvent(eventElement);
	}
	
	@Override
	public void switchToLastMinuteEvents(){
		
		switchToTab( WebConstants.ID.topologyLastMinuteEventsButton );
	}
	
	@Override
	public void switchToLastHourEvents(){
		
		switchToTab( WebConstants.ID.topologyLastHourEventsButton );
	}
	
	@Override
	public void switchToLastDayeEvents(){
		
		switchToTab( WebConstants.ID.topologyLastDayEventsButton );
	}
	
	@Override
	public void switchToAllEvents(){
		
		switchToTab( WebConstants.ID.topologyAllEventsButton );
	}
	
	@Override
	public void enterTextInEventsFilter(String text) throws InterruptedException{
		
		WebElement filterInputElement = helper.waitForElement(By.id(WebConstants.ID.topologyEventsFilterInput), WAIT_TIMEOUT_IN_SECONDS);
		filterInputElement.sendKeys(text);
		Thread.sleep(1000);
	}
	
	@Override
	public void clearTextInEventsFilter(){
		
		WebElement eventsFilter = helper.waitForElement(By.id(WebConstants.ID.topologyEventsFilter), WAIT_TIMEOUT_IN_SECONDS);
		WebElement eventsFilterClearButton = eventsFilter.findElement(By.className(WebConstants.ClassNames.EventsGridClearFilter));
		eventsFilterClearButton.click();
	}
}
