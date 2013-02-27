package com.gigaspaces.webuitf.datagrid.gateways.outbound;

import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.datagrid.utils.AbstractChannelsGridPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class GatewayChannelsBySourceContext extends AbstractChannelsGridPanel{
	
	public GatewayChannelsBySourceContext( AjaxUtils helper, WebElement element, String id ) {
		super( helper, element, id, true );
	}
}