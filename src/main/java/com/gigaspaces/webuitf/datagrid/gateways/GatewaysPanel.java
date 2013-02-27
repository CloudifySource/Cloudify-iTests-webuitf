package com.gigaspaces.webuitf.datagrid.gateways;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.datagrid.gateways.graphical.GatewaysGraphicalPanel;
import com.gigaspaces.webuitf.datagrid.gateways.inbound.GatewaysInboundPanel;
import com.gigaspaces.webuitf.datagrid.gateways.outbound.GatewaysOutboundContextPager;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class GatewaysPanel {
	
	private Logger _logger = Logger.getLogger( this.getClass().getName() );
	
//	private final WebDriver driver;
	private final AjaxUtils _helper;

	public GatewaysPanel( AjaxUtils helper ) {
//		this.driver = driver;
		this._helper = helper;
	}
	
	public GatewaysOutboundContextPager switchToOutboundPanel() {
		clickWhenPossible( WebConstants.ID.gatewaysOutboundTogglerButton );
		return new GatewaysOutboundContextPager( _helper );
	}

	public GatewaysInboundPanel switchToInboundPanel() {
		clickWhenPossible( WebConstants.ID.gatewaysInboundTogglerButton );
		return new GatewaysInboundPanel( _helper );
	}

	public GatewaysGraphicalPanel switchToGraphicalPanel() {
		clickWhenPossible( WebConstants.ID.gatewaysGraphicalTogglerButton);
		return new GatewaysGraphicalPanel( _helper );		
	}
	
	private void clickWhenPossible( String id ){
		_helper.clickWhenPossible( 5, TimeUnit.SECONDS, By.id( id ) );
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			_logger.log( Level.SEVERE, e.toString(), e );
		}		
	}	
}