package com.gigaspaces.webuitf.datagrid.gateways.outbound;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.datagrid.gateways.GatewaysConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class GatewaysOutboundContextPager implements GatewaysConstants {
	
	private Logger _logger = Logger.getLogger( this.getClass().getName() );
	
	private final AjaxUtils _helper;
	
	public GatewaysOutboundContextPager(AjaxUtils helper) {
		this._helper = helper;
	}
	
	public void prev() {
		_helper.getDriver().findElement(By.className("gwt-Anchor")).click();
	}

	public AggregatedGatewayChannelsContext getAggregatedGatewayChannelsContext() {
		String id = OUTBOUND_AGGREAGTED_GATEWAY_GRID_ID;
		AggregatedGatewayChannelsContext context;
		try {
			WebElement element = _helper.waitForElement(TimeUnit.SECONDS, 3, By.id(id));
			context = new AggregatedGatewayChannelsContext( _helper, element, id );
		}
		catch (TimeoutException e) {
			_logger.info("caugh a timeout exception while waiting for element. returning null");
			return null;
		}
		
		return context;
	}	
}