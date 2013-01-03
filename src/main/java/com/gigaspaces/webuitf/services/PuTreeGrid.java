package com.gigaspaces.webuitf.services;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openspaces.admin.pu.DeploymentStatus;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

/**
 * represents the Processing Unit Tree Grid found in the web-ui under the services tab.
 * @author elip
 *
 */
public class PuTreeGrid {
	
	private Selenium selenium;
	private WebDriver driver;
	
	private AjaxUtils helper;
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	public PuTreeGrid(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.helper = new AjaxUtils(driver);
	}
	
	public static PuTreeGrid getInstance(Selenium selenium, WebDriver driver) {
		return new PuTreeGrid(selenium, driver);
	}
	
	@SuppressWarnings("unused")
	public WebUIProcessingUnit getProcessingUnit(String name) {
		
		Exception exception = null;
		String xPath;
		WebElement pu;
		int i = 1;
		WebUIProcessingUnit webUIProcessingUnit = null;
		
		while (exception == null) {
			
			try {
				xPath = WebConstants.Xpath.getPathToProcessingUnitByIndex(i);
				pu = driver.findElement(By.xpath(xPath));
				webUIProcessingUnit = new WebUIProcessingUnit(xPath);
				if (webUIProcessingUnit.getName().equals(name)) {
					break;
				}
				Thread.sleep(1000);
				i++;
				webUIProcessingUnit = null;
			}
			catch (NoSuchElementException e) {
				exception = e;
			}
			catch (SeleniumException e) {
				exception = e;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return webUIProcessingUnit;
	}
	
	public class WebUIProcessingUnit {
		
		private String xpath;
		private String name;

		
		public WebUIProcessingUnit(String xpath) {
			this.xpath = xpath;
			this.name = getNameFromUI();
		}
		
		public String getName() {
			return this.name;
		}
		public DeploymentStatus getStatus() {
			int seconds = 0;
			while (seconds < AjaxUtils.ajaxWaitingTime) {
				try {
					WebElement status = driver.findElement(By.xpath(xpath + WebConstants.Xpath.pathToPuStatus));
					String stat = status.getAttribute("title");
					if (stat.equals("compromised")) return DeploymentStatus.COMPROMISED;
					if (stat.equals("intact")) return DeploymentStatus.INTACT;
					if (stat.equals("broken")) return DeploymentStatus.BROKEN;
					else return DeploymentStatus.SCHEDULED;
				}
				catch (StaleElementReferenceException e) {
					logger.severe("Failed to discover element due to statistics update, retyring...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					seconds++;
				}
			}
			return null;
		}
		public String getType() {
			int seconds = 0;
			while (seconds < AjaxUtils.ajaxWaitingTime) {
				try {
					return selenium.getText(xpath + WebConstants.Xpath.pathToPuType);
				}
				catch (StaleElementReferenceException e) {
					logger.severe("Failed to discover element due to statistics update, retyring...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					seconds++;
				}
			}
			return null;
			
		}
		public Integer getActualInstances() {		
			int seconds = 0;
			while (seconds < AjaxUtils.ajaxWaitingTime) {
				try {
					return Integer.parseInt(selenium.getText(xpath + WebConstants.Xpath.pathToPuActualInstances));
				}
				catch (StaleElementReferenceException e) {
					logger.severe("Failed to discover element due to statistics update, retyring...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					seconds++;
				}
			}
			return null;
		}
		public Integer getPlannedInstances() {
			int seconds = 0;
			while (seconds < AjaxUtils.ajaxWaitingTime) {
				try {
					return Integer.parseInt(selenium.getText(xpath + WebConstants.Xpath.pathToPuPlannedInstances));
				}
				catch (StaleElementReferenceException e) {
					logger.severe("Failed to discover element due to statistics update, retyring...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					seconds++;
				}
			}
			return null;
		}
		
		private String getNameFromUI(){
			int seconds = 0;
			while (seconds < AjaxUtils.ajaxWaitingTime) {
				try {
					return selenium.getText(xpath + WebConstants.Xpath.pathToPuName);				}
				catch (StaleElementReferenceException e) {
					logger.severe("Failed to discover element due to statistics update, retyring...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					seconds++;
				}
			}
			return null;
		}
		
		
		/**
		 * undeploys a given processing unit by name
		 * @param processingUnitName
		 */
		public void undeploy() {
			
			int seconds = 0;
			while (seconds < AjaxUtils.ajaxWaitingTime) {
				try {
					logger.info("Trying to click pu options button");
					driver.findElement(By.xpath(this.xpath + WebConstants.Xpath.pathToPuOptionsButton)).click();
					logger.info("clicked pu options button");
					break;
				}
				catch (StaleElementReferenceException e) {
					if (selenium.isElementPresent("id=" + WebConstants.ID.undeployPuButton)) break;
					logger.severe("Failed to discover element due to statistics update, retyring...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					seconds++;
				}
			}
			
			helper.waitForElement(By.id(WebConstants.ID.undeployPuButton), 3000).click();
			driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert)).click();
		}
		
		/**
		 * restarts a pu instance
		 */
		public void restartPuInstance(int partition) {
			
			int timeout = 5;
			helper.clickWhenPossible(timeout, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToPuButton(this.name)));
			helper.clickWhenPossible(timeout, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToPartitionButton(this.name, partition)));
			helper.clickWhenPossible(timeout, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.getPathToPuInstanceButton(this.name, partition)));
			helper.clickWhenPossible(timeout, TimeUnit.SECONDS, By.id(WebConstants.ID.restartPuInstance));
			helper.clickWhenPossible(timeout, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.acceptAlert));
		}
		
		public boolean isPartitioned() {
			selenium.click(WebConstants.Xpath.getPathToPuButton(this.name));
			return selenium.isTextPresent(this.name + ".");		
		}

		public void expand() {
			
			int seconds = 0;
			while (seconds < AjaxUtils.ajaxWaitingTime) {
				try {
					selenium.click(WebConstants.Xpath.getPathToPuButton(this.name));
					break;
					}
				catch (StaleElementReferenceException e) {
					logger.severe("Failed to discover element due to statistics update, retyring...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					seconds++;
				}
			}		
		}
		
		public void collapseAll() {
			
			Exception exception = null;
			
			int i = 1;
			while (exception == null) {
				try {
					clickOnPartition(i);
					i++;
				}
				catch (SeleniumException e) {
					exception = e;
				}
			}
			this.expand();
			
		}
		
		public boolean isProcessingUnitInstancePresent(String processingUnitInstanceName) {
			
			Exception exception = null;
			
			this.expand();
			int i = 1;
			while (exception == null) {
				try {
					clickOnPartition(i);
					if (selenium.isTextPresent(processingUnitInstanceName)) {
						clickOnPartition(i);
						this.expand();
						return true;
					}
					clickOnPartition(i);
					i++;
				}
				catch (SeleniumException e) {
					exception = e;
				}
			}
			this.expand();
			return false;
			
		}
		
		public void clickOnPartition(int index) throws SeleniumException {
			
			int seconds = 0;
			while (seconds < AjaxUtils.ajaxWaitingTime) {
				try {
					selenium.click(WebConstants.Xpath.getPathToPartitionButton(this.name, index));
					break;
					}
				catch (StaleElementReferenceException e) {
					logger.severe("Failed to discover element due to statistics update, retyring...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					seconds++;
				}
				catch (SeleniumException e) {
					throw e;
				}
			}
			
		}
			
	}
	
}
