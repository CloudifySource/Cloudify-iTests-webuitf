package com.gigaspaces.webuitf.datagrid.caches.localviews;

import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.datagrid.utils.AbstractChannelsGridPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class LocalViewChannelsBySourceContext extends AbstractChannelsGridPanel{
	
	public LocalViewChannelsBySourceContext( AjaxUtils helper, WebElement element, String id ) {
		super( helper, element, id, true );
	}
}