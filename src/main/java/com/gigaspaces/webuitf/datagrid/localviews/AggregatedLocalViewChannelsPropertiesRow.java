package com.gigaspaces.webuitf.datagrid.localviews;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.datagrid.utils.ChannelsPropertiesRow;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class AggregatedLocalViewChannelsPropertiesRow extends ChannelsPropertiesRow implements LocalViewsConstants {
	
	private final AjaxUtils _helper;
	private Logger _logger = Logger.getLogger( this.getClass().getName() );
	
	public AggregatedLocalViewChannelsPropertiesRow( AjaxUtils helper, String sourceName, String targetMemberName, 
			String targetHostName, String targetVersion, 
			long targetPid, String channelState, long sendPacketsPerSec, long redoLogRetainedSize,
			long sendBytesPerSec, int templatesNum ){
		
		super( sourceName, targetMemberName, targetHostName, targetVersion, targetPid, channelState, 
				sendPacketsPerSec, redoLogRetainedSize, sendBytesPerSec, templatesNum );
		_helper = helper;
	}
	
	public LocalViewChannelsBySourceContext next() {
		
		try{
			String rowId = LOCAL_VIEW_ROW_PREFIX + getTargetMemberName();
			//drill down
			_helper.clickWhenPossible( 5, TimeUnit.SECONDS, By.id( rowId ),
					By.className( NEXT_BUTTON_CLASS ), By.tagName( "a" ) );
			
			String gridId = "gs-slider-grid-LOCAL_VIEWS_CHANNELS_LIST";//LOCAL_VIEW_CHANNELS_GRID_ID;
			_logger.info( "Looking for Grid id [" + gridId + "]" );
			WebElement element = _helper.waitForElement( TimeUnit.SECONDS, 10, By.id( gridId ) );			
			return new LocalViewChannelsBySourceContext( _helper, element, gridId );			
		}
		catch( Exception e ){
			e.printStackTrace();
		}
		
		return null;
	}	
}
