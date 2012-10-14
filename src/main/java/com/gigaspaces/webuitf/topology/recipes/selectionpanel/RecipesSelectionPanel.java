package com.gigaspaces.webuitf.topology.recipes.selectionpanel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class RecipesSelectionPanel {

	private WebDriver driver;
	private AjaxUtils helper;
	
	public RecipesSelectionPanel(WebDriver driver, AjaxUtils helper) {
		this.driver = driver;
		this.helper = helper;
	}
	
	public RecipeFolderNode getRecipeFolderNode(String puName) {
		String nodeElementId = getNodeElementId(WebConstants.ID.recipesSelectionTreeFolderNodePrefix, puName, null);
		return new RecipeFolderNode(driver, helper, nodeElementId);
	}
	
	public RecipeFileNode getRecipeFileNode(String puName, String fileName) {
		String nodeElementId = getNodeElementId(WebConstants.ID.recipesSelectionTreeFileNodePrefix, puName, fileName);
		return new RecipeFileNode(helper, nodeElementId);
	}

	/**
	 * 
	 * @param idPrefix
	 * @param puName
	 * @param fileName Optional. if passed, this method is used for file nodes (rather than folder nodes).
	 * @return
	 */
	private String getNodeElementId(String idPrefix, String puName, String fileName) {

		String returnedId = null;
		
		List<WebElement> treeNodeElements = getTreeNodeElements();
		for (WebElement el : treeNodeElements) {
			String id = el.getAttribute("id");
			
			// folder tree node id example: "gs-recipes-selection-tree_folder_travel.cassandra__-1107459053"
			if (id != null
					&& id.startsWith(idPrefix)
					&& id.contains(puName)
					&& (fileName == null || id.contains(fileName))) {
				returnedId = id;
				break;
			}
		}

		return returnedId;
	}

	private List<WebElement> getTreeNodeElements() {
		List<WebElement> treeNodeElements = driver.findElement(
				By.id(WebConstants.ID.recipesSelectionTree)).findElements(
				By.className("x-tree3-node"));
		return treeNodeElements;
	}

}
