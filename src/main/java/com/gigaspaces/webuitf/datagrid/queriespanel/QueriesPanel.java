package com.gigaspaces.webuitf.datagrid.queriespanel;

import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.datagrid.DataGridSubPanel;

public class QueriesPanel extends DataGridSubPanel  {

	public QueriesPanel(WebDriver driver) {
		super(driver);
	}
	
	public QueryNavigator getNavigator() {
		return new QueryNavigator(driver);
	}
	
	public ResultsGrid getResultsGrid() {
		return new ResultsGrid(driver);
	}
}
