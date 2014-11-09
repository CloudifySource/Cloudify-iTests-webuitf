package com.gigaspaces.webuitf.services;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openspaces.admin.gsa.GridServiceAgent;
import org.openspaces.admin.gsc.GridServiceContainer;
import org.openspaces.admin.internal.pu.InternalProcessingUnitInstance;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.vm.VirtualMachineDetails;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * represents the Host and Services grid found in the web-ui under the Topology tab
 * this class is a singleton, use getInstance to obtain the instance.
 * @author elip
 *
 */
public class HostsAndServicesGrid {
	
	public static final int GSC = 0;
	public static final int GSM = 1;
	public static final int LUS = 2;
	
	private Selenium selenium;
	private WebDriver driver;
	private long gsaPID;

    final String HOST_TREE_NODE_PREFIX = "hosts_tree_grid_host_";
    final String HOSTS_TREE_PREFIX = "hosts_tree_grid_";

    final String GSA_SUFFIX = "gsa";
    final String GSC_SUFFIX = "gsc";

	private AjaxUtils helper;
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	public HostsAndServicesGrid(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.helper = new AjaxUtils(driver, selenium);
	}
	
	public static HostsAndServicesGrid getInstance(Selenium selenium, WebDriver driver) {
		return new HostsAndServicesGrid(selenium, driver);
	}
	
	/**
	 * set the gsa process id you currently wish to work with.
	 * this method must be invoked before trying to start grid service components.
	 * @param gsa
	 */
	public void setGsaPID(GridServiceAgent gsa) {
		this.gsaPID = gsa.getVirtualMachine().getDetails().getPid();
	}
	
	/**
	 * starts any type of Grid Service Component. uses the gsa provided in
	 * {@link setGsaPID(GridServiceAgent gsa)}. 
	 * @param hostname - must be the host name attached to the gsa
	 * @param component - an integer value for a component. 
	 *                    use static fields in {@link HostsAndServicesGrid}}
	 * @throws InterruptedException 
	 */
	public void startGridServiceComponent(String hostname, int component) throws InterruptedException {
		clickOnHost(hostname);
        clickOnGSAService();

        WebElement buttonElement = findToolsButton( String.valueOf( gsaPID ), true );
        if( buttonElement != null ) {
            helper.clickWhenPossible(5, TimeUnit.SECONDS, buttonElement);

            switch (component) {
                case 0: {
                    helper.waitForElement(By.id(WebConstants.ID.startGSC), 3).click();
                    break;
                }
                case 1: {
                    helper.waitForElement(By.id(WebConstants.ID.startGSM), 3).click();
                    break;
                }
                case 2: {
                    helper.waitForElement(By.id(WebConstants.ID.startLUS), 3).click();
                }
            }
        }
	}
	
