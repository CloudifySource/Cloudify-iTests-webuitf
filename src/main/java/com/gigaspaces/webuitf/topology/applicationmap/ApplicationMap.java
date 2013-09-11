package com.gigaspaces.webuitf.topology.applicationmap;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;

public class ApplicationMap {
	
	private WebDriver driver;
//	private Selenium selenium;
	private AjaxUtils helper;

	public static final String CONN_STATUS_OK = "ok";
	public static final String CONN_STATUS_WARN = "warn";
	public static final String CONN_STATUS_CRITICAL = "critical";
	public static final String CONN_STATUS_EMPTY = "empty";
	
	private static final String PATH_TAG = "path";
	private static final String DATA_SOURCE_ATTR = "data-source";
	private static final String DATA_TARGET_ATTR = "data-target";

	public ApplicationMap(WebDriver driver, Selenium selenium) {
		this.driver = driver;
//		this.selenium = selenium;
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
		
		List<WebElement> pathElements = driver.findElements( 
				By.tagName( PATH_TAG ).
				cssSelector( PATH_TAG + "[" + DATA_SOURCE_ATTR + "=" + sourceName+ "]").
				cssSelector( PATH_TAG + "[" + DATA_TARGET_ATTR + "=" + targetName + "]") );
		
		return !pathElements.isEmpty();
	}
	
	public Collection<String> getConnectorTargets( String sourceName ){
		List<WebElement> pathElements = driver.findElements( 
				By.tagName( PATH_TAG ).
				cssSelector( PATH_TAG + "[" + DATA_SOURCE_ATTR + "=" + sourceName+ "]") );

		List<String> targetsName = new ArrayList<String>();
		for( WebElement pathElement : pathElements ){
			String target = pathElement.getAttribute( DATA_TARGET_ATTR );
			targetsName.add( target );
		}
		
		return targetsName;
	}	

	public Collection<Connector> getConnectors( String nodeName ){
		List<WebElement> targetElements = driver.findElements( 
				By.cssSelector( PATH_TAG + "[" + DATA_TARGET_ATTR + "=" + nodeName+ "]" ) );
		
		List<WebElement> sourceElements = driver.findElements( 
				By.cssSelector( PATH_TAG + "[" + DATA_SOURCE_ATTR + "=" + nodeName+ "]" ) );		

		List<WebElement> allElements = new ArrayList<WebElement>(targetElements);
		allElements.addAll(sourceElements);
		
		List<Connector> connectors = new ArrayList<Connector>();
		for( WebElement pathElement : allElements ){
			String target = pathElement.getAttribute( DATA_TARGET_ATTR );
			String source = pathElement.getAttribute( DATA_SOURCE_ATTR );
			WebElement parentElement = pathElement.findElement(By.xpath("..") );
			WebElement img = parentElement.findElement( By.tagName( "img" ).className( "status" ) );
			String status = img.getAttribute( "conn-status" );
			connectors.add( new Connector( source, target, status ) );
		}
		
		return connectors;
	}	
	
	public ApplicationNode getApplicationNode(String name) {
		ApplicationNode appNode = new ApplicationNode(name, driver);
		if (appNode.getName() != null) {
			return appNode;
		}
		return null;
	}

	public Collection<String> getConnectorSources( String targetName ) {
		List<WebElement> pathElements = driver.findElements( 
				By.tagName( PATH_TAG ).
				cssSelector( PATH_TAG + "[" + DATA_TARGET_ATTR + "=" + targetName + "]") );

		List<String> sourcesName = new ArrayList<String>();
		for( WebElement pathElement : pathElements ){
			String source = pathElement.getAttribute( DATA_SOURCE_ATTR );
			sourcesName.add( source );
		}
		
		return sourcesName;
	}
}
