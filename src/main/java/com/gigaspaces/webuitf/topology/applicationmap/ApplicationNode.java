package com.gigaspaces.webuitf.topology.applicationmap;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openspaces.admin.pu.DeploymentStatus;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.services.RenderedWebUIElement;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.JsExecutor;

public class ApplicationNode implements RenderedWebUIElement {

	private String name;
	private WebDriver driver;

	private AjaxUtils helper;
	private WebElement element;

	public ApplicationNode(WebElement element, String name,  WebDriver driver) {
		this.driver = driver;
		this.name = name;
		this.element = element;
		this.helper = new AjaxUtils(driver);
	}


	public String getName() {
		return name;
	}

	/*
	public List<Connector> getTargets() {

		List<Connector> connectors = new ArrayList<Connector>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Long length = (Long)js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges.length");
			for (int i = 0 ; i < length ; i++) {
				String sourceNodeName = (String) js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges[" + i + "].source.id");
				if (sourceNodeName.equals(name)) {
					String targetNodeName = (String) js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges[" + i + "].target.id");
					String status = (String) js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges[" + i + "].style.status");
					connectors.add(new Connector(new ApplicationNode(sourceNodeName, driver), new ApplicationNode(targetNodeName,driver), status));
				}
			}
			return connectors;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}

	public List<Connector> getTargeted() {

		List<Connector> connectors = new ArrayList<Connector>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Long length = (Long)js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges.length");
			for (int i = 0 ; i < length ; i++) {
				String sourceNodeName = (String) js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges[" + i + "].source.id");
				String targetNodeName = (String) js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges[" + i + "].target.id");
				if (targetNodeName.equals(name)) {
					String status = (String) js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges[" + i + "].style.status");
					connectors.add(new Connector(new ApplicationNode(sourceNodeName,driver), new ApplicationNode(targetNodeName,driver), status));
				}
			}
			return connectors;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}

	public List<Connector> getConnectors() {

		List<Connector> connectors = new ArrayList<Connector>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Long length = (Long)js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges.length");
			for (int i = 0 ; i < length ; i++) {
				String sourceNodeName = (String) js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges[" + i + "].source.id");
				String targetNodeName = (String) js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges[" + i + "].target.id");
				String status = (String) js.executeScript("return this." + SharedContextConstants.NS_GRAPH_APPLICATION_MAP + ".nodes[" + '"' + name + '"' + "].edges[" + i + "].style.status");
				connectors.add(new Connector(new ApplicationNode(sourceNodeName,driver), new ApplicationNode(targetNodeName,driver), status));
			}
			return connectors;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}*/

	@SuppressWarnings("unchecked")
	public List<String> getComponents() {
        return JsExecutor.getApplicationMapNodeProp(driver, name, "components");
    }


	public Long getxPosition() {
        return JsExecutor.getApplicationMapNodeProp(driver, name, "layoutPosX");
	}

	public Long getyPosition() {
        return JsExecutor.getApplicationMapNodeProp(driver, name, "layoutPosY");
	}

	public String getNodeColor() {
        return JsExecutor.getApplicationMapNodeProp(driver, name, "color");
	}

	public String getNodeType() {
        return JsExecutor.getApplicationMapNodeProp(driver, name, "type");
	}

	public String getPuType() {
        return JsExecutor.getApplicationMapNodeProp(driver, name, "puType");
	}

	public DeploymentStatus getStatus() {

		String status = "";
		List<WebElement> elements = driver.findElements(
				By.tagName( ApplicationMap.G_TAG ).
				className( ApplicationMap.NODE_TYPE_PU_CLASS ) );

		WebElement puElement  = null;
		if( elements != null ){
			for( WebElement element : elements ){
				String puName = element.getAttribute( ApplicationMap.PU_NAME_ATTR );
				if( puName != null && puName.equals( name ) ){
					puElement = element;
					break;
				}
			}
		}

		if( puElement != null ){
			WebElement statusElement = puElement.findElement( By.className( ApplicationMap.STATUS_CLASS ) );
			if( statusElement != null ){
				status = statusElement.getAttribute( ApplicationMap.NODE_STATUS_ATTR );
			}
		}

        if (WebConstants.ID.nodeStatusOk.equals(status)){
        	return DeploymentStatus.INTACT;
        }
        if (WebConstants.ID.nodeStatusWarning.equals(status)){
        	return DeploymentStatus.COMPROMISED;
        }
        if (WebConstants.ID.nodeStatusBroken.equals(status)){
        	return DeploymentStatus.BROKEN;
        }

        return DeploymentStatus.SCHEDULED;
    }

