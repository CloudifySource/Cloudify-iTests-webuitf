package com.gigaspaces.webuitf.topology.physicalpanel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.dashboard.ServicesGrid.Icon;
import com.gigaspaces.webuitf.topology.physicalpanel.PhysicalPanel.OS;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.LogUtils;

/**
 * represents a single row in the physical panel hosts table
 * @author elip
 *
 */
public class HostData {

	private String name;
	private WebDriver driver;
	
	private AjaxUtils helper = new AjaxUtils();
	
	public HostData(String hostName, WebDriver driver) {
		
		this.driver = driver;
		helper.setDriver(driver);
		try {
			String id = WebConstants.ID.getHostId(hostName);
			driver.findElement(By.id(id));
			this.name = hostName;
		}
		catch (NoSuchElementException e) {
		}
		catch (WebDriverException e) {
		}

	}

	public String getName() {
		return name;
	}

	public Icon getIcon() {

		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				String id = WebConstants.ID.getHostId(name);
				WebElement hostElement = driver.findElement(By.id(id));
				WebElement icon = hostElement.findElement(By.className("x-grid3-td-status")).findElement(By.tagName("span"));
				String type = icon.getAttribute("class").trim();
				if (type.equals(WebConstants.ID.okIcon)) return Icon.OK;
				if (type.equals(WebConstants.ID.criticalIcon)) return Icon.CRITICAL;
				if (type.equals(WebConstants.ID.warningIcon)) return Icon.ALERT;
				if (type.equals(WebConstants.ID.naIcon)) return Icon.NA;
				return null;
			}
			catch (StaleElementReferenceException e) {
				LogUtils.log("Failed to discover element due to statistics update, retyring...");
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
	 * 
	 * @return the number of cores present on the host
	 * @throws InterruptedException 
	 */
	public Integer getNumberOfCores() {

		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				String id = WebConstants.ID.getHostId(name);
				WebElement hostElement = driver.findElement(By.id(id));
				WebElement cores = hostElement.findElement(By.className("x-grid3-td-corecpus"));
				String coreCount = cores.getText();
				if (coreCount != " ") {
					return Integer.parseInt(coreCount);
				}
				else return 0;
			}
			catch (StaleElementReferenceException e) {
				LogUtils.log("Failed to discover element due to statistics update, retyring...");
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
	 * 
	 * @return The specific OS the hosts operates under
	 * @throws InterruptedException 
	 */
	public OS getOS() {
		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				String id = WebConstants.ID.getHostId(name);
				WebElement hostElement = driver.findElement(By.id(id));
				WebElement os = hostElement.findElement(By.className("x-grid3-td-os_type")).findElement(By.tagName("span"));
				String osType = os.getAttribute("class").trim();
				if (osType.equals(WebConstants.ClassNames.win32OS)) return OS.WINDOWS32;
				else return OS.LINUX;
			}
			catch (StaleElementReferenceException e) {
				LogUtils.log("Failed to discover element due to statistics update, retyring...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				seconds++;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return all ProcessingUnitInstances deployed on the host. 
	 * this is represented as a list of {@link test.webui.objects.topology.HostData.PuIBox}
	 * @throws InterruptedException 
	 */
	public PuIBoxes getPUIs() {
		return new PuIBoxes(name, driver);

	}
	
	public MorePopUp getMorePopup() {
		return new MorePopUp(name, driver);
	}

	/**
	 * 
	 * @return number of GridServiceContainer running on the host
	 * @throws InterruptedException 
	 */
	public Integer getGSCCount() {

		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				String id = WebConstants.ID.getHostId(name);
				WebElement hostElement = driver.findElement(By.id(id));
				WebElement gsc = hostElement.findElement(By.className("x-grid3-td-gsc_indication"));
				String gscCount = gsc.getText();
				if (!gscCount.equals(" ")) {
					return Integer.parseInt(gscCount);
				}
				else return 0;
			}
			catch (StaleElementReferenceException e) {
				LogUtils.log("Failed to discover element due to statistics update, retyring...");
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

	public Integer getGSACount() {
		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				String id = WebConstants.ID.getHostId(name);
				WebElement hostElement = driver.findElement(By.id(id));
				WebElement gsa = hostElement.findElement(By.className("x-grid3-td-gsa_indication"));
				String gsaCount = gsa.getText();
				if (!gsaCount.equals(" ")) {
					return Integer.parseInt(gsaCount);
				}
				else return 0;
			}
			catch (StaleElementReferenceException e) {
				LogUtils.log("Failed to discover element due to statistics update, retyring...");
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
	 * 
	 * @return the number GridServiceManagers running on the host
	 * @throws InterruptedException 
	 */
	public Integer getGSMCount() {
		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				String id = WebConstants.ID.getHostId(name);
				WebElement hostElement = driver.findElement(By.id(id));
				WebElement gsm = hostElement.findElement(By.className("x-grid3-td-gsm_indication"));
				String gsmCount = gsm.getText();
				if (!gsmCount.equals(" ")) {
					return Integer.parseInt(gsmCount);
				}
				else return 0;
			}

			catch (StaleElementReferenceException e) {
				LogUtils.log("Failed to discover element due to statistics update, retyring...");
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
	
	public void selectComparisonCharts() {
		String id = WebConstants.ID.getHostId(name);
		helper.clickWhenPossible(10, TimeUnit.SECONDS, By.id(id), By.className("x-grid3-cc-chart_selection"));			
	}	
}


