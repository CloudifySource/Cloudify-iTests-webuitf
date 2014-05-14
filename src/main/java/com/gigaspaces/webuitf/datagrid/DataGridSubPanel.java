package com.gigaspaces.webuitf.datagrid;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gigaspaces.webuitf.datagrid.configuration.ConfigurationPanel;
import org.openqa.selenium.By;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.datagrid.eventcontainers.EventContainersTabPanel;
import com.gigaspaces.webuitf.datagrid.gateways.GatewaysPanel;
import com.gigaspaces.webuitf.datagrid.inboundactivity.InboundActivityPanel;
import com.gigaspaces.webuitf.datagrid.locacaches.LocalCachesPanel;
import com.gigaspaces.webuitf.datagrid.localviews.LocalViewsContextPager;
import com.gigaspaces.webuitf.datagrid.notificationsactivity.NotificationsActivityPanel;
import com.gigaspaces.webuitf.datagrid.queriespanel.QueriesPanel;
import com.gigaspaces.webuitf.datagrid.statisticspanel.StatisticsPanel;
import com.gigaspaces.webuitf.datagrid.typespanel.TypesPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class DataGridSubPanel {
	
//	protected WebDriver driver;
	private Logger _logger = Logger.getLogger( this.getClass().getName() );
	protected final AjaxUtils helper;
	
	public DataGridSubPanel(AjaxUtils helper) {
//		this.driver = driver;
		this.helper = helper;
	}

	public QueriesPanel switchToQueriesPanel() {
		clickWhenPossible( WebConstants.ID.queriesPanelToggle );
		return new QueriesPanel(helper);
	}

	public StatisticsPanel switchToStatisticsPanel() {
		clickWhenPossible( WebConstants.ID.statisticsPanelToggle );
		return new StatisticsPanel(helper);
	}

	public TypesPanel switchToTypesPanel() {
		clickWhenPossible( WebConstants.ID.typesPanelToggle );
		return new TypesPanel(helper);
	}

	public GatewaysPanel switchToGateways() {
		clickWhenPossible( WebConstants.ID.gatewaysPanelToggle );
		return new GatewaysPanel( helper );		
	}
	
	public LocalViewsContextPager switchToLocalViews() {
		clickWhenPossible( WebConstants.ID.localViewsPanelToggle );
		return new LocalViewsContextPager( helper );		
	}
	
	public EventContainersTabPanel switchToEventContainers() {
		clickWhenPossible( WebConstants.ID.eventContainersPanelToggle );
		return new EventContainersTabPanel( helper );		
	}	
	
	private void clickWhenPossible( String id ){
		helper.clickWhenPossible( 20, TimeUnit.SECONDS, By.id( id ) );
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			_logger.log( Level.SEVERE, e.toString(), e );
		}		
	}

	public LocalCachesPanel switchToLocalCaches() {
		clickWhenPossible( WebConstants.ID.localCachesPanelToggle );
		return new LocalCachesPanel( helper );
	}
	
	public InboundActivityPanel switchToInboundActivity() {
		clickWhenPossible( WebConstants.ID.inboundActivityPanelToggle );
		return new InboundActivityPanel( helper );
	}
	
	public NotificationsActivityPanel switchToNotificationsActivity() {
		clickWhenPossible( WebConstants.ID.notificationsActivityPanelToggle );
		return new NotificationsActivityPanel( helper );
	}

    public ConfigurationPanel switchToConfiguration() {
        clickWhenPossible( WebConstants.ID.configurationPanelToggle );
        return new ConfigurationPanel( helper );
    }
}