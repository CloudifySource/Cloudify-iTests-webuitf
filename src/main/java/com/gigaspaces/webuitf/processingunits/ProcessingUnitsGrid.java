package com.gigaspaces.webuitf.processingunits;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.services.NodeType;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * represents the Host and Services grid found in the web-ui under the Topology tab
 * this class is a singleton, use getInstance to obtain the instance.
 * @author elip
 *
 */
public class ProcessingUnitsGrid {

    protected static final int WAIT_TIMEOUT_IN_SECONDS = 10;

	private Selenium selenium;
	private WebDriver driver;
	private long gsaPID;

//    final String HOST_TREE_NODE_PREFIX = "hosts_tree_grid_host_";
    final String PU_TREE_PREFIX = "processing_units_tree_grid_";
    final String PU_NODE_PREFIX = "pu_name_";

	private AjaxUtils helper;
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	public ProcessingUnitsGrid(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.helper = new AjaxUtils(driver, selenium);
	}

	public static ProcessingUnitsGrid getInstance(Selenium selenium, WebDriver driver) {
		return new ProcessingUnitsGrid(selenium, driver);
	}

    public void undeployProcessingUnit(String processingUnitName) throws InterruptedException {

        WebElement buttonElement = findProcessingUnitToolsButton(processingUnitName);
        String id = buttonElement.getAttribute("id");
        String tagName = buttonElement.getTagName();
        String className = buttonElement.getAttribute("class");
        if( buttonElement != null ) {
            helper.clickWhenPossible(5, TimeUnit.SECONDS, buttonElement);

            helper.waitForElement(By.id(WebConstants.ID.puGridUninstallMenuItem), 5).click();
            driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert)).click();
        }
    }


    private WebElement findProcessingUnitToolsButton( String processingUnitName ) throws InterruptedException{
        int puDivIndex = 1;
        while (true) {
            By buttonXPath = By.xpath( WebConstants.Xpath.getPathToProcessingUnitsRowNumber( puDivIndex ) );
            System.out.println( ">buttonXPath=" + buttonXPath );
            if( helper.waitForTextToBeExctractable(3, TimeUnit.SECONDS, buttonXPath ).contains(processingUnitName)){
                break;
            }
            else{
                puDivIndex++;
            }

            if( puDivIndex == 300 ){
                throw new NoSuchElementException( "Any Processing Unit tree node was not found" );
            }
        }

        return findToolsButton( puDivIndex );
    }

    private WebElement findToolsButton( int divIndex ){
        By buttonXPath = By.xpath( WebConstants.Xpath.getPathToProcessingUnitsRowToolsButton( divIndex ) );
        WebElement buttonElement = helper.waitForElement(TimeUnit.SECONDS, 10, buttonXPath );

        return buttonElement;
    }

    public String getProcessingUnitType( String processingUnitName ) {
        int puDivIndex = - 1;
        try {
            puDivIndex = getProcessingUnitRowDivIndex(processingUnitName);
        }
        catch( Exception exc ){
            exc.printStackTrace();
        }

        return puDivIndex < 0 ? "n/a" : getCellValue( WebConstants.Xpath.getPathToProcessingUnitsRowPuType( puDivIndex ) );
    }

    public String getProcessingUnitStatus( String processingUnitName ) {
        int puDivIndex = - 1;
        try {
            puDivIndex = getProcessingUnitRowDivIndex(processingUnitName);
        }
        catch( Exception exc ){
            exc.printStackTrace();
        }

        return puDivIndex < 0 ? "n/a" : getCellValue( WebConstants.Xpath.getPathToProcessingUnitsRowStatus( puDivIndex ) );
    }

    public int getProcessingUnitRowDivIndex( String processingUnitName ) throws InterruptedException{
        int puDivIndex = 1;
        while (true) {
            By buttonXPath = By.xpath( WebConstants.Xpath.getPathToProcessingUnitsRowNumber( puDivIndex ) );
            System.out.println( ">buttonXPath=" + buttonXPath );
            if( helper.waitForTextToBeExctractable(3, TimeUnit.SECONDS, buttonXPath ).contains(processingUnitName)){
                break;
            }
            else{
                puDivIndex++;
            }

            if( puDivIndex == 300 ){
                throw new NoSuchElementException( "Any Processing Unit tree node was not found" );
            }
        }

        return puDivIndex;
    }

    private String getCellValue( String xPath ){
        By cellXPath = By.xpath( xPath );
        WebElement cellElement = helper.waitForElement(TimeUnit.SECONDS, 10, cellXPath );
        return cellElement.getText();
    }


