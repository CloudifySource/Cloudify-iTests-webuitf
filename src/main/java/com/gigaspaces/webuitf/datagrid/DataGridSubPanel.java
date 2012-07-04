package com.gigaspaces.webuitf.datagrid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.datagrid.connectionspanel.ConnectionsPanel;
import com.gigaspaces.webuitf.datagrid.instancespanel.InstancesPanel;
import com.gigaspaces.webuitf.datagrid.queriespanel.QueriesPanel;
import com.gigaspaces.webuitf.datagrid.statisticspanel.StatisticsPanel;
import com.gigaspaces.webuitf.datagrid.templatespanel.TemplatesPanel;
import com.gigaspaces.webuitf.datagrid.transactionspanel.TransactionsPanel;
import com.gigaspaces.webuitf.datagrid.typespanel.TypesPanel;

public class DataGridSubPanel {
	
	protected WebDriver driver;
	
	public DataGridSubPanel(WebDriver driver) {
		this.driver = driver;
	}

	public ConnectionsPanel switchToConnectionsPanel() {
		WebElement healthButton = driver.findElement(By.id(WebConstants.ID.connectionsPanelToggle));
		healthButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ConnectionsPanel(driver);
	}
	
	public InstancesPanel switchToInstancesPanel() {
		WebElement healthButton = driver.findElement(By.id(WebConstants.ID.instancesPanelToggle));
		healthButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new InstancesPanel(driver);
	}

	public QueriesPanel switchToQueriesPanel() {
		WebElement healthButton = driver.findElement(By.id(WebConstants.ID.queriesPanelToggle));
		healthButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new QueriesPanel(driver);
	}

	public StatisticsPanel switchToStatisticsPanel() {
		WebElement healthButton = driver.findElement(By.id(WebConstants.ID.statisticsPanelToggle));
		healthButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new StatisticsPanel(driver);
	}

	public TemplatesPanel switchToTemplatesPanel() {
		WebElement healthButton = driver.findElement(By.id(WebConstants.ID.templatesPanelToggle));
		healthButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new TemplatesPanel(driver);
	}

	public TransactionsPanel switchToTransactionsPanel() {
		WebElement healthButton = driver.findElement(By.id(WebConstants.ID.transactionsPanelToggle));
		healthButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new TransactionsPanel(driver);
	}

	public TypesPanel switchToTypesPanel() {
		WebElement healthButton = driver.findElement(By.id(WebConstants.ID.typesPanelToggle));
		healthButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new TypesPanel(driver);
	}


}
