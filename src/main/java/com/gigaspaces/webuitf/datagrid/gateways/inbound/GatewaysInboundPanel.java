package com.gigaspaces.webuitf.datagrid.gateways.inbound;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class GatewaysInboundPanel {
	
	private final AjaxUtils _helper;
	
	private static final String INBOUND_GATEWAY_GRID_ID = "gs-inbound-gateways-grid";
	private static final String INBOUND_GATEWAY_GRID_ROW_ID_PREFIX = INBOUND_GATEWAY_GRID_ID + "_inbound-gateway-row";
	
	private Logger _logger = Logger.getLogger( this.getClass().getName() );
	
	public GatewaysInboundPanel( AjaxUtils helper ) {
		_helper = helper;
	}
	
	public GatewayInboundRow[] getGatewaysInbound() {

		List<GatewayInboundRow> list = new ArrayList<GatewayInboundRow>();

		Exception exception = null;
		int index = 0;
		while( exception == null ) {
			try {
//				WebElement rowElement = _helper.waitForElement( TimeUnit.SECONDS, 2,
//						By.id( INBOUND_GATEWAY_GRID_ROW_ID_PREFIX + index ) );
				WebElement spanCellElement = 
						_helper.waitForElement(TimeUnit.SECONDS, 3, 
								By.id( INBOUND_GATEWAY_GRID_ROW_ID_PREFIX + index ), 
								By.className( "x-grid3-col-id" ), 
								By.tagName( "span" ) );
				//WebElement cellElement = rowElement.findElement( By.className( "x-grid3-col-id" ) );
//				WebElement spanCellElement = cellElement.findElement( By.tagName( "span" ) );
				
				String name = spanCellElement.getText();

				list.add( new GatewayInboundRow( name ) );
				index++;
			}
			catch( Exception e ) {
				exception = e;
			}
		}

		return list.toArray( new GatewayInboundRow[ list.size() ] );
	}	
}