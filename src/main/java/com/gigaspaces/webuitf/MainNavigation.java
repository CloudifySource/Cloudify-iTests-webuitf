package com.gigaspaces.webuitf;

import com.gigaspaces.webuitf.datagrid.DataGridTab;
import com.gigaspaces.webuitf.monitoring.MonitoringTab;
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
	
	public MonitoringTab switchToMonitoring() {
        try {
            clickOnTabButton(WebConstants.ID.monitoringButton);
        }
        catch( Exception exc ){
            exc.printStackTrace();
            return null;
        }

		return new MonitoringTab(selenium, driver);
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

    public boolean isXap() {
        driver.findElement(By.id("gs-about-button")).click();
        return selenium.isTextPresent("XAP Premium");
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

    public int getHostsCount(){

        int hostsCount = -1;
        try{
            WebElement hostsElement = helper.waitForElement( By.id( WebConstants.ID.statusBarInfrastructureHosts ), 7 );
            String txt = hostsElement.getText();
            System.out.println( ">> txt" + txt );
        }
        catch( Exception e ){
            logger.severe( "Failed to find hosts element" + e.toString() );
        }

        return hostsCount;
    }

    public int getGsaCount(){

        int gsaCount = -1;
        try{
            WebElement gsaElement = helper.waitForElement( By.id( WebConstants.ID.statusBarInfrastructureGsa ), 7 );
            String txt = gsaElement.getText();
            System.out.println( ">> txt" + txt );
        }
        catch( Exception e ){
            logger.severe( "Failed to find hosts element" + e.toString() );
        }

        return gsaCount;
    }


    public int getGsmCount(){

        int gsmCount = -1;
        try{
            WebElement gsmElement = helper.waitForElement( By.id( WebConstants.ID.statusBarInfrastructureGsm ), 7 );
            String txt = gsmElement.getText();
            System.out.println( ">> txt" + txt );
        }
        catch( Exception e ){
            logger.severe( "Failed to find hosts element" + e.toString() );
        }

        return gsmCount;
    }


    public int getGscCount(){

        int gscCount = -1;
        try{
            WebElement gscElement = helper.waitForElement( By.id( WebConstants.ID.statusBarInfrastructureGsc ), 7 );
            String txt = gscElement.getText();
            System.out.println( ">> txt" + txt );
        }
        catch( Exception e ){
            logger.severe( "Failed to find hosts element" + e.toString() );
        }

        return gscCount;
    }


    public int getLusCount(){

        int lusCount = -1;
        try{
            WebElement lusElement = helper.waitForElement( By.id( WebConstants.ID.statusBarInfrastructureLus ), 7 );
            String txt = lusElement.getText();
            System.out.println( ">> txt" + txt );
        }
        catch( Exception e ){
            logger.severe( "Failed to find hosts element" + e.toString() );
        }

        return lusCount;
    }


    public int getEsmCount(){

        int esmCount = -1;
        try{
            WebElement esmElement = helper.waitForElement( By.id( WebConstants.ID.statusBarInfrastructureEsm ), 7 );
            String txt = esmElement.getText();
            System.out.println( ">> txt" + txt );
        }
        catch( Exception e ){
            logger.severe( "Failed to find hosts element" + e.toString() );
        }

        return esmCount;
    }

    public int getStatefulServicesCount(){

        return getServicesCount( WebConstants.ID.statusBarServicesStateful );
    }

    public int getStatelessServicesCount(){

        return getServicesCount( WebConstants.ID.statusBarServicesStateless );
    }

    public int getWebServicesCount(){

        return getServicesCount( WebConstants.ID.statusBarServicesWeb );
    }

    public int getGatewayServicesCount(){

        return getServicesCount( WebConstants.ID.statusBarServicesGateway );
    }

    public int getMirrorServicesCount(){

        return getServicesCount( WebConstants.ID.statusBarServicesMirror );
    }


    private int getServicesCount( String id ){

        int servicesCount = -1;
        try{
            WebElement element = helper.waitForElement( By.id( id ), 7 );
            String txt = element.getText();
            System.out.println( ">> txt" + txt );
        }
        catch( Exception e ){
            logger.severe( "Failed to find hosts element" + e.toString() );
        }

        return servicesCount;
    }

}