package com.gigaspaces.webuitf.datagrid;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.util.AjaxUtils;

public class ContextPager {
	
	private Logger _logger = Logger.getLogger(ContextPager.class.getName());
	
	private static final String SPACES_CONTEXT_PREFIX_ID = "gs-slider-grid-SPACES_";
	private static final String SPACE_INSTANCES_CONTEXT_PREFIX_ID = "gs-slider-grid-SPACE_INSTANCES_";
	
	private WebDriver driver;
	
	private AjaxUtils helper;
	
	public ContextPager(WebDriver driver) {
		this.driver = driver;
		this.helper = new AjaxUtils(driver);
	}
	
	public void prev(String spaceName) {
		driver.findElement(By.className("gwt-Anchor")).click();
	}
	
	public int countSpaceContexts(String puName) {
		String id = SPACES_CONTEXT_PREFIX_ID + puName.replace(" ", "_");
		return driver.findElements(By.id(id)).size();
	}
	
	public int countSpaceInstanceContexts(String spaceInstanceUid) {
		String id = SPACE_INSTANCES_CONTEXT_PREFIX_ID + spaceInstanceUid.replace(" ", "_");
		return driver.findElements(By.id(id)).size();
	}
	
	public int countAllSpaceInstances() {
		String id = "gs-slider-grid-SPACE_INSTANCES";
		return driver.findElement(By.id(id)).findElements(By.className("x-grid3-row")).size();
	}
	
	public int countAllBackupSpaceInstances() {
		String id = "gs-slider-grid-SPACE_INSTANCES";
		return driver.findElement(By.id(id)).findElements(By.className("gs-space-mode-BACKUP")).size();
	}
	
	public int countAllPrimarySpaceInstances() {
		String id = "gs-slider-grid-SPACE_INSTANCES";
		return driver.findElement(By.id(id)).findElements(By.className("gs-space-mode-PRIMARY")).size();
	}
	
	public SpaceContext getSpaceContext(String puName) {
		String id = SPACES_CONTEXT_PREFIX_ID + puName.replace(" ", "_");
		try {
			helper.waitForElement(TimeUnit.SECONDS, 10, By.id(id));
		}
		catch (TimeoutException e) {
			_logger.info("caugh a timeout exception while waiting for element. returning null");
			return null;
		}
		SpaceContext spaceContext = new SpaceContext(id, driver);
		return spaceContext;
	}
	
	public SpaceInstanceContext getSpaceInstanceContext(String spaceInstanceUid) {
		String id = SPACE_INSTANCES_CONTEXT_PREFIX_ID + spaceInstanceUid.replace(" ", "_");
		try {
			helper.waitForElement(TimeUnit.SECONDS, 10, By.id(id));
		}
		catch (TimeoutException e) {
			_logger.info("caugh a timeout exception while waiting for element. returning null");
			return null;
		}
		SpaceInstanceContext spaceInstanceContext = new SpaceInstanceContext(id,driver);
		return spaceInstanceContext;
	}

}