/*
    public void clickOnGSAService(){
        clickOnGridComponentService( GSA_SUFFIX, -1 );
    }

    public void clickOnGSCService( GridServiceContainer gsc ){
        VirtualMachineDetails vmDetails = gsc.getVirtualMachine().getDetails();
        long pid = vmDetails.getPid();
        clickOnGridComponentService(GSC_SUFFIX, pid);
    }
*/


    /*
    public void clickOnProcessingUnitInstance( ProcessingUnitInstance puInstance ){
        String puInstanceName = puInstance.getProcessingUnitInstanceName();
        String realId = null;

        List<WebElement> elements = driver.findElements(By.className("x-tree3-node"));
        logger.info( "elements size=" + elements.size() );
        for( WebElement el : elements ) {
            String id = helper.retrieveAttribute( el, "id" );
            logger.info( "id=" + id  );
            if( id != null && id.contains( PU_TREE_PREFIX + puInstanceName ) ){
                logger.info( "in if contains"  );
                realId = id;
                break;
            }
        }

        helper.clickWhenPossible(20, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToHostnameOptions(realId)));
    }*/

    /*
    private void clickOnGridComponentService( String serviceNamePrefix, long pid ){

        String realId = null;

        List<WebElement> elements = driver.findElements(By.className("x-tree3-node"));
        logger.info( "elements size=" + elements.size() );
        for( WebElement el : elements ) {
            String id = helper.retrieveAttribute( el, "id" );
            logger.info( "id=" + id  );
            if( id != null && id.contains( PU_TREE_PREFIX + serviceNamePrefix ) &&
                    pid < 0 || id.contains( "[" + String.valueOf( pid ) + "]" ) ) {
                logger.info( "iin if contains"  );
                realId = id;
                break;
            }
        }

        helper.clickWhenPossible(20, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToHostnameOptions(realId)));
    }*/


	/**
	 * if hostname is not found, search for hostAddress.
	 * hostAddress may be null.
	 * @param hostname
	 * @param hostAddress
	 * @throws InterruptedException
	 */
    /*
	public void clickOnHost(String hostname, String hostAddress) throws InterruptedException {
		
		String hostsTreePrefix = HOST_TREE_NODE_PREFIX;
		String realId = null;

		List<WebElement> elements = driver.findElements(By.className("x-tree3-node"));
		for (WebElement el : elements) {
			String id = helper.retrieveAttribute(el, "id");
			
			if ((id != null) && ((id.contains(hostsTreePrefix + hostname)) || ((hostAddress != null) && (id.contains(hostsTreePrefix + hostAddress))))) {
				realId = id;
				break;
			}
		}

        logger.info( ">>realId=" + realId );

        helper.clickWhenPossible(20, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToHostnameOptions(realId)));
	}*/
	
	/**
	 * @param component , input lower case strings only
	 * @return number of components of type 'component'.
	 */
	public int countNumberOf(String component) {
		int count = 0;
		int i = 1;
		String rowText = null;
		WebDriverException exception = null;
		
		while (exception == null) {
			try {
				rowText = driver.findElement(By.xpath(WebConstants.Xpath.getPathToProcessingUnitsRowNumber(i))).getText();
				if (rowText.contains(component)) {
					logger.info("Found " + component + "[Count=" + ++count + "]");
				}
				i++;
			}
			catch (NoSuchElementException e) {
				logger.severe("Caught NoSuchElementException while locating element " + e.getMessage());
				exception = e;
			}
			catch (WebDriverException e) {
				logger.severe("caught an exception while locating element ");
                exception = e;
            }
		}
		return count;
	}

    public void expandProcessingUnitRow( String puName ){

        String realId = null;

        List<WebElement> elements = driver.findElements(By.className("x-tree3-node"));
        logger.info( "elements size=" + elements.size() );
        for( WebElement el : elements ) {
            String id = helper.retrieveAttribute( el, "id" );
            logger.info( "id=" + id  );
            if( id != null && id.contains( PU_TREE_PREFIX + PU_NODE_PREFIX + puName ) ) {
                logger.info( "in if contains"  );
                realId = id;
                break;
            }
        }

        helper.clickWhenPossible(20, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToHostnameOptions(realId)));
    }


    public void selectProcessingUnitRow( final String puName ){
        RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {
            @Override
            public boolean getCondition() {
                try {
                    logger.info( "within condition, selectProcessingUnitRow, puName=" + puName );
                    selectProcessingUnit(puName);
                }
                catch( Throwable t ){
                    logger.log(Level.WARNING, t.toString(), t );
                    return false;
                }

                return true;
            }
        };
        AjaxUtils.repetitiveAssertTrue("Unable to select processing unit row [" + puName + "]", condition, 12*1000 );
    }

    private void selectProcessingUnit( String puName ){

        List<WebElement> elements = driver.findElements(By.className("x-tree3-node"));
        int listsSize = elements.size();
        logger.info( "elements size=" + listsSize );
        int index;
        for( index = 0; index < listsSize; index++ ) {
            WebElement el = elements.get( index );
            String id = helper.retrieveAttribute( el, "id" );
            logger.info( "id=" + id  );
            if( id != null && id.contains( PU_TREE_PREFIX + PU_NODE_PREFIX + puName ) ) {
                logger.info( "in if contains"  );
                break;
            }
        }

        helper.clickWhenPossible(10, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToHeaderProcessingUnitsGrid(index)));
    }


    public void selectRow( int index ) {

        List<WebElement> visibleElements =
                driver.findElements(By.className(WebConstants.ClassNames.ServicesGridApplicationNameCell));

        int numOfElements = visibleElements.size() - 1; // subtracting the irrelevant "component-name" headline

        if( index > 0 && index < numOfElements ){
            logger.info( ">> getRow element, index=" + index );
            WebElement rowElement = helper.waitForElement(
                    By.xpath(WebConstants.Xpath.getPathToHeaderProcessingUnitsGrid(index)), WAIT_TIMEOUT_IN_SECONDS);
            helper.clickWhenPossible( 10, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToHeaderProcessingUnitsGrid(index)) );
        }
    }

