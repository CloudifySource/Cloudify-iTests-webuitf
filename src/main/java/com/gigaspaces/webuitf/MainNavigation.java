package com.gigaspaces.webuitf;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.dashboard.DashboardTab;
import com.gigaspaces.webuitf.datagrid.DataGridTab;
import com.gigaspaces.webuitf.services.ServicesTab;
import com.gigaspaces.webuitf.topology.TopologyTab;
import com.thoughtworks.selenium.Selenium;

public class MainNavigation {
	
	protected Selenium selenium;
	protected WebDriver driver;
	
	public MainNavigation() {}
	
	public MainNavigation(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
	}
	
	public LoginPage logout() throws InterruptedException {
		selenium.click(WebConstants.ID.logoutButton);
		selenium.click(WebConstants.Xpath.acceptAlert);
		Thread.sleep(1000);
		return new LoginPage(selenium, driver);
	}
	
	public ServicesTab switchToServices() {
		selenium.click(WebConstants.Xpath.servicesButton);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (NoSuchElementException e) {
			return null;
		}
		return new ServicesTab(selenium, driver);
	}
	
	public DashboardTab switchToDashboard() {
		selenium.click(WebConstants.Xpath.dashBoardButton);
		try {
			Thread.sleep(2000);
			} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (NoSuchElementException e) {
			return null;
		}
		return new DashboardTab(selenium, driver);
	}
	
	public TopologyTab switchToTopology() {
		selenium.click(WebConstants.Xpath.topologyButton);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (NoSuchElementException e) {
			return null;
		}
		return new TopologyTab(selenium, driver);
	}

	public DataGridTab switchToDataGrid() {
		return switchToDataGrid( true );
	}
	
	public DataGridTab switchToDataGrid( boolean wait ) {
		selenium.click(WebConstants.Xpath.consoleButton);
		if( wait ){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			catch (NoSuchElementException e) {
				return null;
			}
		}
		return new DataGridTab(driver);
	}	
	
}
