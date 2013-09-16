package com.gigaspaces.webuitf.topology.applicationmap;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

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
	private static final String TEXT_TAG = "text";	
	private static final String DATA_SOURCE_ATTR = "data-source";
	private static final String DATA_TARGET_ATTR = "data-target";
	
	private static final String TYPE_INDICATION_COMPONENTS_ATTR = "type-indication-components";
	private static final String PU_NAME_ATTR = "pu-name";
	
	private static final String PU_NAME_TEXT_CLASS = "puNameText";

	public ApplicationMap(WebDriver driver, Selenium selenium) {
		this.driver = driver;
//		this.selenium = selenium;
		this.helper = new AjaxUtils(driver);
	}
	
	public enum DumpType {
		JVM_THREAD,NETWORK,LOG,PU,JVM_HEAP;
	}
	
/*	public enum ServiceTypes {
		UNDEFINED,
		LOAD_BALANCER,
		WEB_SERVER,
		SECURITY_SERVER,
		APP_SERVER,
		ESB_SERVER,
		MESSAGE_BUS,
		DATABASE,
		NOSQL_DB;
	}*/
	
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

	public ApplicationNode getApplicationNode( String name ){
		List<WebElement> elements = driver.findElements( 
				By.tagName( TEXT_TAG ).
				className( PU_NAME_TEXT_CLASS ) );
		WebElement resultElement  = null;
		if( elements != null ){
			for( WebElement element : elements ){
				if( element.getText()!= null && element.getText().equals( name ) ){
					resultElement = element;
					break;
				}
			}
		}
		
		return resultElement == null ? null : new ApplicationNode( resultElement, name, driver );
	}
	
	public String getApplicationNodeStatus( String name ){
		List<WebElement> elements = driver.findElements( 
				By.tagName( "g" ).
				className( "nodetype-pu" ) );
		
		WebElement puElement  = null;
		if( elements != null ){
			for( WebElement element : elements ){
				String puName = element.getAttribute( PU_NAME_ATTR );
				if( puName != null && puName.equals( name ) ){
					puElement = element;
					break;
				}
			}
		}
		
		if( puElement != null ){
			WebElement statusElement = puElement.findElement( By.className( "status" ) );
			if( statusElement != null ){
				return statusElement.getAttribute( "node-status" );
			}
		}
		
		return null;
	}	
	
/*	public ApplicationNode getApplicationNode( String name ) {
		
		ApplicationNode appNode = new ApplicationNode(name, driver);
		if (appNode.getName() != null) {
			return appNode;
		}
		return null;
	}

	public ApplicationNode getApplicationNode(String name, String simpleName ) {
		ApplicationNode appNode = new ApplicationNode(name, simpleName, driver);
		if (appNode.getName() != null) {
			return appNode;
		}
		return null;
	}*/
	
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

	public List<String> getTypeIndicationComponents(String puName ) {
		WebElement element = driver.findElement( 
				By.cssSelector( "g[" + PU_NAME_ATTR + "=" + puName + "]" ).
				className( "components" ) );

		List<String> resultList = new ArrayList<String>();
		if( element != null ){
			String attribute = element.getAttribute( TYPE_INDICATION_COMPONENTS_ATTR );
			if( attribute != null && !attribute.trim().isEmpty() ){
				if( !attribute.contains( "," ) ){
					resultList.add( attribute );
				}
				else{
					StringTokenizer strTokenizer = new StringTokenizer( attribute, "," );
					while( strTokenizer.hasMoreTokens() ){
						resultList.add( strTokenizer.nextToken() );
					}
				}
			}
		}
		return resultList;
	}
}
