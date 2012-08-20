package com.gigaspaces.webuitf.topology.applicationmap;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openspaces.admin.pu.DeploymentStatus;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.services.RenderedWebUIElement;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class ApplicationNode implements RenderedWebUIElement {
	
	private String name;
	private WebDriver driver;
	
	private AjaxUtils helper;
	
	public ApplicationNode(String name, WebDriver driver) {
		this.driver = driver;
		this.name = getNameFromUI(name);
		this.helper = new AjaxUtils(driver);
	}
	
	public String getName() {
		return name;
	}
	
	public List<Connector> getTargets() {
		
		List<Connector> connectors = new ArrayList<Connector>();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		try {
			Long length = (Long)js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges.length");
			for (int i = 0 ; i < length ; i++) {
				String sourceNodeName = (String) js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges[" + i + "].source.id");
				if (sourceNodeName.equals(name)) {
					String targetNodeName = (String) js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges[" + i + "].target.id");
					String status = (String) js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges[" + i + "].style.status");
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
			Long length = (Long)js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges.length");
			for (int i = 0 ; i < length ; i++) {
				String sourceNodeName = (String) js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges[" + i + "].source.id");
				String targetNodeName = (String) js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges[" + i + "].target.id");
				if (targetNodeName.equals(name)) {
					String status = (String) js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges[" + i + "].style.status");
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
			Long length = (Long)js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges.length");
			for (int i = 0 ; i < length ; i++) {
				String sourceNodeName = (String) js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges[" + i + "].source.id");
				String targetNodeName = (String) js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges[" + i + "].target.id");
				String status = (String) js.executeScript("return this.dGraphAppMap.nodes[" + '"' + name + '"' + "].edges[" + i + "].style.status");
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
	}

	@SuppressWarnings("unchecked")
	public List<String> getComponents() {
		
		List<String> comps = new ArrayList<String>();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			comps = (List<String>)js.executeScript("return this.dGraphAppMap.nodes[" + '"' 
					+ name + '"' + "].components");
			return comps;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
		
	}

	public Long getxPosition() {
		
		Long xPosition = null;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			xPosition = (Long)js.executeScript("return this.dGraphAppMap.nodes[" + '"' 
					+ name + '"' + "].layoutPosX");
			return xPosition;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}
	
	public Long getyPosition() {
		
		Long xPosition = null;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			xPosition = (Long)js.executeScript("return this.dGraphAppMap.nodes[" + '"' 
					+ name + '"' + "].layoutPosY");
			return xPosition;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}

	public String getNodeColor() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			String color = (String)js.executeScript("return this.dGraphAppMap.nodes[" + '"' 
					+ name + '"' + "].nodeColor");
			return color;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}
	
	public String getNodeType() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			String type = (String)js.executeScript("return this.dGraphAppMap.nodes[" + '"' 
					+ name + '"' + "].nodeType");
			return type;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}
	
	public String getPuType() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			String type = (String)js.executeScript("return this.dGraphAppMap.nodes[" + '"' 
					+ name + '"' + "].puType");
			return type;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}

	public DeploymentStatus getStatus() {

		String stat;
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			stat = (String)js.executeScript("return this.dGraphAppMap.nodes[" + '"' 
					+ name + '"' + "].status");
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
		if (stat.equals(WebConstants.ID.nodeStatusOk)) return DeploymentStatus.INTACT;
		if (stat.equals(WebConstants.ID.nodeStatusWarning)) return DeploymentStatus.COMPROMISED;
		if (stat.equals(WebConstants.ID.nodeStatusBroken)) return DeploymentStatus.BROKEN;
		else return DeploymentStatus.SCHEDULED;
	}

	public Long getPlannedInstances() {

		Long actualInstances = null;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			actualInstances = (Long)js.executeScript("return this.dGraphAppMap.nodes[" + '"' 
					+ name + '"' + "].plannedInstances");
			return actualInstances;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}

	public Long getActualInstances() {

		Long actualInstances = null;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			actualInstances = (Long)js.executeScript("return this.dGraphAppMap.nodes[" + '"' 
					+ name + '"' + "].actualInstances");
			return actualInstances;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}
	
	public void select() {
		WebElement el = driver.findElement(By.id(WebConstants.ID.nodePath + this.name));
		el.click();
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
		WebElement el = driver.findElement(By.xpath(WebConstants.Xpath.pathToUndeployNode));
		el.click();
		el = driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert));
		el.click();
		isActionMenuNotVisible();
	}
	
	public void showInfo() {
		clickOnInfo();
	}

	public boolean isDisplayed() {
		
		RemoteWebElement node = (RemoteWebElement) driver.findElement(By.id(WebConstants.ID.nodePath + this.name));
		return node.isDisplayed();
	}
	
	public void restart() {
		//TODO implement
	}
	
	private String getNameFromUI(String name) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			String label = (String)js.executeScript("return this.dGraphAppMap.nodes[" + '"' 
					+ name + '"' + "].id");
			return label;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		catch (WebDriverException e) {
			return null;
		}
	}
}
