package com.gigaspaces.webuitf.datagrid.caches;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.datagrid.caches.locacaches.LocalCachesPanel;
import com.gigaspaces.webuitf.datagrid.caches.localviews.LocalViewsContextPager;
import com.gigaspaces.webuitf.util.AjaxUtils;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by evgenyf on 5/20/2014.
 */
public class CachesPanel {

    private final AjaxUtils helper;

    private Logger _logger = Logger.getLogger( this.getClass().getName() );

    public CachesPanel( AjaxUtils helper ){
        this.helper = helper;
    }

    public LocalViewsContextPager switchToLocalViews() {
        clickWhenPossible(WebConstants.ID.localViewsPanelToggle);
        return new LocalViewsContextPager( helper );
    }

    public LocalCachesPanel switchToLocalCaches() {
        clickWhenPossible( WebConstants.ID.localCachesPanelToggle );
        return new LocalCachesPanel( helper );
    }

    private void clickWhenPossible( String id ){
        helper.clickWhenPossible( 20, TimeUnit.SECONDS, By.id(id) );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            _logger.log( Level.SEVERE, e.toString(), e );
        }
    }
}
