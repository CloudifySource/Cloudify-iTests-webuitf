package com.gigaspaces.webuitf.datagrid.network.notificationsactivity;

import com.gigaspaces.webuitf.util.AjaxUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author evgenyf
 * @since 9.7.0
 * 
 */
public class NotificationsActivityPanel implements NotificationsActivityConstants{
	
	private final AjaxUtils _helper;
	private WebElement _gridElement;
	private static final String QTIP_ATTRIBUTE = "qtip";
	
	private Logger _logger = Logger.getLogger( this.getClass().getName() );

	public NotificationsActivityPanel( AjaxUtils helper ) {
		this._helper = helper;
		_gridElement = 
			_helper.waitForElement( TimeUnit.SECONDS, 15, By.id( NOTIFICATION_ACTIVITY_GRID_ID ) );
	}
	
	public NotificationsActivityRow[] getContent() {

		List<NotificationsActivityRow> list = new ArrayList<NotificationsActivityRow>();

		List<WebElement> gscNameColumnElements = 
			_gridElement.findElements( By.className( NOTIFICATION_ACTIVITY_GSC_NAME_COLUMN_CLASS_NAME ) );
		List<WebElement> pidColumnElements =
			_gridElement.findElements( By.className( NOTIFICATION_ACTIVITY_PID_COLUMN_CLASS_NAME ) );
		List<WebElement> hostIpColumnElements = 
			_gridElement.findElements( By.className( NOTIFICATION_ACTIVITY_HOST_IP_COLUMN_CLASS_NAME ) );
		List<WebElement> receivedTrafficColumnElements =
			_gridElement.findElements( By.className( NOTIFICATION_ACTIVITY_RECEIVED_TRAFFIC_COLUMN_CLASS_NAME ) );
		List<WebElement> generatedTrafficColumnElements =
			_gridElement.findElements( By.className( NOTIFICATION_ACTIVITY_GENERATED_TRAFFIC_COLUMN_CLASS_NAME ) );
		List<WebElement> totalTrafficColumnElements = 
			_gridElement.findElements( By.className( NOTIFICATION_ACTIVITY_TOTAL_TRAFFIC_COLUMN_CLASS_NAME ) );
		List<WebElement> invocationCountColumnElements = 
				_gridElement.findElements( By.className( NOTIFICATION_ACTIVITY_INVOCATION_COUNT_COLUMN_CLASS_NAME ) );
		
		int rowsNum = gscNameColumnElements.size();
        _logger.info( ">>> rowsNum" + rowsNum );
        _logger.info( ">>> pidColumnElements.size=" + pidColumnElements.size() );
        _logger.info( ">>> hostIpColumnElements.size=" + hostIpColumnElements.size() );
        _logger.info( ">>> receivedTrafficColumnElements.size=" + receivedTrafficColumnElements.size() );
        _logger.info( ">>> generatedTrafficColumnElements.size=" + generatedTrafficColumnElements.size() );
        _logger.info( ">>> totalTrafficColumnElements.size=" + totalTrafficColumnElements.size() );
        _logger.info( ">>> invocationCountColumnElements.size=" + invocationCountColumnElements.size() );

		for( int rowIndex = 0; rowIndex < rowsNum; rowIndex++ ){
			
			String gscName = getTagValue( gscNameColumnElements, rowIndex );
			String pidStr = getTagValue( pidColumnElements, rowIndex );
			String hostIp = getTagValue( hostIpColumnElements, rowIndex );
			
			WebElement receivedTrafficElement = getSpanCellElement( receivedTrafficColumnElements, rowIndex );
			WebElement generatedTrafficElement = getSpanCellElement( generatedTrafficColumnElements, rowIndex );
			WebElement totalTrafficElement = getSpanCellElement( totalTrafficColumnElements, rowIndex );
			WebElement invocationCountElement = getSpanCellElement( invocationCountColumnElements, rowIndex );			
			
			String receivedTrafficMBStr = getTagValue( receivedTrafficElement );
			String generatedTrafficMBStr = getTagValue( generatedTrafficElement );			
			String totalTrafficMBStr = getTagValue( totalTrafficElement );
			String invocationCountStr = getTagValue( invocationCountElement );
			
			String receivedTrafficQtip = getQtipAttributeValue( receivedTrafficElement );
			String generatedTrafficQtip = getQtipAttributeValue( generatedTrafficElement );			
			String totalTrafficQtip = getQtipAttributeValue( totalTrafficElement );			

			_logger.info( ">rowIndex=" + rowIndex );
			_logger.info( "gscName=" + gscName );
			_logger.info( "pidStr=" + pidStr );
			_logger.info( "hostIp=" + hostIp );
			_logger.info( "invocationCount=" + invocationCountStr );
			_logger.info( "receivedTrafficQtip=" + receivedTrafficQtip );
			_logger.info( "generatedTrafficQtip=" + generatedTrafficQtip );
			_logger.info( "totalTrafficQtip=" + totalTrafficQtip );
			_logger.info( "receivedTrafficMBStr=" + receivedTrafficMBStr );
			_logger.info( "generatedTrafficMBStr=" + generatedTrafficMBStr );
			_logger.info( "totalTrafficMBStr=" + totalTrafficMBStr + "\n\n" );
			
			long pid = Long.parseLong( pidStr );
			double receivedTrafficMB = Double.parseDouble( receivedTrafficMBStr );
			double generatedTrafficMB = Double.parseDouble( generatedTrafficMBStr );			
			double totalTrafficMB = Double.parseDouble( totalTrafficMBStr );
			
			double receivedTraffic = extractValueFromQtip( receivedTrafficQtip );
			double generatedTraffic = extractValueFromQtip( generatedTrafficQtip );			
			double totalTraffic = extractValueFromQtip( totalTrafficQtip );
			
			long invocationCount = Long.parseLong( invocationCountStr );
			
			NotificationsActivityRow notificationActivityRow = new NotificationsActivityRow( gscName, 
					pid, hostIp, invocationCount, receivedTraffic, generatedTraffic, totalTraffic,
					receivedTrafficMB, generatedTrafficMB, totalTrafficMB );
			list.add( notificationActivityRow );
		}

		return list.toArray( new NotificationsActivityRow[ list.size() ] );
	}
	
