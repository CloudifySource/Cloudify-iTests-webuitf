package com.gigaspaces.webuitf;

import com.gigaspaces.webuitf.alerts.AlertsPanel;
import com.gigaspaces.webuitf.datagrid.DataGridTab;
import com.gigaspaces.webuitf.events.HeaderEventsGrid;
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

    private boolean clickOnTabButton( String buttonId ) throws Exception{

        WebElement buttonTableElement = helper.waitForElement(
                TimeUnit.SECONDS, TIMEOUT_IN_SECONDS, By.id( buttonId ));

        String className = buttonTableElement.getAttribute( "class" );
        //if style is disabled then return false since button is not clickable
        //GS-
        if( className.contains( "x-item-disabled" ) ){
            return false;
        }

        WebElement button = buttonTableElement.findElement(By.className("x-btn-text").tagName("button"));
        helper.clickWhenPossible( TIMEOUT_IN_SECONDS, TimeUnit.SECONDS, button );
        return true;
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
            boolean isClickable = clickOnTabButton(WebConstants.ID.monitoringButton);
            if( !isClickable ){
                return null;
            }
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

    public HeaderEventsGrid showEvents(){
        WebElement webElement = helper.waitForElement(TimeUnit.SECONDS, 5, By.id( WebConstants.ID.eventsButton ) );
        helper.clickWhenPossible( 5, TimeUnit.SECONDS, By.id( WebConstants.ID.eventsButton ) );
        return new HeaderEventsGrid(driver, helper );
    }

    public AlertsPanel showAlerts(){
        WebElement webElement = helper.waitForElement(TimeUnit.SECONDS, 5, By.id( WebConstants.ID.alertsButton ) );
        helper.clickWhenPossible( 5, TimeUnit.SECONDS, By.id( WebConstants.ID.alertsButton ) );
        return new AlertsPanel( selenium, driver );
    }

    public boolean isXap() {
        driver.findElement(By.id("gs-about-button")).click();
        return selenium.isTextPresent("XAP Premium");
    }

    public LookupDetails getLookupDetails() {
        driver.findElement(By.id("gs-about-button")).click();
        WebElement lookupLocatorsElement = null;
        WebElement lookupGroupsElement = null;
        try{
            lookupLocatorsElement = helper.waitForElement(
                    By.className( WebConstants.ClassNames.lookupLocators ), 10 );
        }
        catch( Exception e ){
        }
        try{
            lookupGroupsElement = helper.waitForElement(
                    By.className( WebConstants.ClassNames.lookupGroups ), 10 );
        }
        catch( Exception e ){
        }

        String locators = lookupLocatorsElement == null ? null : lookupLocatorsElement.getText();
        String groups = lookupGroupsElement == null ? null : lookupGroupsElement.getText();

        return new LookupDetails(groups, locators );
    }

    public int getHostsCount(){
        return getServicesCount( WebConstants.ID.statusBarInfrastructureHosts );
    }

    public int getGsaCount(){
        return getServicesCount( WebConstants.ID.statusBarInfrastructureGsa );
    }

    public int getGsmCount(){
        return getServicesCount( WebConstants.ID.statusBarInfrastructureGsm );
    }

    public int getGscCount(){
        return getServicesCount( WebConstants.ID.statusBarInfrastructureGsc );
    }

    public int getLusCount(){
        return getServicesCount( WebConstants.ID.statusBarInfrastructureLus );
    }

    public int getEsmCount(){
        return getServicesCount( WebConstants.ID.statusBarInfrastructureEsm );
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

    public double getStatefulServicesThroughput(){
        return getServicesThroughput( WebConstants.ID.statusBarServicesStatefulThroughput );
    }

    public double getWebServicesThroughput (){
        return getServicesThroughput( WebConstants.ID.statusBarServicesWebThroughput );
    }

    private int getServicesCount( String id ){

        int servicesCount = 0;
        try{
            WebElement element = helper.waitForElement( By.id( id ), 7 );
            if( element != null ) {
                String txt = element.getText();
                logger.info( ">> getServicesCount, id=" + id + ", txt=" + txt );
                if( txt != null && txt.trim().length() > 0 ) {
                    int spaceLastIndex = txt.lastIndexOf(" ");
                    spaceLastIndex = spaceLastIndex < 0 ? 0 : spaceLastIndex;
                    String numStr = txt.substring( spaceLastIndex, txt.length() ).trim();
                    if( numStr.length() > 0 ){
                        servicesCount = Integer.parseInt( numStr );
                    }
                    logger.info( ">> servicesCount=" + servicesCount );
                }
            }
        }
        catch( Exception e ){
            logger.severe( "Failed to find hosts element" + e.toString() );
        }

        return servicesCount;
    }

    private double getServicesThroughput( String id ){

        double servicesThroughput = -1;
        try{
            WebElement element = helper.waitForElement( By.id( id ), 7 );
            if( element != null ) {
                String txt = element.getText();
                logger.info( ">> getServicesThroughput, id=" + id + ", txt=" + txt );
                if( txt != null && txt.trim().length() > 0 ) {
                    int startIndex = txt.indexOf("(");
                    int lastIndex = txt.indexOf( " " );
                    String numStr = txt.substring( startIndex + 1, lastIndex ).trim();
                    if( numStr.length() > 0 ){
                        servicesThroughput = Double.parseDouble( numStr );
                    }
                    logger.info( ">> servicesThroughput=" + servicesThroughput );
                }
            }
        }
        catch( Exception e ){
            logger.severe( "Failed to find hosts element" + e.toString() );
        }

        return servicesThroughput;
    }


    public class LookupDetails{

        private final String _lookupGroups;
        private final String _lookupLocators;

        public LookupDetails( String lookupGroups, String lookupLocators ){
            _lookupGroups = lookupGroups;
            _lookupLocators = lookupLocators;
        }

        public String getLookupGroups() {
            return _lookupGroups;
        }

        public String getLookupLocators() {
            return _lookupLocators;
        }
    }
}