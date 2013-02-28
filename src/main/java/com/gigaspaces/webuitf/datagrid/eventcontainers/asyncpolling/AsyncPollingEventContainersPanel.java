package com.gigaspaces.webuitf.datagrid.eventcontainers.asyncpolling;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.datagrid.eventcontainers.AbstractEventContainerPropertiesRow;
import com.gigaspaces.webuitf.datagrid.eventcontainers.AbstractEventContainersPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class AsyncPollingEventContainersPanel extends AbstractEventContainersPanel implements AsyncPollingEventContainerConstants{
	
	private List<WebElement> _concurrentConsumersEventsElements;
	private List<WebElement> _receiveTimeoutElements;

	public AsyncPollingEventContainersPanel( AjaxUtils helper ) {
		super( helper, ASYNC_POLLING_EVENT_CONTAINERS_GRID );
	}

	@Override
	protected AbstractEventContainerPropertiesRow createRow( int rowIndex, String id,
			boolean isTransactional, String status, long processedEvents,
			long failedEvents ) {

		int receiveTimeout = getReceiveTimeoutTagValue( rowIndex );
		int concurrentConsumers = getConcurrentConsumersTagValue( rowIndex );
		
		return new AsyncPollingEventContainerPropertiesRow( rowIndex, id, status, isTransactional, 
				failedEvents, processedEvents, concurrentConsumers, receiveTimeout );
	}

	@Override
	protected void searchEventSpecificColumnsElements() {
		_concurrentConsumersEventsElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC, 
				By.id( _gridId ), By.className( CONCURRENT_CONSUMERS_COLUMN_CLASS ), By.tagName( SPAN_TAG ) );
		_receiveTimeoutElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC, 
				By.id( _gridId ), By.className( RECEIVE_TIMEOUT_COLUMN_CLASS ), By.tagName( SPAN_TAG ) );		
	}
	
	private int getConcurrentConsumersTagValue( int index ){
		return getIntTagValue( _concurrentConsumersEventsElements, index );
	}
	
	private int getReceiveTimeoutTagValue( int index ){
		return getIntTagValue( _receiveTimeoutElements, index );
	}	
}