/*
    public List<AbstractServiceHostWrapper> getHostsAndServices() {

        List<WebElement> visibleElements =
                driver.findElements(By.className(WebConstants.ClassNames.ServicesGridApplicationNameCell));

        int numOfElements = visibleElements.size() - 1; // subtracting the irrelevant "component-name" headline
        List<AbstractServiceHostWrapper> visibleRows = new ArrayList<AbstractServiceHostWrapper>( numOfElements );

        for(int i = 1; i <= numOfElements; i++){
            logger.info( ">>> i=" + i );
            visibleRows.add( getRow( i ) );
        }

        return visibleRows;
    }
*/
/*
    private AbstractServiceHostWrapper getRow(int index) {
        logger.info( ">> getRow(), index=" + index );
        WebElement rowElement = helper.waitForElement(
                By.xpath(WebConstants.Xpath.getPathToHeaderProcessingUnitsGrid(index)), WAIT_TIMEOUT_IN_SECONDS);

        return prepareRow(rowElement);
    }
    */
/*
    protected AbstractServiceHostWrapper prepareRow( WebElement rowElement ) {

        WebElement nameElement = rowElement.findElement(By.className(WebConstants.ClassNames.ServicesGridNameCell));
        String name = retrieveNameText(nameElement);

        String id = nameElement.getAttribute("id");

        logger.info( ">>>prepareRow, name=" + name + ", ID=" + id );

        WebElement typeElement = helper.waitForElement(By.xpath(WebConstants.Xpath.getPathToTypeElement(id)), 5);
        String typeElementId = typeElement.getAttribute( "id" );
        NodeType nodeType = getNodeType(typeElementId);

        AbstractServiceHostWrapper serviceHostWrapper = createWrapper( nodeType, name, rowElement );

        logger.info( ">>>prepareRow, typeElementId=" + typeElementId );

        return serviceHostWrapper;
    }*/
