package com.gigaspaces.webuitf.topology.applicationmap;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;

public class ApplicationMap {
	
	private WebDriver driver;

	public static final String CONN_STATUS_OK = "conn-status-ok";
	public static final String CONN_STATUS_WARN = "conn-status-warn";
	public static final String CONN_STATUS_CRITICAL = "conn-status-critical";
	public static final String CONN_STATUS_EMPTY = "conn-status-empty";

	public ApplicationMap(WebDriver driver) {
		this.driver = driver;
	}
	
	public enum DumpType {
		JVM_THREAD,NETWORK,LOG,PU,JVM_HEAP,
	}
	
	public enum ServiceTypes {
		UNDEFINED,
		LOAD_BALANCER,
		WEB_SERVER,
		SECURITY_SERVER,
		APP_SERVER,
		ESB_SERVER,
		MESSAGE_BUS,
		DATABASE,
		NOSQL_DB;
	}
	
	public void selectApplication(final String applicationName) {
		
		RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {			
			public boolean getCondition() {
				WebElement arrowDown = driver.findElement(By.id(WebConstants.ID.topologyCombobox)).findElement(By.className("icon"));
				arrowDown.click();
				List<WebElement> allApps = driver.findElement(By.id(WebConstants.ID.topologyCombobox)).findElements(By.className("visible"));
				WebElement app = null;
				for (WebElement e : allApps) {
					if (e.getText().equals(applicationName)) {
						app = e;
						break;
					}
				}
				if ((app != null) && app.isDisplayed()) {
					app.click();
					return true;
				}
				else {
					return false;
				}
			}
		};

		AjaxUtils.repetitiveAssertTrue("Application is not present in the applications menu panel", condition,10000);
	}
	
	public void deselectAllNodes() {
		WebElement graphCanvas = driver.findElement(By.id("graph-canvas"));
		graphCanvas.click();
	}

	public ApplicationNode getApplicationNode(String name) {
		ApplicationNode appNode = new ApplicationNode(name, driver);
		if (appNode.getName() != null) {
			return appNode;
		}
		return null;
	}
}
