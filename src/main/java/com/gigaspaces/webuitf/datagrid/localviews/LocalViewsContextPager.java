package com.gigaspaces.webuitf.datagrid.localviews;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class LocalViewsContextPager implements LocalViewsConstants {
	
	private Logger _logger = Logger.getLogger(LocalViewsContextPager.class.getName());
	
	private final AjaxUtils _helper;
	
	public LocalViewsContextPager(AjaxUtils helper) {
		this._helper = helper;
	}
	
	public AggregatedLocalViewChannelsContext getAggregatedLocalViewChannelsContext() {
		String id = AGGREAGTED_LOCAL_VIEWS_GRID_ID;
		AggregatedLocalViewChannelsContext context;
		try {
			WebElement element = _helper.waitForElement(TimeUnit.SECONDS, 3, By.id(id));
			context = new AggregatedLocalViewChannelsContext( _helper, element, id );
		}
		catch (TimeoutException e) {
			_logger.info("caugh a timeout exception while waiting for element. returning null");
			return null;
		}
		
		return context;
	}	
}