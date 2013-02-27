package com.gigaspaces.webuitf.datagrid.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
abstract public class AbstractChannelsGridPanel {

	//		private Logger _logger = Logger.getLogger(AggregatedGatewayChannelsContext.class.getName());
	protected final AjaxUtils _helper;
	protected final WebElement _element;
	protected final String _id;
	private final boolean _retriveNonAggregatedProperties;
	
	private Logger _logger = Logger.getLogger( this.getClass().getName() );

	public AbstractChannelsGridPanel( AjaxUtils helper, WebElement element, String id, boolean retriveNonAggregatedProperties ) {
		this._helper = helper;
		this._element = element;
		this._id = id;
		this._retriveNonAggregatedProperties = retriveNonAggregatedProperties;
	}

	public ChannelsPropertiesRow[] getChannelsInfo() {

		List<ChannelsPropertiesRow> list = new ArrayList<ChannelsPropertiesRow>();

		Exception exception = null;
		try {
			//wait to panel will be initialized
			Thread.sleep( 3000 );

			List<WebElement> sourceNameElements = null;
			if( _retriveNonAggregatedProperties ){
				sourceNameElements = 
					_element.findElements( By.className( ChannelsPropertiesRow.SOURCE_NAME_COLUMN_CLASS ) );
			}			
			List<WebElement> targetHostNameElements = 
					_element.findElements( By.className( ChannelsPropertiesRow.TARGET_HOST_NAME_COLUMN_CLASS ) );
			List<WebElement> targetMemberNameElements = 
					_element.findElements( By.className( ChannelsPropertiesRow.TARGET_MEMBER_NAME_COLUMN_CLASS ) );
			List<WebElement> targetPidElements = 
					_element.findElements( By.className( ChannelsPropertiesRow.TARGET_PID_COLUMN_CLASS ) );
			List<WebElement> targetVersionElements = 
					_element.findElements( By.className( ChannelsPropertiesRow.TARGET_VERSION_COLUMN_CLASS ) );			
			List<WebElement> channelStateElements = 
					_element.findElements( By.className( ChannelsPropertiesRow.CHANNEL_STATE_ICON_COLUMN_CLASS ) );

			List<WebElement> sendBytesPerSecElements = 
					_element.findElements( By.className( ChannelsPropertiesRow.SEND_BYTES_PER_SEC_COLUMN_CLASS) );
			List<WebElement> sendPacketsPerSecElements = 
					_element.findElements( By.className( ChannelsPropertiesRow.SEND_PACKETS_PER_SEC_COLUMN_CLASS ) );
			List<WebElement> redoLogRetainedSizeElements = 
					_element.findElements( By.className( ChannelsPropertiesRow.REDOLOG_RETAINED_SIZE_COLUMN_CLASS ) );			
			
			int listSize = targetHostNameElements.size();
			_logger.info( "> listSize=" + listSize );

			for( int rowIndex = 0; rowIndex < listSize; rowIndex++ ) {

				_logger.info( "> index=" + rowIndex );

				String targetHostName = getTagValue( targetHostNameElements, rowIndex );
				String targetMemberName = getTagValue( targetMemberNameElements, rowIndex );
				String targetPidStr = getTagValue( targetPidElements, rowIndex );
				String targetVersion = getTagValue( targetVersionElements, rowIndex );
				String channelState = getImgQtipValue( channelStateElements, rowIndex );
				
				String sourceName = getTagValue( sourceNameElements, rowIndex );				

				String sendBytesPersSecStr = getTagValue( sendBytesPerSecElements, rowIndex, 
						WebConstants.Attributes.UNFORMATTED_VALUE_ATTRIBUTE );
				String sendPacketsPersSecStr = getTagValue( sendPacketsPerSecElements, rowIndex, 
						WebConstants.Attributes.UNFORMATTED_VALUE_ATTRIBUTE );
				String redoLogRetainedSizeStr = getTagValue( redoLogRetainedSizeElements, rowIndex, 
						WebConstants.Attributes.UNFORMATTED_VALUE_ATTRIBUTE );
				
				_logger.info( ">sendBytesPersSecStr=" + sendBytesPersSecStr );
				_logger.info( ">sendPacketsPersSecStr=" + sendPacketsPersSecStr );
				_logger.info( ">redoLogRetainedSizeStr=" + redoLogRetainedSizeStr );
				_logger.info( "> targetHostName=" + targetHostName );
				_logger.info( "> targetMemberName=" + targetMemberName );
				_logger.info( "> targetPidName=" + targetPidStr );
				_logger.info( "> targetVersion=" + targetVersion);
				_logger.info( "> sourceName=" + sourceName );
				_logger.info( "> channelStateName=" + channelState );

				long targetPid = -1;
				long sendBytesPerSec = -1;
				long sendPacketsPerSec= -1;
				long redoLogRetainedSize = -1;				

				if( targetPidStr.length() > 0 ){
					try{
						targetPid = Long.parseLong( targetPidStr );
					}
					catch( NumberFormatException e ){
						e.printStackTrace();
					}
				}
				if( sendBytesPersSecStr.length() > 0 ){
					try{
						sendBytesPerSec = Long.parseLong( sendBytesPersSecStr );
					}
					catch( NumberFormatException e ){
						e.printStackTrace();
					}
				}
				if( sendPacketsPersSecStr.length() > 0 ){
					try{					
						sendPacketsPerSec= Long.parseLong( sendPacketsPersSecStr );
					}
					catch( NumberFormatException e ){
						e.printStackTrace();
					}
				}
				if( sendPacketsPersSecStr.length() > 0 ){
					try{					
						redoLogRetainedSize = Long.parseLong( sendPacketsPersSecStr );
					}
					catch( NumberFormatException e ){
						e.printStackTrace();
					}
				}

				_logger.info( ">targetPid=" + targetPid );
				_logger.info( "> targetVersion=" + targetVersion);
				_logger.info( "> channelStateName=" + channelState );
				_logger.info( "> sendBytesPerSec=" + sendBytesPerSec );
				_logger.info( "> sendBytesPerSec=" + sendBytesPerSec );
				_logger.info( "> sendPacketsPerSec=" + sendPacketsPerSec );
				_logger.info( "> redoLogRetainedSize=" + redoLogRetainedSize );
				
				ChannelsPropertiesRow channelRow = createChannelRow( rowIndex, sourceName, 
						targetHostName, targetMemberName, targetPid, targetVersion, channelState, 
						sendBytesPerSec, sendPacketsPerSec, redoLogRetainedSize );

				list.add( channelRow );
			}
		}
		catch( Exception e ) {
			exception = e;
			_logger.log( Level.SEVERE, e.toString(), e );
		}


		return list.toArray( new ChannelsPropertiesRow[ list.size() ] );
	}	

