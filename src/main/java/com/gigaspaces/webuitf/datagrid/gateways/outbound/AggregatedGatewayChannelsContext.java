package com.gigaspaces.webuitf.datagrid.gateways.outbound;

import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.datagrid.gateways.GatewaysConstants;
import com.gigaspaces.webuitf.datagrid.utils.AbstractChannelsGridPanel;
import com.gigaspaces.webuitf.datagrid.utils.ChannelsPropertiesRow;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class AggregatedGatewayChannelsContext extends AbstractChannelsGridPanel implements GatewaysConstants{

	public AggregatedGatewayChannelsContext( AjaxUtils helper, WebElement element, String id ) {
		super( helper, element, id, false );
	}

	@Override
	protected ChannelsPropertiesRow createChannelRow( int index, String sourceName, 
			String targetHostName, String targetMemberName, long targetPid, String targetVersion, 
			String channelState, long sendBytesPerSec, long sendPacketsPerSec, 
			long redoLogRetainedSize, int templatesNum ) {

		AggregatedGatewayChannelsPropertiesRow channelRow = new AggregatedGatewayChannelsPropertiesRow( _helper, 
				sourceName, targetMemberName, targetHostName, targetVersion, targetPid, channelState, 
				sendPacketsPerSec, redoLogRetainedSize, sendBytesPerSec );
		return channelRow;
	}	
}