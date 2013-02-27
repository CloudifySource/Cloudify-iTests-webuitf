package com.gigaspaces.webuitf.datagrid.queriespanel;

import com.gigaspaces.webuitf.datagrid.DataGridSubPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class QueriesPanel extends DataGridSubPanel  {

	public QueriesPanel(AjaxUtils helper) {
		super(helper);
	}
	
	public QueryNavigator getNavigator() {
		return new QueryNavigator(helper.getDriver());
	}
	
	public ResultsGrid getResultsGrid() {
		return new ResultsGrid(helper.getDriver());
	}
}