	protected ChannelsPropertiesRow createChannelRow( int index, String sourceName, 
				String targetHostName, String targetMemberName, long targetPid, String targetVersion, 
				String channelState, long sendBytesPerSec, 
				long sendPacketsPerSec, long redoLogRetainedSize ) {
		
		ChannelsPropertiesRow channelRow = new ChannelsPropertiesRow( sourceName, targetMemberName, 
				targetHostName, targetVersion, targetPid, channelState, sendPacketsPerSec,
				redoLogRetainedSize, sendBytesPerSec );
		return channelRow;
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

	private String getTagValue( List<WebElement> elementsList, int index, String attrName ){
		String value = null;
		try{
			WebElement webElement = elementsList.get( index );
			WebElement spanCellElement = webElement.findElement( By.tagName( "span" ) );
			value = spanCellElement.getAttribute( attrName );
		}
		catch( Exception e ){
			_logger.log( Level.SEVERE, e.toString(), e );
		}

		return value;		
	}	

	private String getImgQtipValue( List<WebElement> elementsList, int index ){
		String value = null;
		try{
			WebElement webElement = elementsList.get( index );
			WebElement spanCellElement = webElement.findElement( By.tagName( "img" ) );
			value = spanCellElement.getAttribute( "qtip" );
		}
		catch( Exception e ){
			_logger.log( Level.SEVERE, e.toString(), e );
		}

		return value;		
	}	
}	
