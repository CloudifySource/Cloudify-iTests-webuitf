package com.gigaspaces.webuitf.datagrid;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.util.AjaxUtils;

public class SpaceContext {
	
	private Logger _logger = Logger.getLogger(SpaceContext.class.getName());

	private static final String PU_NAME_CLASS = "x-grid3-td-pu_name";
	private static final String SPACE_NAME_CLASS = "x-grid3-td-slider_model_name";
	private static final String NEXT_BUTTON_CLASS = "x-grid3-td-slider_model_next_button";
	private static final String ACTUAL_INSTANCES_CLASS = "x-grid3-td-actual_instances";
	private static final String SPACE_INSTANCES_GRID = "gs-slider-grid-SPACE_INSTANCES";
	private static final String REMOTE_ACTIVITY_PROBE_BUTTON_CLASS = "x-grid3-td-remote_activity";

	private String spaceName;
	private String puName;
	private String id;

	private WebDriver driver;

	private AjaxUtils helper;

	public SpaceContext(String id, WebDriver driver) {
		this.id = id;
		this.driver = driver;
		this.helper = new AjaxUtils(driver);
		this.helper.setDriver(driver);
		this.puName = helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.id(id),By.className(PU_NAME_CLASS));
		this.spaceName = helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.id(id),By.className(SPACE_NAME_CLASS));
	}
	public String getSpaceName() {
		return spaceName;
	}

	public String getPuName() {
		return puName;
	}

	public void select() {
		for (int i = 0 ; i < 3 ; i++) {
			helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(id),By.className(SPACE_NAME_CLASS));
		}
	}
	
	public int getActualInstances() {
		String inst = helper.waitForTextToBeExctractable(10, TimeUnit.SECONDS, By.id(id), By.className(ACTUAL_INSTANCES_CLASS));
		return Integer.valueOf(inst);
	}

	/*
	 * TODO: optimize / fix to include stricter base case
	 */
	public SpaceInstanceContext next() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(id),By.className(NEXT_BUTTON_CLASS), By.tagName("a"));
		// wait for animation
		try {
			Thread.sleep(3000);
			double seconds = 0;
			while (seconds < 5) {
				try {

					List<WebElement> elements = driver.findElement(By.id(SPACE_INSTANCES_GRID)).findElements(By.className("x-grid3-row"));
					for (WebElement el : elements) {
						String className = el.getAttribute("class");
						if (className.contains("selected")) {
							return new SpaceInstanceContext(el.getAttribute("id"), driver);
						}
					}
				}
				catch (StaleElementReferenceException e) {
					_logger.info("caught an exception while retrieving attribute ");
					seconds += 0.5;
				}
				catch (WebDriverException e) {
					_logger.info("caught an exception while retrieving attribute ");
					seconds += 0.5;
				}
			}

		} catch (InterruptedException e) {
		}
		return null;

	}

	public boolean isSelected() {
		String className = helper.retrieveAttribute(By.id(id), "class", driver);
		if (className.contains("selected")) {
			return true;
		}
		return false;
	}
	
	public void setRemoteActivityProbe(boolean b) {

		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(id),
				By.className(REMOTE_ACTIVITY_PROBE_BUTTON_CLASS), By.tagName("button"));
	}
}