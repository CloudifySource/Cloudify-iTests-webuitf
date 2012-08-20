package com.gigaspaces.webuitf.datagrid.typespanel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.LogUtils;

public class SpaceType {

	private static final String NEXT_BUTTON_CLASS = "x-grid3-col-slider_model_next_button";
	private static final String TOOLS_BOX_CLASS = "x-grid3-td-data_type_tools";
	private static final String CLEAR_ID = "clear_data_type";
	private static final String QUERY_ID = "query_data_type";

	private WebDriver driver;

	private String id;

	private String type;
	private int instancesCount;
	private int templatesCount;
	private String spaceKey;
	private String spaceRoutingKey;
	private List<String> indexedFields;
	private String storageType;
	private String priorityGroup;

	private AjaxUtils helper;

	public SpaceType(String type, WebDriver driver, String id) {
		this.type = type;
		this.driver = driver;
		this.id = id;
		this.helper = new AjaxUtils(driver);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStorageType() {
		return storageType;
	}
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
	public String getPriorityGroup() {
		return priorityGroup;
	}
	public void setPriorityGroup(String priorityGroup) {
		this.priorityGroup = priorityGroup;
	}
	public int getInstancesCount() {
		return instancesCount;
	}
	public void setInstancesCount(int instancesCount) {
		this.instancesCount = instancesCount;
	}
	public int getTemplatesCount() {
		return templatesCount;
	}
	public void setTemplatesCount(int templatesCount) {
		this.templatesCount = templatesCount;
	}
	public String getSpaceKey() {
		return spaceKey;
	}
	public void setSpaceKey(String spaceKey) {
		this.spaceKey = spaceKey;
	}
	public String getSpaceRoutingKey() {
		return spaceRoutingKey;
	}
	public void setSpaceRoutingKey(String spaceRoutingKey) {
		this.spaceRoutingKey = spaceRoutingKey;
	}
	public List<String> getIndexedFields() {
		return indexedFields;
	}
	public void setIndexedFields(List<String> indexedFields) {
		this.indexedFields = indexedFields;
	}

	public SpaceTypeMembersPanel next() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(id),By.className(NEXT_BUTTON_CLASS), By.tagName("a"));
		return new SpaceTypeMembersPanel(driver);
	}

	public void select() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(id), By.tagName("div"));
	}
	
	public void query() {

		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				WebElement typeElement = driver.findElement(By.id(id));
				WebElement actions = typeElement.findElement(By.className(TOOLS_BOX_CLASS)).findElement(By.tagName("button"));
				actions.click();
				WebElement queryItem = driver.findElement(By.id(QUERY_ID));
				queryItem.click();
				break;
			}
			catch (StaleElementReferenceException e) {
				LogUtils.log("Failed to discover element due to statistics update, retyring...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				seconds++;
			}
		}
	}

	public void clear() {

		int seconds = 0;
		while (seconds < AjaxUtils.ajaxWaitingTime) {
			try {
				WebElement typeElement = driver.findElement(By.id(id));
				WebElement actions = typeElement.findElement(By.className(TOOLS_BOX_CLASS)).findElement(By.tagName("button"));
				actions.click();
				WebElement clearItem = driver.findElement(By.id(CLEAR_ID));
				clearItem.click();
				driver.findElement(By.xpath(WebConstants.Xpath.acceptAlert)).click();
				break;
			}
			catch (StaleElementReferenceException e) {
				LogUtils.log("Failed to discover element due to statistics update, retyring...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				seconds++;
			}
		}
	}
	
	public void jumpToQuery() {
		WebElement row = driver.findElement(By.id(id));
		row.findElement(By.className("x-grid3-col-data_type_name")).findElement(By.tagName("a")).click();
	}
}
