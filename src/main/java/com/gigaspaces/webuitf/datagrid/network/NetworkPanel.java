package com.gigaspaces.webuitf.datagrid.network;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.datagrid.network.inboundactivity.InboundActivityPanel;
import com.gigaspaces.webuitf.datagrid.network.notificationsactivity.NotificationsActivityPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author evgenyf
 * @since 10.0
 */
public class NetworkPanel {

    private final AjaxUtils helper;

    private Logger _logger = Logger.getLogger( this.getClass().getName() );

    public NetworkPanel(AjaxUtils helper){
        this.helper = helper;
    }

    public NotificationsActivityPanel switchToNotificationsActivity() {
        clickWhenPossible(WebConstants.ID.notificationsActivityPanelToggle);
        return new NotificationsActivityPanel( helper );
    }

    public InboundActivityPanel switchToInboundActivity() {
        clickWhenPossible( WebConstants.ID.inboundActivityPanelToggle );
        return new InboundActivityPanel( helper );
    }

    private void clickWhenPossible( String id ){
        helper.clickWhenPossible( 20, TimeUnit.SECONDS, By.id(id) );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            _logger.log( Level.SEVERE, e.toString(), e );
        }
    }

    public void setRemoteActivityProbe(boolean b) {

        helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id( "gs-remote-activity" ) );
    }

    public void getRemoteActivityProbeValue() {

        WebElement webElement = helper.waitForElement(TimeUnit.SECONDS, 5, By.id("gs-remote-activity"));
        System.out.println( "RemoteActivityProbeValue, "+ webElement );
    }
}
