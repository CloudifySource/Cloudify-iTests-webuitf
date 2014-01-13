package com.gigaspaces.webuitf.datagrid.eventcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
abstract public class AbstractEventContainersPanel implements EventContainersConstants{
	
	protected final AjaxUtils _helper;
	protected final String _gridId;
	
	protected final String SPAN_TAG = "span"; 
	protected final String DIV_TAG = "div";
	
	protected final int WAITING_TIME_SEC = 2;
	
//	private static final String INBOUND_GATEWAY_GRID_ID = "gs-inbound-gateways-grid";
//	private static final String INBOUND_GATEWAY_GRID_ROW_ID_PREFIX = INBOUND_GATEWAY_GRID_ID + "_inbound-gateway-row";
	
	protected Logger _logger = Logger.getLogger( this.getClass().getName() );
	
	private List<WebElement> _idElements;
	private List<WebElement> _transactionalElements;
	private List<WebElement> _statusElements;
	private List<WebElement> _failedEventsElements;
	private List<WebElement> _processedEventsElements;
	
	public AbstractEventContainersPanel( AjaxUtils helper, String gridId ) {
		_helper = helper;
		_gridId = gridId;
	}
	
	public AbstractEventContainerPropertiesRow[] getEventContainersInfo( boolean waitForInitialization ) {

		List<AbstractEventContainerPropertiesRow> list = new ArrayList<AbstractEventContainerPropertiesRow>();

		Exception exception = null;
		try {
			if( waitForInitialization ){
				//wait to panel will be initialized
				Thread.sleep( 3000 );
			}

			//!!!!!!!!important to invoke it before loop
			searchCommonColumnsElements();
			searchEventSpecificColumnsElements();
			
			int listSize = _idElements.size();
			_logger.info( "> listSize=" + listSize );

			for( int rowIndex = 0; rowIndex < listSize; rowIndex++ ) {

				_logger.info( "> index=" + rowIndex );

				String id = getIdTagValue( rowIndex );
				boolean isTransactional = getTransactionalTagValue( rowIndex );
				String status = getStatusTagValue( rowIndex );
				long failedEvents = getFailedEventsTagValue( rowIndex );
				long processedEvents = getProcessedEventsTagValue( rowIndex );				
				
				_logger.info( "> id=" + id );
				_logger.info( "> isTransactional=" + isTransactional );
				_logger.info( "> status=" + status );
				_logger.info( "> failedEvents=" + failedEvents );
				_logger.info( "> processedEvents=" + processedEvents );

				AbstractEventContainerPropertiesRow eventContainerRow = 
						createRow( rowIndex, id, isTransactional, status, processedEvents, failedEvents );

				list.add( eventContainerRow );
			}
		}
		catch( Exception e ) {
			exception = e;
			_logger.log( Level.SEVERE, e.toString(), e );
		}


		return list.toArray( new AbstractEventContainerPropertiesRow[ list.size() ] );
	}	
	
	private long getProcessedEventsTagValue(int rowIndex) {
		return getLongTagValue( _processedEventsElements, rowIndex );
	}

	private long getFailedEventsTagValue(int rowIndex) {
		return getLongTagValue( _failedEventsElements, rowIndex );
	}

	private String getStatusTagValue( int rowIndex ) {
		return getTagValue( _statusElements, rowIndex );
	}

	private boolean getTransactionalTagValue( int rowIndex ) {
		return isCheckBoxSelected( _transactionalElements, rowIndex );
	}

	private String getIdTagValue(int rowIndex) {
		return getTagValue( _idElements, rowIndex );
	}

	protected void searchCommonColumnsElements() {

		_idElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC, By.cssSelector( "#" + _gridId + 
				" ." + ID_COLUMN_CLASS + " " + SPAN_TAG ) );
		_transactionalElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC, By.cssSelector( "#" + _gridId + 
				" ." + TRANSACTIONAL_COLUMN_CLASS + " " + DIV_TAG ) );					
		_statusElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC, By.cssSelector( "#" + _gridId + 
				" ." + STATUS_COLUMN_CLASS + " " + SPAN_TAG ) );					
		_failedEventsElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC, By.cssSelector( "#" + _gridId + 
				" ." + FAILED_EVENTS_COLUMN_CLASS + " " + SPAN_TAG ) );					
		_processedEventsElements = _helper.waitForElements( TimeUnit.SECONDS, WAITING_TIME_SEC, By.cssSelector( "#" + _gridId + 
				" ." + PROCESSED_EVENTS_COLUMN_CLASS + " " + SPAN_TAG ) );		
	}

	abstract protected AbstractEventContainerPropertiesRow createRow( int rowIndex, String id,
			boolean isTransactional, String status, long processedEvents, long failedEvents );
	
	abstract protected void searchEventSpecificColumnsElements();

	protected long getLongTagValue( List<WebElement> elements, int rowIndex ) {
		
		String longStr = getTagValue( elements, rowIndex );
		long val = -1;

		if( longStr.length() > 0 ){
			try{
				val = Long.parseLong( longStr );
			}
			catch( NumberFormatException e ){
				_logger.log( Level.SEVERE , e.toString(), e );
			}
		}		
		
		return val;
	}	
	
	protected String getTagValue( List<WebElement> elementsList, int index ){
		String value = null;
		if( elementsList != null && elementsList.size() > index ){
			WebElement webElement = elementsList.get( index );
			value = webElement.getText();
		}

		return value;		
	}		
	
	protected boolean isCheckBoxSelected( List<WebElement> elementsList, int index ){
		boolean isSelected = false;
//		_logger.info( "--elementsList SIZE:" + elementsList.size() );
		if( elementsList != null && elementsList.size() > index ){
			WebElement webElement = elementsList.get( index );
			String classes = webElement.getAttribute( "class" );
//			_logger.info( "check box div classes:[" + classes + "]" );
			isSelected = classes.contains( "x-grid3-check-col-on" );
		}

		return isSelected;		
	}
	
	protected int getIntTagValue( List<WebElement> elements, int rowIndex ) {
		
		String intStr = getTagValue( elements, rowIndex );
		int val = -1;

		if( intStr.length() > 0 ){
			try{
				val = Integer.parseInt( intStr );
			}
			catch( NumberFormatException e ){
				_logger.log( Level.SEVERE , e.toString(), e );
			}
		}		
		
		return val;
	}	
}