package com.gigaspaces.webuitf.datagrid.gateways.outbound;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.datagrid.gateways.GatewaysConstants;
import com.gigaspaces.webuitf.datagrid.utils.ChannelsPropertiesRow;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class AggregatedGatewayChannelsPropertiesRow extends ChannelsPropertiesRow implements GatewaysConstants {
	
	private final AjaxUtils _helper;
	private Logger _logger = Logger.getLogger( this.getClass().getName() );
	
	public AggregatedGatewayChannelsPropertiesRow( AjaxUtils helper, String sourceName, String targetMemberName, 
			String targetHostName, String targetVersion, 
			long targetPid, String channelState, long sendPacketsPerSec, long redoLogRetainedSize,
			long sendBytesPerSec ){
		
		super( sourceName, targetMemberName, targetHostName, targetVersion, targetPid, channelState, 
				sendPacketsPerSec, redoLogRetainedSize, sendBytesPerSec );
		_helper = helper;
	}
	
	public GatewayChannelsBySourceContext next() {
		
		try{
			String rowId = GATEWAY_ROW_PREFIX + getTargetMemberName();
			//drill down
			_helper.clickWhenPossible( 5, TimeUnit.SECONDS, By.id( rowId ),
					By.className( NEXT_BUTTON_CLASS ), By.tagName( "a" ) );
			
			String gridId = OUTBOUND_CHANNELS_GATEWAY_GRID_ID;
			System.out.println( "Looking for Grid id [" + gridId + "]" );
			WebElement element = _helper.waitForElement( TimeUnit.SECONDS, 3, By.id( gridId ) );			
			return new GatewayChannelsBySourceContext( _helper, element, gridId );			
		}
		catch( Exception e ){
			_logger.log( Level.SEVERE, e.toString(), e );
		}
		
		return null;
	}	
}
