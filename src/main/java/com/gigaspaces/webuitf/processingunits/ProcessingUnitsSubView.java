package com.gigaspaces.webuitf.processingunits;


import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.processingunits.details.ConfigPanel;
import com.gigaspaces.webuitf.processingunits.events.ProcessingUnitsEventsGrid;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.util.components.logspanel.LogsPanel;
import com.gigaspaces.webuitf.processingunits.physicalpanel.PhysicalPanel;

import java.util.concurrent.TimeUnit;

public class ProcessingUnitsSubView {

    protected Selenium selenium;
    protected WebDriver driver;
    protected AjaxUtils helper;

    public ProcessingUnitsSubView() {}

    public ProcessingUnitsSubView(Selenium selenium, WebDriver driver) {
        this.selenium = selenium;
        this.driver = driver;
        this.helper = new AjaxUtils(driver);
    }

    public ConfigPanel switchToConfigPanel() {
        helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.processingUnitsConfigButtonId));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ConfigPanel(selenium,driver);
    }

    public LogsPanel switchToLogsPanel() {
        helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.processingUnitsLogsButtonId));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LogsPanel(driver, selenium, WebConstants.ClassNames.GS_SIDE_MENU_PROCESSING_UNITS_VIEW_LOGS);
    }

    public PhysicalPanel switchToPhysicalPanel() {
        helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.processingUnitsHostsButtonId));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new PhysicalPanel(selenium, driver);
    }

    public ProcessingUnitsEventsGrid switchToEventsGrid() {
        helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.processingUnitsEventsButtonId));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ProcessingUnitsEventsGrid( driver, helper );
    }
}