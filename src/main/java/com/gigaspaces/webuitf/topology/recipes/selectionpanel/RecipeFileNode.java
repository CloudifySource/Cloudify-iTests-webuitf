package com.gigaspaces.webuitf.topology.recipes.selectionpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.util.AjaxUtils;

public class RecipeFileNode {

	private final int TIMEOUT_IN_SECONDS = 2;

	private AjaxUtils helper;

	private String id;

	public RecipeFileNode(AjaxUtils helper, String id) {
		this.helper = helper;
		this.id = id;
	}

	public void select() {
		WebElement element = helper.waitForElement(By.id(id), TIMEOUT_IN_SECONDS);
		element.click();
	}
	
}
