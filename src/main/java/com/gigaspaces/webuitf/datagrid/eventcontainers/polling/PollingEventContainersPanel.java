package com.gigaspaces.webuitf.datagrid.eventcontainers.polling;

import com.gigaspaces.webuitf.datagrid.eventcontainers.AbstractEventContainerPropertiesRow;
import com.gigaspaces.webuitf.datagrid.eventcontainers.AbstractEventContainersPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class PollingEventContainersPanel extends AbstractEventContainersPanel implements PollingEventContainerConstants{
	
	private List<WebElement> _maxConcurrentConsumersElements;		
	private List<WebElement> _receiveTimeoutElements;
	private List<WebElement> _receiveOperationsHandlerElements;
	private List<WebElement> _passArrayAsIsElements;	
	
	public PollingEventContainersPanel( AjaxUtils helper ) {
		super( helper, POLLING_EVENT_CONTAINERS_GRID );
	}

	@Override
	protected AbstractEventContainerPropertiesRow createRow(int rowIndex,
			String id, boolean isTransactional, String status,
			long processedEvents, long failedEvents) {

		int maxConcurrentConsumers = getMaxConcurrentConsumersTagValue( rowIndex ); 
		long receiveTimeout = getReceiveTimeoutTagValue( rowIndex );
		String receiveOperationsHandler = getReceiveOperationsHandler( rowIndex );
		boolean isPassArrayAsIs = getPassArrayAsIsTagValue( rowIndex );
		
		return new PollingEventContainerPropertiesRow( rowIndex, id, status, isTransactional, 
				failedEvents, processedEvents, maxConcurrentConsumers, receiveTimeout, 
				receiveOperationsHandler, isPassArrayAsIs );
	}

	private String getReceiveOperationsHandler(int rowIndex) {
		return getTagValue( _receiveOperationsHandlerElements, rowIndex );
	}

	@Override
	protected void searchEventSpecificColumnsElements() {

        _maxConcurrentConsumersElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
                By.cssSelector( "#" + _gridId + " ." + MAX_CONCURRENT_CONSUMERS_COLUMN_CLASS +
                        " " + SPAN_TAG ) );
        _receiveTimeoutElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
                By.cssSelector( "#" + _gridId + " ." + RECEIVE_TIMEOUT_COLUMN_CLASS +
                        " " + SPAN_TAG ) );
        _receiveOperationsHandlerElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
                By.cssSelector( "#" + _gridId + " ." + RECEIVE_OPERATION_HANDLER_COLUMN_CLASS +
                        " " + SPAN_TAG ) );
        _passArrayAsIsElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
                By.cssSelector( "#" + _gridId + " ." + IS_PASS_ARRAY_AS_IS_COLUMN_CLASS +
                        " " + DIV_TAG ) );
    }
	
	private int getMaxConcurrentConsumersTagValue( int rowIndex ){
		return getIntTagValue( _maxConcurrentConsumersElements, rowIndex );
	}
	
	private long getReceiveTimeoutTagValue( int rowIndex ){
		return getLongTagValue( _receiveTimeoutElements, rowIndex );
	}
	
	private boolean getPassArrayAsIsTagValue( int rowIndex ){
		return isCheckBoxSelected( _passArrayAsIsElements, rowIndex );
	}	
}