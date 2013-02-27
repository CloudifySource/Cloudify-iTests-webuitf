package com.gigaspaces.webuitf.datagrid.gateways.inbound;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class GatewayInboundRow {

	private final String _name;
	
	public GatewayInboundRow( String name ){
		_name = name;
	}
	
	public String getName(){
		return _name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}