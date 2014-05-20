package com.gigaspaces.webuitf.datagrid.caches.locacaches;

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
 * @since 9.5.0
 * 
 */
public class LocalCachesPanel implements LocalCachesConstants{
	private final AjaxUtils _helper;
	private WebElement _gridElement;
	
	private Logger _logger = Logger.getLogger( this.getClass().getName() );

	public LocalCachesPanel( AjaxUtils helper ) {
		this._helper = helper;
		_gridElement = _helper.waitForElement( TimeUnit.SECONDS, 2,
				By.id( LOCAL_CACHES_GRID_ID ) );
	}
	
	public LocalCacheRow[] getContent() {

		List<LocalCacheRow> list = new ArrayList<LocalCacheRow>();

		List<WebElement> idColumnElements = 
				_gridElement.findElements( By.className( LOCAL_CACHES_ID_COLUMN_CLASS_NAME ) );
		List<WebElement> pidColumnElements =
				_gridElement.findElements( By.className( LOCAL_CACHES_PID_COLUMN_CLASS_NAME ) );
		List<WebElement> hostColumnElements =
				_gridElement.findElements( By.className( LOCAL_CACHES_HOST_COLUMN_CLASS_NAME ) );
		List<WebElement> versionColumnElements =
				_gridElement.findElements( By.className( LOCAL_CACHES_VERSION_COLUMN_CLASS_NAME ) );

		int rowsNum = idColumnElements.size();
		for( int rowIndex = 0; rowIndex < rowsNum; rowIndex++ ){
			String id = getTagValue( idColumnElements, rowIndex );
			String pidStr = getTagValue( pidColumnElements, rowIndex );
			String hostName = getTagValue( hostColumnElements, rowIndex );
			String version = getTagValue( versionColumnElements, rowIndex );
			long pid = -1;
			try{
				pid = Long.parseLong( pidStr );
			}
			catch( NumberFormatException e ){
				e.printStackTrace();
			}

			list.add( new LocalCacheRow( id, version, hostName, pid ) );					
		}

		return list.toArray( new LocalCacheRow[ list.size() ] );
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