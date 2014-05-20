package com.gigaspaces.webuitf.datagrid.caches.localviews;

import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.datagrid.utils.AbstractChannelsGridPanel;
import com.gigaspaces.webuitf.datagrid.utils.ChannelsPropertiesRow;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class AggregatedLocalViewChannelsContext extends AbstractChannelsGridPanel implements LocalViewsConstants{

	public AggregatedLocalViewChannelsContext( AjaxUtils helper, WebElement element, String id ) {
		super( helper, element, id, false );
	}

	@Override
	protected ChannelsPropertiesRow createChannelRow( int index, String sourceName, 
			String targetHostName, String targetMemberName, long targetPid, String targetVersion, 
			String channelState, long sendBytesPerSec, long sendPacketsPerSec, 
			long redoLogRetainedSize, int templatesNum ) {

		AggregatedLocalViewChannelsPropertiesRow channelRow = 
				new AggregatedLocalViewChannelsPropertiesRow( _helper, sourceName, targetMemberName, 
						targetHostName, targetVersion, targetPid, channelState, sendPacketsPerSec, 
						redoLogRetainedSize, sendBytesPerSec, templatesNum );
		return channelRow;
	}	
}