	private static double extractValueFromQtip( String qtipStr ){
		return Double.parseDouble( qtipStr.substring( 0, qtipStr.indexOf( " " ) ).trim() );
	}
	
	private String getTagValue( List<WebElement> elementsList, int index ){
		
		return getTagValue( getSpanCellElement(elementsList, index) );
	}
	
	private static String getTagValue( WebElement element ){
		
		return element == null ? null : element.getText();
	}
	
	private static String getQtipAttributeValue( WebElement spanCellElement ){
		
		return getAttributeValue( spanCellElement, QTIP_ATTRIBUTE );
	}	
	
	private static String getAttributeValue( WebElement spanCellElement, String attrName ){
		
		return spanCellElement == null ? null : spanCellElement.getAttribute( attrName );
	}	
	
	private WebElement getSpanCellElement( List<WebElement> elementsList, int index ){

		return getElement( elementsList, index, "span" );		
	}
	
	private WebElement getDivCellElement( List<WebElement> elementsList, int index ){

		return getElement( elementsList, index, "div" );		
	}	
	
	private WebElement getElement( List<WebElement> elementsList, int index, String tagName ){
		WebElement spanCellElement = null;
		if( elementsList != null && elementsList.size() > index ){
			try{
				WebElement webElement = elementsList.get( index );
				spanCellElement = webElement.findElement( By.tagName( tagName ) );
			}
			catch( Exception e ){
				_logger.log( Level.SEVERE, e.toString(), e );
			}
		}

		return spanCellElement;		
	}
}