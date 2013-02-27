package com.gigaspaces.webuitf.datagrid.typespanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpaceTypeMember {
	
	private String name;
	private String type;
	private String storageType;
	private String indexes;
	
	private String id;
	
	private WebDriver driver;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getIndexes() {
		return indexes;
	}

	public void setIndexes(String indexes) {
		this.indexes = indexes;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void select() {
		driver.findElement(By.id(id)).click();
	}
	
	@Override
	public String toString() {
		
		return "[SpaceTypeMember --> name=" + name + ", type=" + type + ", storageType=" + storageType + ", indexes=" + indexes + "]";
		
	}

}
