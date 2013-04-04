package com.gigaspaces.webuitf.services;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.gigaspaces.internal.cluster.node.impl.ReplicationLogUtils;
import com.sun.jini.logging.LogUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openspaces.admin.gsa.GridServiceAgent;
import org.openspaces.admin.gsc.GridServiceContainer;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;

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
		String realId = null;
		List<WebElement> elements = driver.findElements(By.className("x-tree3-node"));
        logger.info("searching gsaPID '" + gsaPID + "'");

        for (int i = 0 ; i < elements.size() ; i++) {
            String xpath = "//div[@id='hosts_tree_grid']/div/div/div[2]/div/div[" + (i+1) + "]/table/tbody/tr/td/div/div";
            String id = helper.retrieveAttribute(By.xpath(xpath), "id", driver);
            logger.info("found id '" + id + "'");
			if ((id != null) && (id.contains(Long.toString(gsaPID)))) {
				realId = id;
				break;
			}
		}		

		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToGsaOption(realId)));
		switch (component)  {
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
	
	/**
	 * terminates a certain GSC via the web-ui
	 * @param gsc
	 * @throws InterruptedException 
	 */
	public void terminateGSC(String hostName, GridServiceContainer gsc) throws InterruptedException {
		clickOnHost(hostName);
		String gscPid = "" + gsc.getVirtualMachine().getDetails().getPid();
        String componentName;
		int gscDivIndex = 2;
		while (true) {
            componentName = helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToRowNumber(gscDivIndex)));
            logger.info("found component: " + componentName);
            if (componentName.contains(gscPid)) break;
			else gscDivIndex++;
		}

		String realId = null;
		
		List<WebElement> elements = driver.findElements(By.className("x-tree3-node"));
		for (WebElement el : elements) {
			String id = el.getAttribute("id");
			if ((id != null) && (id.contains(gscPid))) {
				realId = id;
				break;
			}
		}		

		
		driver.findElement(By.xpath(WebConstants.Xpath.getPathToGscOption(realId))).click();
		helper.waitForElement(By.id(WebConstants.ID.terminateComponent), 3000).click();
		driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert)).click();
	}
	
	public void restartGSC(String hostName, GridServiceContainer gsc) throws InterruptedException {
		clickOnHost(hostName);
		String gscPid = "" + gsc.getVirtualMachine().getDetails().getPid();
		int gscDivIndex = 2;
		while (true) {
			if (helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToRowNumber(gscDivIndex)))
				.contains(gscPid)) break;
			else gscDivIndex++;
		}

		String realId = null;
		
		List<WebElement> elements = driver.findElements(By.className("x-tree3-node"));
		for (WebElement el : elements) {
			String id = helper.retrieveAttribute(el, "id");
			if ((id != null) && (id.contains(gscPid))) {
				realId = id;
				break;
			}
		}		
		
		driver.findElement(By.xpath(WebConstants.Xpath.getPathToGscOption(realId))).click();
		helper.waitForElement(By.id(WebConstants.ID.restartComponent), 3000).click();
		driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert)).click();
	}
	
	public boolean isGSCPresent(GridServiceContainer gsc) {
		String processPid = "" + gsc.getVirtualMachine().getDetails().getPid();
		return selenium.isTextPresent(processPid);
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
		
		String hostsTreePrefix = "hosts_tree_grid_host_";
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
