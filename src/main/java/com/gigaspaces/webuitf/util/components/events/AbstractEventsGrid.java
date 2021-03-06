package com.gigaspaces.webuitf.util.components.events;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class AbstractEventsGrid {

	private final WebDriver driver;
	protected final AjaxUtils helper;
	protected static final int WAIT_TIMEOUT_IN_SECONDS = 7;
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	public AbstractEventsGrid( WebDriver driver, AjaxUtils helper ) {
		this.driver = driver;
		this.helper = helper;
	}
	
	public abstract WebUIAdminEvent getEvent(int index);

	protected WebUIAdminEvent prepareEvent(WebElement eventElement) {
		WebElement eventTitle = eventElement.findElement(By.className(WebConstants.ClassNames.EventsGridEventTitle));
		String title = retrieveText(eventTitle);
		
		WebElement eventMessage = eventElement.findElement(By.className(WebConstants.ClassNames.EventsGridEventMessage));
		String message = retrieveText(eventMessage);
		
		WebElement eventDescription = eventElement.findElement(By.className(WebConstants.ClassNames.EventsGridEventDescription));
		String description = retrieveText(eventDescription);
		
		String idAttribute = eventElement.getAttribute("id");
		int firstIndex = idAttribute.indexOf("_")+1;
		int secondIndex = idAttribute.indexOf("_", firstIndex);
		String time = idAttribute.substring(firstIndex, secondIndex);
				
		WebElement eventStatus = eventElement.findElement(By.className(WebConstants.ClassNames.EventsGridEventStatus));
		String status = retrieveText(eventStatus);
		
		WebUIAdminEvent event = new WebUIAdminEvent(title, message, description, Long.valueOf(time), status);
		return event;
	}

	private String retrieveText(WebElement eventTitle) {
		return eventTitle.findElement(By.xpath(WebConstants.Xpath.pathToEventText)).getText();
	}
	
	public void waitFor(final int numberOfEvents, int timeoutInMillis) {
		
		RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {

			public boolean getCondition() {
				List<WebElement> events = driver.findElements(By.className(WebConstants.ClassNames.EventsGridEventTitle));
                logger.info("Within condition, current events size:" + events.size() + ", waiting for " + numberOfEvents);
				return events.size() >= numberOfEvents;
			}
		};
		AjaxUtils.repetitiveAssertTrue("could not find " + numberOfEvents + " event after " + timeoutInMillis, condition, timeoutInMillis);
		try {
			Thread.sleep(1000); // wait just a few secs more for stabilization
		} catch (InterruptedException e) {
		}
	}

	public List<WebUIAdminEvent> getVisibleEvents() {

        List<WebElement> visibleEventsElements =
				driver.findElements(By.className(WebConstants.ClassNames.EventsGridEventTitle));
		List<WebUIAdminEvent> visibleEvents = new ArrayList<WebUIAdminEvent>();
		
		int numOfEvents = visibleEventsElements.size() - 1; // subtracting the irrelevant "component-name" headline
		
		logger.info( ">>> visibleEventsElements size:" + visibleEventsElements.size() + 
					 ", numOfEvents:" + numOfEvents );
		
		for(int i = 1; i <= numOfEvents; i++){
			visibleEvents.add(getEvent(i));
		}

		return visibleEvents;
	}

	public String printAllEvents() {

		StringBuilder sb = new StringBuilder();
		List<WebUIAdminEvent> events = getVisibleEvents();
		
		logger.info("Here are all the visible events sorted by time");

		for (WebUIAdminEvent event : events) {
			
			logger.info(event.toString());
			sb.append(event.toString() + "\n");
		}
		
		return sb.toString();

	}
	
	protected void switchToTab( String buttonId ){
		
		helper.clickWhenPossible( WAIT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS, By.id( buttonId ) );
	}	
	
	public WebUIAdminEvent getChronologicalEvent(int index){
		int totalEvents = getVisibleEvents().size();
        logger.info( ">>> getChronologicalEvent(), totalEvents=" + totalEvents + ", index=" + index );
		return getEvent(totalEvents - index + 1);
	}
	
	public abstract void switchToLastMinuteEvents();
	
	public abstract void switchToLastHourEvents();
	
	public abstract void switchToLastDayeEvents();
	
	public abstract void switchToAllEvents();
	
	public abstract void enterTextInEventsFilter(String text) throws InterruptedException;
	
	public abstract void clearTextInEventsFilter();
	
	
}
