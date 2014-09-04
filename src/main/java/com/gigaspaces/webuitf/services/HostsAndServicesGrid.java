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

        WebElement buttonElement = findToolsButton( String.valueOf( gsaPID ) );
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
	
	public void restartGSC(String hostName, GridServiceContainer gsc) throws InterruptedException {

        WebElement buttonElement = findGscToolsButton( hostName, gsc );
        if( buttonElement != null ) {
            helper.clickWhenPossible(5, TimeUnit.SECONDS, buttonElement);

            helper.waitForElement(By.id(WebConstants.ID.restartComponent), 5).click();
            driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert)).click();
        }
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

        return findToolsButton(gscPid);
    }

    private WebElement findToolsButton( String pid ){

        WebElement element = null;
        WebElement buttonElement = null;
        //find grid all rows
        List<WebElement> elements = driver.findElements( By.className( "x-grid3-row" ) );
        for( WebElement el : elements ) {
            String className = helper.retrieveAttribute( el, "class" );
            //check if this row presents specific PID in row
            if( className.contains( pid ) ){
                element = el;
                break;
            }
        }

        if( element != null ){
            buttonElement = element.findElement( By.className( "x-btn-text" ).tagName( "button" ) );
        }

        return buttonElement;
    }
	
	public boolean isGSCPresent(GridServiceContainer gsc) {
		String processPid = "" + gsc.getVirtualMachine().getDetails().getPid();
		return selenium.isTextPresent(processPid);
	}

    public void clickOnGSAService(){
        clickOnGridComponentService( GSA_SUFFIX );
    }

    public void clickOnGSCService(){
        clickOnGridComponentService( GSC_SUFFIX );
    }

    private void clickOnGridComponentService( String serviceNamePrefix ){

        String realId = null;

        List<WebElement> elements = driver.findElements(By.className("x-tree3-node"));
        for( WebElement el : elements ) {
            String id = helper.retrieveAttribute( el, "id" );
            System.out.println( "id=" + id  );
            if( id != null && id.contains( HOSTS_TREE_PREFIX + serviceNamePrefix/*GSA_SUFFIX */) ) {
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
