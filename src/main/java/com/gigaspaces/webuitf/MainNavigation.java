package com.gigaspaces.webuitf;

import com.gigaspaces.webuitf.dashboard.DashboardTab;
import com.gigaspaces.webuitf.datagrid.DataGridTab;
import com.gigaspaces.webuitf.services.ServicesTab;
import com.gigaspaces.webuitf.topology.TopologyTab;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class MainNavigation {
	
	protected Selenium selenium;
	protected WebDriver driver;
	protected AjaxUtils helper;
	
	protected Logger logger = Logger.getLogger(MainNavigation.class.getName());

	private static int TIMEOUT_IN_SECONDS = 40;
	
	public MainNavigation() {}
	
	public MainNavigation(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.helper = new AjaxUtils(driver, selenium);
	}
	
	public LoginPage logout() throws InterruptedException {
		selenium.click(WebConstants.ID.logoutButton);
		selenium.click(WebConstants.Xpath.acceptAlert);
		Thread.sleep(1000);
		return new LoginPage(selenium, driver);
	}


    private void clickOnTabButton( String butttonId ) throws Exception{

        WebElement buttonTableElement = helper.waitForElement(
                TimeUnit.SECONDS, TIMEOUT_IN_SECONDS, By.id( butttonId ));
        WebElement button = buttonTableElement.findElement(By.className("x-btn-text").tagName("button"));
        helper.clickWhenPossible( TIMEOUT_IN_SECONDS, TimeUnit.SECONDS, button );
    }

	public ServicesTab switchToServices() {

        try {
            clickOnTabButton(WebConstants.ID.servicesButton);
        }
        catch( Exception exc ){
            exc.printStackTrace();
            return null;
        }
		return new ServicesTab(selenium, driver);
	}
	
	public DashboardTab switchToDashboard() {
        try {
            clickOnTabButton(WebConstants.ID.dashBoardButton);
        }
        catch( Exception exc ){
            exc.printStackTrace();
            return null;
        }

		return new DashboardTab(selenium, driver);
	}
	
	public TopologyTab switchToTopology() {
        try {
            clickOnTabButton(WebConstants.ID.topologyButton);
        }
        catch( Exception exc ){
            exc.printStackTrace();
            return null;
        }

		return new TopologyTab(selenium, driver);
	}

	public DataGridTab switchToDataGrid() {
		return switchToDataGrid( true );
	}
	
	public DataGridTab switchToDataGrid( boolean wait ) {
        try {
            clickOnTabButton(WebConstants.ID.consoleButton);
        }
        catch( Exception exc ){
            exc.printStackTrace();
            return null;
        }

		return new DataGridTab(selenium, driver);
	}

	public String getLookupLocators() {
		
		WebElement lookupLocatorsElement = null; 
		try{
			lookupLocatorsElement = helper.waitForElement( 
						By.className( WebConstants.ClassNames.lookupLocators ), 10 );
		}
		catch( Exception e ){
		}
		
		return lookupLocatorsElement == null ? null : lookupLocatorsElement.getText();
	}

	public String getLookupGroups() {
		
		WebElement lookupGroupsElement = null;
		try{
			lookupGroupsElement = helper.waitForElement( 
						By.className( WebConstants.ClassNames.lookupGroups ), 10 );
		}
		catch( Exception e ){
		}
		
		return lookupGroupsElement == null ? null : lookupGroupsElement.getText();
	}	
}