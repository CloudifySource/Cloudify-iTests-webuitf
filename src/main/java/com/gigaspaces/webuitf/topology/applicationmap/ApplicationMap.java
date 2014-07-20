package com.gigaspaces.webuitf.topology.applicationmap;


import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class ApplicationMap {
	
	private WebDriver driver;
	private AjaxUtils helper;

	public static final String CONN_STATUS_OK = "ok";
	public static final String CONN_STATUS_WARN = "warn";
	public static final String CONN_STATUS_CRITICAL = "critical";
	public static final String CONN_STATUS_EMPTY = "empty";
	
	public static final String PATH_TAG = "path";
	public static final String TEXT_TAG = "text";
	public static final String G_TAG = "g";
	public static final String DATA_SOURCE_ATTR = "data-source";
	public static final String DATA_TARGET_ATTR = "data-target";
	public static final String NODE_TYPE_PU_CLASS = "nodetype-pu";
	public static final String STATUS_CLASS = "status";
	public static final String NODE_STATUS_ATTR = "node-status";
	
	public static final String TYPE_INDICATION_COMPONENTS_ATTR = "type-indication-components";
	public static final String PU_NAME_ATTR = "pu-name";

    protected Logger logger = Logger.getLogger(this.getClass().getName());

	public ApplicationMap(WebDriver driver, Selenium selenium) {
		this.driver = driver;
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
        WebElement graphCanvas = retrieveGraphCanvas();
        graphCanvas.click();
    }

    private WebElement retrieveGraphCanvas() {
        // we avoid using By.id, as it will fetch a synthetic element, and subsequent actions
        // (e.g. element.click) will fail with a javascript exception.
        // see the reported issue here: https://code.google.com/p/chromedriver/issues/detail?id=427
        return helper.waitForElement(By.cssSelector("#" + WebConstants.ID.graphApplicationMap + " svg"), 2);
    }


    public boolean hasConnector( String sourceName, String targetName ){
		
		List<WebElement> pathElements = driver.findElements( 
				By.tagName( PATH_TAG ).
				cssSelector( PATH_TAG + "[" + DATA_SOURCE_ATTR + "=\"" + sourceName+ "\"]").
				cssSelector( PATH_TAG + "[" + DATA_TARGET_ATTR + "=\"" + targetName + "\"]") );
		
		return !pathElements.isEmpty();
	}
	
	
	public Collection<String> getConnectorTargets( String sourceName ){
		List<WebElement> pathElements = driver.findElements( 
				By.tagName( PATH_TAG ).
				cssSelector( PATH_TAG + "[" + DATA_SOURCE_ATTR + "=\"" + sourceName+ "\"]") );

		List<String> targetsName = new ArrayList<String>();
		for( WebElement pathElement : pathElements ){
			String target = pathElement.getAttribute( DATA_TARGET_ATTR );
			targetsName.add( target );
		}
		
		return targetsName;
	}
	
	public Collection<String> getConnectorSources( String targetName ) {
		List<WebElement> pathElements = driver.findElements( 
				By.tagName( PATH_TAG ).
				cssSelector( PATH_TAG + "[" + DATA_TARGET_ATTR + "=\"" + targetName + "\"]") );

		List<String> sourcesName = new ArrayList<String>();
		for( WebElement pathElement : pathElements ){
			String source = pathElement.getAttribute( DATA_SOURCE_ATTR );
			sourcesName.add( source );
		}
		
		return sourcesName;
	}	
/*
	public Collection<Connector> getConnectorsNew( String nodeName ){
		List<WebElement> targetElements = driver.findElements( 
				By.cssSelector( PATH_TAG + "[" + DATA_TARGET_ATTR + "=\"" + nodeName+ "\"]" ) );
		
		List<WebElement> sourceElements = driver.findElements( 
				By.cssSelector( PATH_TAG + "[" + DATA_SOURCE_ATTR + "=\"" + nodeName+ "\"]" ) );

        List<Connector> connectors = new ArrayList<Connector>();

        List<WebElement> allEdgeElements = driver.findElements( By.className( "edge" ).tagName("g") );
        for( WebElement edgeElement : allEdgeElements ){
            try {
                logger.info("edgeElement tag name:" + edgeElement.getTagName());
                WebElement pathElement = edgeElement.findElement(By.tagName("path"));

                logger.info("pathElement:" + pathElement);
                if (pathElement != null) {
                    String targetAttrVal = pathElement.getAttribute(DATA_TARGET_ATTR);
                    String sourceAttrVal = pathElement.getAttribute(DATA_SOURCE_ATTR);
                    logger.info("pathElement, target=" + targetAttrVal + ", source=" + sourceAttrVal);
                    if( ( targetAttrVal != null && targetAttrVal.equals( nodeName ) ) ||
                         ( sourceAttrVal != null && sourceAttrVal.equals( nodeName ) ) ) {

                        //WebElement parentElement = pathElement.findElement(By.xpath("..") );
                        WebElement img = edgeElement.findElement( By.tagName( "img" ).className( STATUS_CLASS ) );
                        String status = img == null ? "n/a" : img.getAttribute( "conn-status" );
                        connectors.add( new Connector( sourceAttrVal, targetAttrVal, status ) );
                    }
                }
            }
            catch( Exception e ){
                //logger.log(Level.WARNING, e.toString(), e );
            }
        }

		return connectors;
	}
*/
    public Collection<Connector> getConnectors( String nodeName ){

        List<WebElement> targetElements = driver.findElements(
                By.cssSelector( PATH_TAG + "[" + DATA_TARGET_ATTR + "=\"" + nodeName+ "\"]" ) );

        List<WebElement> sourceElements = driver.findElements(
                By.cssSelector( PATH_TAG + "[" + DATA_SOURCE_ATTR + "=\"" + nodeName+ "\"]" ) );

        List<WebElement> allElements = new ArrayList<WebElement>(targetElements);
        allElements.addAll(sourceElements);

        List<Connector> connectors = new ArrayList<Connector>();
        for( WebElement pathElement : allElements ){
            String target = pathElement.getAttribute( DATA_TARGET_ATTR );
            String source = pathElement.getAttribute( DATA_SOURCE_ATTR );
            WebElement parentElement = pathElement.findElement(By.xpath("..") );
            WebElement img = parentElement.findElement( By.tagName( "img" ).className( STATUS_CLASS ) );
            String status = img.getAttribute( "conn-status" );
            connectors.add( new Connector( source, target, status ) );
        }

        return connectors;
    }


    public ApplicationNode getApplicationNode( String name ){
		
		List<WebElement> elements = driver.findElements( 
				By.tagName( G_TAG ).
				className( NODE_TYPE_PU_CLASS ) );
		
  		WebElement resultElement  = null;
		if( elements != null ){
			for( WebElement element : elements ){
				String puNameAttr = element.getAttribute( PU_NAME_ATTR );
				if( name.equals( puNameAttr )){
					resultElement = element;
					break;
				}
			}
		}
		
		return resultElement == null ? null : new ApplicationNode( resultElement, name, driver );
	}
	
	public String getApplicationNodeStatus( String name ){
		List<WebElement> elements = driver.findElements( 
				By.tagName( G_TAG ).
				className( NODE_TYPE_PU_CLASS ) );
		
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
			WebElement statusElement = puElement.findElement( By.className( STATUS_CLASS ) );
			if( statusElement != null ){
				return statusElement.getAttribute( NODE_STATUS_ATTR );
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
	
	public List<String> getTypeIndicationComponents(String puName ) {
		WebElement element = driver.findElement( 
				By.cssSelector( "g[" + PU_NAME_ATTR + "=\"" + puName + "\"]" ).
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
