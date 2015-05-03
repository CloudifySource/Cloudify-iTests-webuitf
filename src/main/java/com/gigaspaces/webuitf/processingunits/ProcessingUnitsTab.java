package com.gigaspaces.webuitf.processingunits;

import com.gigaspaces.webuitf.MainNavigation;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;

/**
 * represents the Services tab of thw web-ui
 * @author elip
 *
 */
public class ProcessingUnitsTab extends MainNavigation {

	/**
	 * constructs an empty instance
	 * @param selenium
	 * @param driver
	 */
	public ProcessingUnitsTab(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.helper = new AjaxUtils(driver, selenium);
	}

	/**
	 * retrieve the Host and Services grid from the Topology tab
	 * @return the Host and Services singelton
	 */
	public ProcessingUnitsGrid getProcessingUnitsGrid() {
		return ProcessingUnitsGrid.getInstance(selenium, driver);
	}

    public ProcessingUnitsSubView getProcessingUnitsSubPanel() {
        return new ProcessingUnitsSubView(selenium, driver);
    }

 /*   public void clickOnSummaryButton(){
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
    }*/
}