package com.gigaspaces.webuitf.services;


import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.components.logspanel.LogsPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HostsSubView {

    protected Selenium selenium;
    protected WebDriver driver;
    protected AjaxUtils helper;

    public HostsSubView() {}

    public HostsSubView(Selenium selenium, WebDriver driver) {
        this.selenium = selenium;
        this.driver = driver;
        this.helper = new AjaxUtils(driver);
    }

    public LogsPanel switchToLogsPanel() {
        helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.logsPanelToggle));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LogsPanel(driver, selenium, WebConstants.ClassNames.GS_SIDE_MENU_HOSTS_VIEW_LOGS);
    }
}