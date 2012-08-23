package com.gigaspaces.webuitf.datagrid.typespanel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.datagrid.DataGridSubPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class TypesPanel extends DataGridSubPanel {

	private static final String TYPE_ID_PREFIX = "gs-slider-grid-DATA_TYPES_LIST_";
	private static final String TYPES_GRID_ID = "gs-slider-grid-DATA_TYPES_LIST";
	private AjaxUtils helper;

	private static final int WAIT_TIMEOUT_IN_MS = 5000;
	private static final String OBJECT_COUNT_CLASS = "x-grid3-td-data_type_objects_count";
	private static final String TEMPLATES_COUNT_CLASS = "x-grid3-td-data_type_templates_count";
	private static final String STORAGE_TYPE_CLASS = "x-grid3-col-data_type_storage_type";
	private static final String PRIORATY_GROUP_CLASS = "x-grid3-col-data_type_priority_group";
	private static final String SPACE_KEY_CLASS = "x-grid3-td-data_type_space_key";
	private static final String SPACE_ROUTING_KEY_CLASS = "x-grid3-td-data_type_space_routing_key";
	private static final String INDEXED_FIELDS_CLASS = "x-grid3-td-data_type_indexed_fields";
	
	public TypesPanel(WebDriver driver) {
		super(driver);
		this.helper = new AjaxUtils(driver);
	}

	public SpaceType getType(String type) {

		String id = TYPE_ID_PREFIX + type;
		helper.waitForElement(By.id(id), WAIT_TIMEOUT_IN_MS);

		SpaceType spaceType = new SpaceType(type, driver, id);
		spaceType.setId(id);
		int instancesCount = Integer.parseInt(helper.waitForTextToBeExctractable(4, TimeUnit.SECONDS, By.id(id),By.className(OBJECT_COUNT_CLASS)));
		int templatesCount = Integer.parseInt(helper.waitForTextToBeExctractable(4, TimeUnit.SECONDS, By.id(id),By.className(TEMPLATES_COUNT_CLASS)));
		String storageType = helper.waitForTextToBeExctractable(4, TimeUnit.SECONDS, By.id(id),By.className(STORAGE_TYPE_CLASS));
		String priorityGroup = helper.waitForTextToBeExctractable(4, TimeUnit.SECONDS, By.id(id),By.className(PRIORATY_GROUP_CLASS));
		String spaceKey = helper.waitForTextToBeExctractable(4, TimeUnit.SECONDS, By.id(id),By.className(SPACE_KEY_CLASS));
		String spaceRoutingKey = helper.waitForTextToBeExctractable(4, TimeUnit.SECONDS, By.id(id),By.className(SPACE_ROUTING_KEY_CLASS));
		List<String> indexedFields = new ArrayList<String>();
		String indexed = helper.waitForTextToBeExctractable(4, TimeUnit.SECONDS, By.id(id),By.className(INDEXED_FIELDS_CLASS));
		for (String s : indexed.split(",")) {
			indexedFields.add(s);
		}
		spaceType.setInstancesCount(instancesCount);
		spaceType.setTemplatesCount(templatesCount);
		spaceType.setPriorityGroup(priorityGroup);
		spaceType.setStorageType(storageType);
		spaceType.setIndexedFields(indexedFields);
		spaceType.setSpaceKey(spaceKey);
		spaceType.setSpaceRoutingKey(spaceRoutingKey);
		return spaceType;
	}

	public boolean waitForTypes(int timeoutInSeconds) {
		
		try {
			helper.waitForElementToDisappear(TimeUnit.SECONDS, timeoutInSeconds, By.className("ext-el-mask"));
			return true;
		}
		catch (TimeoutException e) {
			return false;
		}
	}
	
	public int countNumberOfTypes() {
		return driver.findElement(By.id(TYPES_GRID_ID)).findElements(By.className("x-grid3-row")).size();		
	}

}
