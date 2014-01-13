package com.gigaspaces.webuitf.datagrid.eventcontainers.notify;

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
public class NotifyEventContainersPanel extends AbstractEventContainersPanel implements NotifyEventContainerConstants{
	
	private List<WebElement> _notifyOperationsElements;
	private List<WebElement> _performTakeOnNotifyElements;
	private List<WebElement> _isFifoElements;

	private List<WebElement> _commTypeElements;
	private List<WebElement> _batchSizeElements;
	private List<WebElement> _batchTimeElements;
	private List<WebElement> _isPassArrayAsIsElements;
	
	
	public NotifyEventContainersPanel( AjaxUtils helper ) {
		super( helper, NOTIFY_EVENT_CONTAINERS_GRID );
	}

	@Override
	protected AbstractEventContainerPropertiesRow createRow( int rowIndex, String id, 
			boolean isTransactional, String status, long processedEvents, long failedEvents ) {

		String notifyOperations = getNotifyOperationsTagValue( rowIndex );
		boolean isPerformTakeOnNotify = getPerformTakeOnNotifyTagValue( rowIndex );
		boolean isFifo = getFifoTagValue( rowIndex );
		String commType = getCommTypeTagValue( rowIndex ); 
		int batchTime = getBatchTimeTagValue( rowIndex );
		int batchSize = getBatchSizeTagValue( rowIndex );;
		boolean isPassArrayAsIs = getPassArrayAsIsTagValue( rowIndex );
		
		return new NotifyEventContainerPropertiesRow( rowIndex, id, status, isTransactional, 
				failedEvents, processedEvents, notifyOperations, isPerformTakeOnNotify, isFifo, 
				commType, batchTime, batchSize, isPassArrayAsIs );
	}

	@Override
	protected void searchEventSpecificColumnsElements() {

		_notifyOperationsElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
				By.cssSelector( "#" + _gridId + " ." + NOTIFY_OPERATIONS_COLUMN_CLASS + " " + SPAN_TAG ) );
		_performTakeOnNotifyElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
				By.cssSelector( "#" + _gridId + " ." + IS_TAKE_ON_NOTIFY_COLUMN_CLASS + " " + DIV_TAG ) );
		_isFifoElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
				By.cssSelector( "#" + _gridId + " ." + IS_FIFO_COLUMN_CLASS + " " + DIV_TAG ) );

		_batchSizeElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
				By.cssSelector( "#" + _gridId + " ." + BATCH_SIZE_COLUMN_CLASS + " " + SPAN_TAG ) );
		_batchTimeElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
				By.cssSelector( "#" + _gridId + " ." + BATCH_TIME_HANDLER_COLUMN_CLASS + " " + SPAN_TAG ) );

		_commTypeElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
				By.cssSelector( "#" + _gridId + " ." + COMM_TYPE_COLUMN_CLASS + " " + SPAN_TAG ) );
		_isPassArrayAsIsElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC,
				By.cssSelector( "#" + _gridId + " ." + IS_PASS_ARRAY_AS_IS_COLUMN_CLASS + " " + DIV_TAG ) );
	}
	
	private String getNotifyOperationsTagValue( int rowIndex ) {

		return getTagValue( _notifyOperationsElements, rowIndex );
	}
	
	private String getCommTypeTagValue( int rowIndex ) {

		return getTagValue( _commTypeElements, rowIndex );
	}	
	
	private boolean getPerformTakeOnNotifyTagValue( int rowIndex ) {

		return isCheckBoxSelected( _performTakeOnNotifyElements, rowIndex );
	}
	
	private boolean getFifoTagValue( int rowIndex ) {

		return isCheckBoxSelected( _isFifoElements, rowIndex );
	}
	
	private boolean getPassArrayAsIsTagValue( int rowIndex ) {

		return isCheckBoxSelected( _isPassArrayAsIsElements, rowIndex );
	}
	
	private int getBatchSizeTagValue( int rowIndex ) {
	
		return getIntTagValue( _batchSizeElements, rowIndex );
	}

	private int getBatchTimeTagValue( int rowIndex ) {
	
		return getIntTagValue( _batchTimeElements, rowIndex );
	}	
}