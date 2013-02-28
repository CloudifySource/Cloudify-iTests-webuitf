package com.gigaspaces.webuitf.datagrid.eventcontainers;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.datagrid.eventcontainers.asyncpolling.AsyncPollingEventContainersPanel;
import com.gigaspaces.webuitf.datagrid.eventcontainers.notify.NotifyEventContainersPanel;
import com.gigaspaces.webuitf.datagrid.eventcontainers.polling.PollingEventContainersPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class EventContainersTabPanel {
	
	private Logger _logger = Logger.getLogger( this.getClass().getName() );
	
	private final AjaxUtils _helper;

	public EventContainersTabPanel( AjaxUtils helper ) {
		this._helper = helper;
	}
	
	public AsyncPollingEventContainersPanel switchToAsyncPollingEventContainersPanel() {
		clickWhenPossible( WebConstants.ID.asyncPollingEventContainersTogglerButton );
		return new AsyncPollingEventContainersPanel( _helper );
	}

	public PollingEventContainersPanel switchToPollingEventContainersPanel() {
		clickWhenPossible( WebConstants.ID.pollingEventContainersTogglerbutton );
		return new PollingEventContainersPanel( _helper );
	}

	public NotifyEventContainersPanel switchToNotifyEventContainersPanel() {
		clickWhenPossible( WebConstants.ID.notifyEventContainersTogglerbutton );
		return new NotifyEventContainersPanel( _helper );		
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