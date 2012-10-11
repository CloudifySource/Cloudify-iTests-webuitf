package com.gigaspaces.webuitf.topology.recipes.selectionpanel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class RecipeFolderNode {
	
	private final int TIMEOUT_IN_SECONDS = 2;

	private WebDriver driver;
	private AjaxUtils helper;

	private String id;


	public RecipeFolderNode(WebDriver driver, AjaxUtils helper, String id) {
		this.driver = driver;
		this.helper = helper;
		this.id = id;
	}

	public void expand() {
		if (isExpanded()) {
			return;
		}
		toggleExpandCollapse();
	}

	public void collapse() {
		if (!isExpanded()) {
			return;
		}
		toggleExpandCollapse();
	}
	
	public void select() {
		WebElement element = helper.waitForElement(By.id(id), TIMEOUT_IN_SECONDS);
		element.click();
	}

	protected boolean isExpanded() {
		List<WebElement> treeNodeElements = getTreeNodeElements();
		for (WebElement el : treeNodeElements) {
			String id = el.getAttribute("id");
			if (id != null && id.contains(WebConstants.ID.recipesSelectionTreeFileNodePrefix)) {
				return true;
			}
		}
		return false;
	}
	
	private void toggleExpandCollapse() {
		WebElement element = helper.waitForElement(By.id(id), TIMEOUT_IN_SECONDS);
		// there can be only one node joint per a parent tree node
		WebElement joint = element.findElement(By.className("x-tree3-node-joint"));
		joint.click();
	}

	private List<WebElement> getTreeNodeElements() {
		List<WebElement> treeNodeElements = driver.findElement(
				By.id(WebConstants.ID.recipesSelectionTree)).findElements(
				By.className("x-tree3-node"));
		return treeNodeElements;
	}

}
