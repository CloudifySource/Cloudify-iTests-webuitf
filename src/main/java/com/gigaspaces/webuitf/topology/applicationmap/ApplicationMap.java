package com.gigaspaces.webuitf.topology.applicationmap;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;

public class ApplicationMap {
	
	private WebDriver driver;
	private Selenium selenium;
	private AjaxUtils helper;

	public static final String CONN_STATUS_OK = "conn-status-ok";
	public static final String CONN_STATUS_WARN = "conn-status-warn";
	public static final String CONN_STATUS_CRITICAL = "conn-status-critical";
	public static final String CONN_STATUS_EMPTY = "conn-status-empty";
	
	private final String PATH_TAG = "path";

	public ApplicationMap(WebDriver driver, Selenium selenium) {
		this.driver = driver;
		this.selenium = selenium;
		this.helper = new AjaxUtils(driver);
	}
	
	public enum DumpType {
		JVM_THREAD,NETWORK,LOG,PU,JVM_HEAP;
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
	
	public void deselectAllNodes() {
		WebElement graphCanvas = driver.findElement(By.id(WebConstants.ID.graphApplicationMap));
		graphCanvas.click();
	}
	
	public boolean hasConnector( String sourceName, String targetName ){
		WebElement result = null;
		List<WebElement> pathElements = driver.findElements( 
				By.tagName( PATH_TAG ).
				cssSelector( PATH_TAG + "[data-source=" + sourceName+ "]").
				cssSelector( PATH_TAG + "[data-target=" + targetName + "]") );
		
		return !pathElements.isEmpty();
	}

	public ApplicationNode getApplicationNode(String name) {
		ApplicationNode appNode = new ApplicationNode(name, driver);
		if (appNode.getName() != null) {
			return appNode;
		}
		return null;
	}
}
