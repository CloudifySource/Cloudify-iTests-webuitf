package com.gigaspaces.webuitf.datagrid.typespanel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class SpaceTypeMembersPanel {
	
	private static final String MEMBER_ID_PREFIX = "gs-slider-grid-DATA_TYPE_MEMBERS_LIST_";
	private static final String MEMBER_ATTRIBUTE_CLASS_PREFIX = "x-grid3-td-data_type_member_";
	
	private WebDriver driver;
	private AjaxUtils helper;

	public SpaceTypeMembersPanel(WebDriver driver) {
		this.driver = driver;
		this.helper.setDriver(driver);
		this.helper = new AjaxUtils(driver);
	}
	
	public SpaceTypeMember getMember(String name) {
		SpaceTypeMember member = new SpaceTypeMember();
		String id = MEMBER_ID_PREFIX + name;
		try {
			WebElement memberRow = driver.findElement(By.id(id));
			member.setId(id);
			member.setName(name);
			member.setType(memberRow.findElement(By.className(MEMBER_ATTRIBUTE_CLASS_PREFIX + "type")).getText());
			member.setStorageType(memberRow.findElement(By.className(MEMBER_ATTRIBUTE_CLASS_PREFIX + "storage_type")).getText());
			member.setIndexes(memberRow.findElement(By.className(MEMBER_ATTRIBUTE_CLASS_PREFIX + "indexes")).getText());
			return member;
		}
		catch (NoSuchElementException e) {
			return null;
		}
		
	}
	
	public void previous(String type) {
		// removed - dangerous: clicks every anchor element in the page!
//		driver.findElement(By.className("gwt-Anchor")).click();
		
		By cssSelector = By.cssSelector(".gs-a-btn[data-value=\"" + type + "\"]");
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.sliderDataTypes), cssSelector);
	}

}
