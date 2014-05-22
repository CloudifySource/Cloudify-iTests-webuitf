package com.gigaspaces.webuitf.datagrid.network.inboundactivity;

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
public class InboundActivityPanel implements InboundActivityConstants{
	
	private final AjaxUtils _helper;
	private WebElement _gridElement;
	private static final String QTIP_ATTRIBUTE = "qtip";
	
	private Logger _logger = Logger.getLogger( this.getClass().getName() );

	public InboundActivityPanel( AjaxUtils helper ) {
		this._helper = helper;
		_gridElement = _helper.waitForElement( TimeUnit.SECONDS, 15, By.id( INBOUND_ACTIVITY_GRID_ID ) );
	}
	
	public InboundActivityRow[] getContent() {

		List<InboundActivityRow> list = new ArrayList<InboundActivityRow>();

		List<WebElement> spaceModeColumnElements = 
			_gridElement.findElements( By.className( INBOUN_ACTIVITY_SPACE_MODE_COLUMN_CLASS_NAME ) );
		List<WebElement> spaceInstanceNameColumnElements =
			_gridElement.findElements( By.className( INBOUN_ACTIVITY_SPACE_INSTANCE_COLUMN_CLASS_NAME ) );
		List<WebElement> pidColumnElements =
			_gridElement.findElements( By.className( INBOUN_ACTIVITY_PID_COLUMN_CLASS_NAME ) );
		List<WebElement> hostIpColumnElements = 
			_gridElement.findElements( By.className( INBOUN_ACTIVITY_HOST_IP_COLUMN_CLASS_NAME ) );
		List<WebElement> receivedTrafficColumnElements =
			_gridElement.findElements( By.className( INBOUN_ACTIVITY_RECEIVED_TRAFFIC_COLUMN_CLASS_NAME ) );
		List<WebElement> generatedTrafficColumnElements =
			_gridElement.findElements( By.className( INBOUN_ACTIVITY_GENERATED_TRAFFIC_COLUMN_CLASS_NAME ) );
		List<WebElement> totalTrafficColumnElements = 
			_gridElement.findElements( By.className( INBOUN_ACTIVITY_TOTAL_TRAFFIC_COLUMN_CLASS_NAME ) );

		int rowsNum = spaceModeColumnElements.size();
		for( int rowIndex = 0; rowIndex < rowsNum; rowIndex++ ){
			String spaceMode = "TBD";
//			String spaceMode = getTagValue( spaceModeColumnElements, rowIndex );
			String spaceInstanceName = getTagValue( spaceInstanceNameColumnElements, rowIndex );
			String pidStr = getTagValue( pidColumnElements, rowIndex );
			String hostIp = getTagValue( hostIpColumnElements, rowIndex );
			
			WebElement receivedTrafficElement = getSpanCellElement( receivedTrafficColumnElements, rowIndex );
			WebElement generatedTrafficElement = getSpanCellElement( generatedTrafficColumnElements, rowIndex );
			WebElement totalTrafficElement = getSpanCellElement( totalTrafficColumnElements, rowIndex );
			
			String receivedTrafficMBStr = getTagValue( receivedTrafficElement );
			String generatedTrafficMBStr = getTagValue( generatedTrafficElement );			
			String totalTrafficMBStr = getTagValue( totalTrafficElement );
			
			String receivedTrafficQtip = getQtipAttributeValue( receivedTrafficElement );
			String generatedTrafficQtip = getQtipAttributeValue( generatedTrafficElement );			
			String totalTrafficQtip = getQtipAttributeValue( totalTrafficElement );			

			_logger.info( ">rowIndex=" + rowIndex );
			_logger.info( "spaceInstanceName=" + spaceInstanceName );
			_logger.info( "pidStr=" + pidStr );
			_logger.info( "hostIp=" + hostIp );
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
			
			InboundActivityRow inboundActivityRow = new InboundActivityRow( spaceInstanceName, 
					spaceMode, pid, hostIp, receivedTraffic, generatedTraffic, totalTraffic,
					receivedTrafficMB, generatedTrafficMB, totalTrafficMB );
			list.add( inboundActivityRow );
		}

		return list.toArray( new InboundActivityRow[ list.size() ] );
	}
	
	private static double extractValueFromQtip( String qtipStr ){
		return Double.parseDouble( qtipStr.substring( 0, qtipStr.indexOf( " " ) ).trim() );
	}
	
	private String getTagValue( List<WebElement> elementsList, int index ){
		
		return getTagValue( getSpanCellElement(elementsList, index) );
	}
	
	private static String getTagValue( WebElement spanCellElement ){
		
		return spanCellElement == null ? null : spanCellElement.getText();
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