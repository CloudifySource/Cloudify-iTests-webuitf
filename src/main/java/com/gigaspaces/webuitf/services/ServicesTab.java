package com.gigaspaces.webuitf.services;

import com.gigaspaces.webuitf.MainNavigation;
import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * represents the Services tab of thw web-ui
 * @author elip
 *
 */
public class ServicesTab extends MainNavigation {

	/**
	 * constructs an empty instance
	 * @param selenium
	 * @param driver
	 */
	public ServicesTab(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.helper = new AjaxUtils(driver, selenium);
	}

	/**
	 * retrieve the Host and Services grid from the Topology tab
	 * @return the Host and Services singelton
	 */
	public HostsAndServicesGrid getHostAndServicesGrid() {
		return HostsAndServicesGrid.getInstance(selenium, driver);
	}

    public void clickOnSummaryButton(){
        helper.clickWhenPossible(3, TimeUnit.SECONDS, By.id( WebConstants.ID.hostsSummaryButtonId ) );
    }

    public void clickOnLogsButton(){
        helper.clickWhenPossible(3, TimeUnit.SECONDS, By.id( WebConstants.ID.hostsLogsButtonId ) );
    }

    public String getSummaryText(){
        WebElement webElement = helper.waitForElement(TimeUnit.SECONDS, 3, By.className("gs-config-panel-header"));
        return webElement.getText();
    }

    public String getLogsTitle(){
        WebElement webElement = helper.waitForElement(TimeUnit.SECONDS, 3, By.className("gs-logs-service-name"));
        return webElement.getText();
    }


}