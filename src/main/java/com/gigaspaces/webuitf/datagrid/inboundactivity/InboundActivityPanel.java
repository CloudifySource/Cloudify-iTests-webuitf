package com.gigaspaces.webuitf.datagrid.inboundactivity;

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
 * @since 9.7.0
 * 
 */
public class InboundActivityPanel implements InboundActivityConstants{
	
	private final AjaxUtils _helper;
	private WebElement _gridElement;
	
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
			String receivedTrafficStr = getTagValue( receivedTrafficColumnElements, rowIndex );
			String generatedTrafficStr = getTagValue( generatedTrafficColumnElements, rowIndex );			
			String totalTrafficStr = getTagValue( totalTrafficColumnElements, rowIndex );

			_logger.info( ">rowIndex=" + rowIndex );
			_logger.info( "spaceInstanceName=" + spaceInstanceName );
			_logger.info( "pidStr=" + pidStr );
			_logger.info( "hostIp=" + hostIp );
			_logger.info( "receivedTrafficStr=" + receivedTrafficStr );
			_logger.info( "generatedTrafficStr=" + generatedTrafficStr );
			_logger.info( "totalTrafficStr=" + totalTrafficStr + "\n\n" );
			
			long pid = Long.parseLong( pidStr );
			double receivedTraffic = Double.parseDouble( receivedTrafficStr );
			double generatedTraffic = Double.parseDouble( generatedTrafficStr );			
			double totalTraffic = Double.parseDouble( totalTrafficStr );			
			
			InboundActivityRow inboundActivityRow = new InboundActivityRow( spaceInstanceName, 
					spaceMode, pid, hostIp, receivedTraffic, generatedTraffic, totalTraffic );
			list.add( inboundActivityRow );
		}

		return list.toArray( new InboundActivityRow[ list.size() ] );
	}	
	
	private String getTagValue( List<WebElement> elementsList, int index ){
		String value = null;
		if( elementsList != null && elementsList.size() > index ){
			try{
				WebElement webElement = elementsList.get( index );
				WebElement spanCellElement = webElement.findElement( By.tagName( "span" ) );
				value = spanCellElement.getText();
			}
			catch( Exception e ){
				_logger.log( Level.SEVERE, e.toString(), e );
			}
		}

		return value;		
	}
}