package com.gigaspaces.webuitf.datagrid;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.datagrid.connectionspanel.ConnectionsPanel;
import com.gigaspaces.webuitf.datagrid.instancespanel.InstancesPanel;
import com.gigaspaces.webuitf.datagrid.queriespanel.QueriesPanel;
import com.gigaspaces.webuitf.datagrid.statisticspanel.StatisticsPanel;
import com.gigaspaces.webuitf.datagrid.templatespanel.TemplatesPanel;
import com.gigaspaces.webuitf.datagrid.transactionspanel.TransactionsPanel;
import com.gigaspaces.webuitf.datagrid.typespanel.TypesPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class DataGridSubPanel {
	
	protected WebDriver driver;
	protected AjaxUtils helper;
	
	public DataGridSubPanel(WebDriver driver) {
		this.driver = driver;
		this.helper = new AjaxUtils(driver);
	}

	public ConnectionsPanel switchToConnectionsPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.connectionsPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ConnectionsPanel(driver);
	}
	
	public InstancesPanel switchToInstancesPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.instancesPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new InstancesPanel(driver);
	}

	public QueriesPanel switchToQueriesPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.queriesPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new QueriesPanel(driver);
	}

	public StatisticsPanel switchToStatisticsPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.statisticsPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new StatisticsPanel(driver);
	}

	public TemplatesPanel switchToTemplatesPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.templatesPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new TemplatesPanel(driver);
	}

	public TransactionsPanel switchToTransactionsPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.transactionsPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new TransactionsPanel(driver);
	}

	public TypesPanel switchToTypesPanel() {
		helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(WebConstants.ID.typesPanelToggle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new TypesPanel(driver);
	}

}