/*
    private AbstractServiceHostWrapper createWrapper( NodeType nodeType, String name, WebElement rowElement ){

        AbstractServiceHostWrapper retValue = null;
        switch( nodeType ){
            case HOST:
                retValue = new HostWrapper( name, nodeType );
                break;

            case GRID_SERVICE:
                WebElement zonesElement = rowElement.findElement(By.className(WebConstants.ClassNames.ServicesGridZonesCell));
                WebElement threadsCountElement = rowElement.findElement(By.className(WebConstants.ClassNames.ServicesGridThreadCountCell));

                String zones = retrieveRegularText(zonesElement);
                String threadsCountStr = retrieveRegularText(threadsCountElement);
                retValue = new GridServiceWrapper( name, nodeType, Integer.parseInt( threadsCountStr ), zones );
                break;

            case PROCESSING_UNIT_INSTANCE:GRID_SERVICE:
                zonesElement = rowElement.findElement(By.className(WebConstants.ClassNames.ServicesGridZonesCell));
                WebElement appNameElement = rowElement.findElement(By.className(WebConstants.ClassNames.ServicesGridApplicationNameCell));
                WebElement puTypeElement = rowElement.findElement(By.className(WebConstants.ClassNames.ServicesGridPuTypeCell));
                threadsCountElement = rowElement.findElement(By.className(WebConstants.ClassNames.ServicesGridThreadCountCell));

                String appName = retrieveRegularText(appNameElement);
                zones = retrieveRegularText(zonesElement);
                String puType = retrieveRegularText(puTypeElement);
                retValue = new ProcessingUnitInstanceWrapper( name, nodeType, zones, puType, appName );
                break;

            case SPACE_INSTANCE:
                retValue = new SpaceInstanceWrapper( name, nodeType );
                break;
        }

        return retValue;
    }*/

    private NodeType getNodeType( String typeElementId ){

        NodeType retValue;
        if( typeElementId.contains( WebConstants.SERVICE_ID_PREFIX + "HostTreeNode" + WebConstants.SERVICE_ID_SUFFIX ) ){
            retValue = NodeType.HOST;
        }
        else if( typeElementId.contains( WebConstants.SERVICE_ID_PREFIX + "ProcessingUnitInstanceTreeNode" + WebConstants.SERVICE_ID_SUFFIX ) ){
            retValue = NodeType.PROCESSING_UNIT_INSTANCE;
        }
        else if( typeElementId.contains(  WebConstants.SERVICE_ID_PREFIX + "SpaceInstanceTreeNode" + WebConstants.SERVICE_ID_SUFFIX ) ){
            retValue = NodeType.SPACE_INSTANCE;
        }
        else{
            retValue = NodeType.GRID_SERVICE;
        }

        return retValue;
    }

    private String retrieveNameText(WebElement cellElement) {
        return retrieveText(cellElement, WebConstants.Xpath.pathToServicesNameText);
    }

    private String retrieveRegularText(WebElement cellElement) {
        return retrieveText( cellElement, WebConstants.Xpath.pathToServicesRegularText );
    }

    private String retrieveText( WebElement cellElement, String xPath ) {
        WebElement webElement = null;
        try {
            webElement = cellElement.findElement(By.xpath(xPath));
        }
        catch( Exception e ){
//            e.printStackTrace();
        }
//        WebElement webElement = helper.waitForElement(cellElement, TimeUnit.SECONDS, 10, By.xpath(xPath));
        return webElement == null ? null : webElement.getText();
    }
}

