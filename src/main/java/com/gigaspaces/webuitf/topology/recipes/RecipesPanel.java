package com.gigaspaces.webuitf.topology.recipes;

import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.topology.TopologySubPanel;
import com.gigaspaces.webuitf.topology.recipes.selectionpanel.RecipesSelectionPanel;
import com.thoughtworks.selenium.Selenium;

public class RecipesPanel extends TopologySubPanel {

	public RecipesPanel(Selenium selenium, WebDriver driver) {
		super(selenium, driver);
	}
	
	public RecipesViewPanel getViewPanel() {
		return new RecipesViewPanel(driver);
	}
	
	public RecipesSelectionPanel getSelectionPanel() {
		return new RecipesSelectionPanel(driver, helper);
	}
}
