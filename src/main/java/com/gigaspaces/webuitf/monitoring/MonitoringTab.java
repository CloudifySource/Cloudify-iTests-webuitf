package com.gigaspaces.webuitf.monitoring;

import com.gigaspaces.webuitf.BaseApplicationContextPanel;
import com.gigaspaces.webuitf.WebConstants;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class MonitoringTab extends BaseApplicationContextPanel {

    public MonitoringTab(Selenium selenium, WebDriver driver) {
        super(selenium, driver);
    }

    public boolean isNotAuthorized(){
        WebElement labelElement =
                helper.waitForElement(TimeUnit.SECONDS, 3, By.id( WebConstants.ID.monitoringUnavailableNotAuthorized ));
        return labelElement != null;
    }

    public boolean isDbNotDefined(){
        WebElement labelElement =
                helper.waitForElement(TimeUnit.SECONDS, 3, By.id( WebConstants.ID.monitoringUnavailableDbNotDefined ));
        return labelElement != null;
    }
}