	public Long getPlannedInstances() {
        return JsExecutor.getApplicationMapNodeProp(driver, name, "plannedInstances");
	}

	public Long getActualInstances() {
        return JsExecutor.getApplicationMapNodeProp(driver, name, "actualInstances");
	}

	private static boolean isSelected( WebElement puElement ) {
		if( puElement == null ){
			throw new IllegalStateException( "WebElement cannot be null" );
		}
		//return JsScripts.getApplicationMapNodeProp(driver, name, "selected");

		//check strokeWidth for detecting selection
		WebElement rectElement = puElement.findElement( By.className( "puRect" ) );
		String styleAttribute = rectElement.getAttribute( "style" );
		final String STROKE_WIDTH_ATTR = "stroke-width:";
		int strokeWidthIndex = styleAttribute.indexOf( STROKE_WIDTH_ATTR );
		float strokeWidth = -1;
		if( strokeWidthIndex > 0 ){
			int strokeWidthEndIndex = styleAttribute.indexOf( "px;", strokeWidthIndex );
			if( strokeWidthEndIndex < 0 ){
				strokeWidthEndIndex = styleAttribute.indexOf( ";", strokeWidthIndex );
			}
			String strokeWidthStr = styleAttribute.substring( strokeWidthIndex + STROKE_WIDTH_ATTR.length(), strokeWidthEndIndex );
			strokeWidth = Float.parseFloat( strokeWidthStr.trim() );
		}

		return strokeWidth > 2;
	}

	public boolean isSelected(){
		return isSelected( element );
	}

    public void select() {

        WebElement requiredPuNodeElement = element;//retrievePuNode( name );

        if ( requiredPuNodeElement == null ) {
            throw new IllegalStateException( "Processing Unit [" + name + "] was not found in Application Map" );
        }

        boolean selected = isSelected( requiredPuNodeElement );
        if (selected) {
            return;
        }

        requiredPuNodeElement.click();
	}

	public void clickOnActions() {
		WebElement actionsButton = driver.findElement(By.id(WebConstants.ID.getActionToolBoxId(this.name)));
		actionsButton.click();
	}
	
	public void clickOnInfo() {
		WebElement infoButton = driver.findElement(By.id(WebConstants.ID.getInfoToolBoxId(this.name)));
		infoButton.click();
	}
	
	public void isActionMenuVisible() {
		WebElement menu = helper.waitForElement(By.className("x-menu-list"), 3);
		if (!menu.isDisplayed()) {
			throw new IllegalStateException("Action Menu Item is not visible");
		}
		List<WebElement> items = menu.findElements(By.className("x-menu-list-item"));
		if (items.size() != 2) {
			throw new IllegalStateException("Action Menu Item list size is not 2");
		}
	}
	
	public void isActionMenuNotVisible() {
		try {
			driver.findElement(By.className("x-menu-list"));
			throw new IllegalStateException("Action Menu Item is still visible");
		}
		catch (WebDriverException e) {
			return;
		}
	}
	
	public void undeploy() {
		clickOnActions();
		isActionMenuVisible();
		
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e1) {
		}

		helper.clickWhenPossible( 20, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.pathToUndeployNode) );
		
		helper.clickWhenPossible( 10, TimeUnit.SECONDS, By.xpath(WebConstants.Xpath.acceptAlert) );
		
		isActionMenuNotVisible();
	}
	
	public void showInfo() {
		clickOnInfo();
	}

	public boolean isDisplayed() {
		
		return ( element == null ) ? false : element.isDisplayed();
	}
}