	/**
	 * terminates a certain GSC via the web-ui
	 * @param gsc
	 * @throws InterruptedException 
	 */
	public void terminateGSC(String hostName, GridServiceContainer gsc) throws InterruptedException {

        WebElement buttonElement = findGscToolsButton( hostName, gsc );
        if( buttonElement != null ) {
            helper.clickWhenPossible(5, TimeUnit.SECONDS, buttonElement);

            helper.waitForElement(By.id(WebConstants.ID.terminateComponent), 5).click();
            driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert)).click();
        }
	}

    public void restartProcessingUnitInstance( String hostName,
                                            ProcessingUnitInstance processingUnitInstance)throws InterruptedException{

        WebElement buttonElement = findProcessingUnitInstanceToolsButton( hostName, processingUnitInstance );
        if( buttonElement != null ) {
            helper.clickWhenPossible(5, TimeUnit.SECONDS, buttonElement);

            helper.waitForElement(By.id(WebConstants.ID.restartPuInstance), 5).click();
            driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert)).click();
        }
    }

    public void relocateProcessingUnitInstance( String hostName, ProcessingUnitInstance processingUnitInstance,
                                                GridServiceContainer targetGsc )throws InterruptedException{

        WebElement buttonElement = findProcessingUnitInstanceToolsButton( hostName, processingUnitInstance );
        if( buttonElement != null ) {
            helper.clickWhenPossible(5, TimeUnit.SECONDS, buttonElement);

            helper.waitForElement(By.id(WebConstants.ID.relocatePuInstance), 5).click();

            WebElement relocationGridElement =
                    helper.waitForElement( TimeUnit.SECONDS, 3, By.id( WebConstants.ID.relocationGrid ) );


            String rowClassName = "x-grid3-row";

            String targetGscPid = "" + targetGsc.getVirtualMachine().getDetails().getPid();;
            String targetGscName = "gsc-" + targetGsc.getAgentId() + "[" + targetGscPid + "]";

            System.out.println( ">>> Select target " + targetGscName );

            WebElement requiredRowElement = null;
            List<WebElement> elements = relocationGridElement.findElements(By.className(rowClassName));
            System.out.println( "Rows, size=" + elements.size() );
            for( WebElement rowElement : elements ){
                if( requiredRowElement != null ){
                    break;
                }
                //String className = helper.retrieveAttribute( el, "class" );
                List<WebElement> spanElements = rowElement.findElements(By.tagName("span"));

                System.out.println( "span elements count=" + spanElements.size() );
                for( WebElement spanElement : spanElements ){
                    System.out.println( "Span before get text..." );
                    String text = spanElement.getText();
                    System.out.println( "Span element text:" + text );
                    if( text.equals( targetGscName ) ){
                        requiredRowElement = rowElement;
                        break;
                    }
                }
            }

            if( requiredRowElement != null ){
                //select required row
                requiredRowElement.click();
                List<WebElement> selectButtons =
                        helper.waitForElements(TimeUnit.SECONDS, 3, By.className("x-btn-text").tagName("button"));
                System.out.println( "select buttons size:" + selectButtons.size() );

                for( WebElement selectButton : selectButtons ) {
                    System.out.println("Button :" + selectButton.getText());
                    if( selectButton.getText().equals( "Select" ) ){
                        System.out.println( "Before click on \"Select\" button" );
                        helper.clickWhenPossible( 5, TimeUnit.SECONDS, selectButton );
                        //selectButton.click();
                        System.out.println( "After click on \"Select\" button" );
                        //click on "Yes" button in confirmation dialog
                        driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert)).click();
                        break;
                    }
                }
            }
        }
    }


	public void restartGSC(String hostName, GridServiceContainer gsc) throws InterruptedException {

        WebElement buttonElement = findGscToolsButton( hostName, gsc );
        if( buttonElement != null ) {
            helper.clickWhenPossible(5, TimeUnit.SECONDS, buttonElement);

            helper.waitForElement(By.id(WebConstants.ID.restartComponent), 5).click();
            driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert)).click();
        }
	}

    private WebElement findProcessingUnitInstanceToolsButton( String hostName, ProcessingUnitInstance processingUnitInstance ) throws InterruptedException{
        clickOnHost(hostName);
        clickOnGSAService();

        GridServiceContainer gsc = processingUnitInstance.getGridServiceContainer();
        clickOnGSCService(gsc);

        Thread.sleep(2*1000);

        String puInstanceName = ((InternalProcessingUnitInstance)processingUnitInstance).
                getProcessingUnitInstanceSimpleName();

        System.out.println( ">>> puInstanceName=" + puInstanceName );

        int puInstanceDivIndex = 3;
        while (true) {
            String searchedText = helper.waitForTextToBeExctractable(3,
                    TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToRowNumber( puInstanceDivIndex )));
            System.out.println( ">>> searchedText=" + searchedText );
            if( searchedText.contains( puInstanceName )) {
                System.out.println( ">>> contains, break" );
                break;
            }
            else {
                puInstanceDivIndex++;
            }

            if( puInstanceDivIndex == 300 ){
                throw new NoSuchElementException( "Any Processing Unit Instance tree node was not found" );
            }
        }

        return findToolsButton( puInstanceName, false );
    }


    private WebElement findGscToolsButton( String hostName, GridServiceContainer gsc ) throws InterruptedException{
        clickOnHost(hostName);
        clickOnGSAService();
        String gscPid = "" + gsc.getVirtualMachine().getDetails().getPid();
        int gscDivIndex = 2;
        while (true) {
            if (helper.waitForTextToBeExctractable(3, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToRowNumber(gscDivIndex)))
                    .contains(gscPid)) break;
            else gscDivIndex++;

            if( gscDivIndex == 300 ){
                throw new NoSuchElementException( "Any GSC tree node was not found" );
            }
        }

        return findToolsButton(gscPid, true);
    }

    private WebElement findToolsButton( String str, boolean isGridService ){

        WebElement element = null;
        WebElement buttonElement = null;
        //find grid all rows
        String rowClassName = /*isGridService ? "x-grid3-row" :*/ "gs-actions-button";
        List<WebElement> elements = driver.findElements( By.className( rowClassName ) );
        for( WebElement el : elements ) {
            String className = helper.retrieveAttribute( el, "class" );
            String id = helper.retrieveAttribute( el, "id" );
            System.out.println( "Class name [" + className + "], id [" + id +
                    "], contains [" + str + "]=" + className.contains( str ) );
            //check if this row presents specific PID in row
            if( className.contains( str ) || id.contains( HOSTS_TREE_PREFIX + str ) ){
                element = el;
                break;
            }
        }

        if( element != null ){
            buttonElement = element.findElement(By.className("x-btn-text").tagName("button"));
        }

        return buttonElement;
    }
	
	public boolean isGSCPresent(GridServiceContainer gsc) {
		String processPid = "" + gsc.getVirtualMachine().getDetails().getPid();
		return selenium.isTextPresent(processPid);
	}

    public void clickOnGSAService(){
        clickOnGridComponentService( GSA_SUFFIX, -1 );
    }

    public void clickOnGSCService( GridServiceContainer gsc ){
        VirtualMachineDetails vmDetails = gsc.getVirtualMachine().getDetails();
        long pid = vmDetails.getPid();
        clickOnGridComponentService(GSC_SUFFIX, pid);
    }

    private void clickOnGridComponentService( String serviceNamePrefix, long pid ){

        String realId = null;

        List<WebElement> elements = driver.findElements(By.className("x-tree3-node"));
        System.out.println( "elements size=" + elements.size() );
        for( WebElement el : elements ) {
            String id = helper.retrieveAttribute( el, "id" );
            System.out.println( "id=" + id  );
            if( id != null && id.contains( HOSTS_TREE_PREFIX + serviceNamePrefix ) &&
                    pid < 0 || id.contains( "[" + String.valueOf( pid ) + "]" ) ) {
                System.out.println( "iin if contains"  );
                realId = id;
                break;
            }
        }

        helper.clickWhenPossible(10, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToHostnameOptions(realId)));
    }

	public void clickOnHost(String hostname) throws InterruptedException {
		
		clickOnHost(hostname, null);
	}
	
	/**
	 * if hostname is not found, search for hostAddress.
	 * hostAddress may be null.
	 * @param hostname
	 * @param hostAddress
	 * @throws InterruptedException
	 */
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

        helper.clickWhenPossible(10, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToHostnameOptions(realId)));
	}
	
	/**
	 * @param component , input lower case strings only
	 * @return number of components of type 'component'.
	 */
	public int countNumberOf(String component) {
		int count = 0;
		int i = 2;
		String rowText = null;
		WebDriverException exception = null;
		
		while (exception == null) {
			try {
				rowText = driver.findElement(By.xpath(WebConstants.Xpath.getPathToRowNumber(i))).getText();
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
			}
		}
		return count;
	}	
}
