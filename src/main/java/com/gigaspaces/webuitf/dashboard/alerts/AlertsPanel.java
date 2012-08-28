package com.gigaspaces.webuitf.dashboard.alerts;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openspaces.admin.alert.Alert;
import org.openspaces.admin.alert.AlertSeverity;
import org.openspaces.admin.alert.AlertStatus;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.LogUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;
import com.thoughtworks.selenium.Selenium;

/**
 * represents the Alerts grid found in the web-ui under the Dashboard tab
 * this class is a singleton, use getInstance to obtain the instance.
 * @author elip
 *
 */
public class AlertsPanel {
	
	public static final String REPLICATION = "Replication Channel Disconnected";
	
	public static final String GC = "Garbage Collection";

	public static final String PHYSICAL_MEMORY = "Physical Memory Utilization";

	public static final String CPU_UTILIZATION = "CPU Utilization";

	public static final String HEAP_MEMORY = "Heap Memory Utilization";

	public static final String PROVISION = "Provision Failure";

	public static String MIRROR = "Mirror Persistence Failure";
	
	public static String REDOLOG_OVERFLOW = "Replication Redo log Overflow";
	
	public static String REDOLOG_SIZE = "Replication Redo log";
	
	public static String MEMBER_ALIVE = "Member Alive Indicator";
	
	public static String ELASTIC_GSA_ALERT = "Grid Service Agent Provisioning Alert";
	public static String ELASTIC_GSC_ALERT = "Grid Service Container Provisioning Alert";
	public static final String ELASTIC_MACHINE_ALERT = "Machine Provisioning Alert";

	private Selenium selenium;
	private WebDriver driver;
	
	private AjaxUtils helper;

	public AlertsPanel(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.helper = new AjaxUtils(driver, selenium);
	}

	public static AlertsPanel getInstance(Selenium selenium, WebDriver driver) {
		return new AlertsPanel(selenium, driver);
	}
	
	public void waitForAlerts(final AlertStatus status, final String alertType, final int numberOfResolved){
		
		RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {
			public boolean getCondition() {
				int i = 0;
				List<WebUIAlert> webuiAlerts = getParentAlertsByType(alertType);
				for (WebUIAlert alert : webuiAlerts) {
					if (alert.getStatus().equals(status)){
						i++;
					}
				}
				if (i == numberOfResolved) return true;
				return false;
			}
		};
		AjaxUtils.repetitiveAssertTrue("Could not find " + numberOfResolved + " alerts of type " + alertType + " in status " + status, condition, 60000);
	}
	
	/**
	 * 
	 * @param alerts - the alerts retrieved from the webui
	 * @param adminAlerts - the alerts triggered by the test
	 */
	public boolean areAlertsConsistent(List<WebUIAlert> alerts, List<Alert> adminAlerts) {
		List<String> alertGroupIDS = new ArrayList<String>();
		
		for (Alert alert : adminAlerts) {
			/* if a resolved alert appears in the admin alerts, it must have a corresponding resolved alert
			   from the webui */
			if (alert.getStatus().equals(AlertStatus.RESOLVED)) {
				boolean found = false;
				for (WebUIAlert webuiAlert : alerts) {
					if (webuiAlert.equals(alert)) {
						found = true;
						break;
					}
				}
				if (!found) {
					return false;
				}
			}
			/* if a raised alert appears in the admin alerts, it must have at least one corresponding 
			   alert in the ui */
			if (alert.getStatus().equals(AlertStatus.RAISED)) {
				if (!alertGroupIDS.contains(alert.getGroupUid())) {
					boolean found = false;
					for (WebUIAlert webuiAlert : alerts) {
						if (webuiAlert.equals(alert)) {
							found = true;
							break;
						}
					}
					if (!found) {
						return false;
					}
					alertGroupIDS.add(alert.getGroupUid());
				}
			}
		}
		
		/* here we check that every resolved alert is associated with at least one raised alert */
		if (alerts.size() != 0) {
			for (int j = 0 ; j < alerts.size() ; j++) {
				if (alerts.get(j).getStatus().equals(AlertStatus.RESOLVED)) {
					boolean status = alerts.get(j + 1).getStatus().equals(AlertStatus.RAISED);
					if (!status) {
						return false;
					}
					boolean location = alerts.get(j + 1).getLocation().equals(alerts.get(j).getLocation());
					if (!location) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * retrieves alerts from the webui that fit a given AlertStatus
	 * @param status
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<WebUIAlert> getAlertsByStatus(AlertStatus status) {
		
		Exception exception = null;
		WebElement alert;
		String xPath;
		int i = 1;
		
		List<WebUIAlert> alerts = new ArrayList<WebUIAlert>();
		
		while(exception == null) {
			try {
				xPath = WebConstants.Xpath.getPathToAlertByIndex(i);
				alert = driver.findElement(By.xpath(xPath));
				WebUIAlert webUIAlert = new WebUIAlert(xPath);
				if (webUIAlert.getStatus().equals(status)) {
					LogUtils.log("found alert : " + webUIAlert);
					alerts.add(webUIAlert);
					selenium.click(xPath + WebConstants.Xpath.pathToAlertExpansionButton);
				}
				Thread.sleep(1000);
				i++;
			}
			catch (Exception e) {
				exception = e;
			}
		}
		return alerts;
	}
	
	/**
	 * retrieves alerts from web-ui apart from the specified type.
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<WebUIAlert> getAlertsByType(String type) {
		
		Exception exception = null;
		WebElement alert;
		String xPath;
		int i = 1;
		
		List<WebUIAlert> alerts = new ArrayList<WebUIAlert>();
		
		while(exception == null) {
			try {
				xPath = WebConstants.Xpath.getPathToAlertByIndex(i);
				alert = driver.findElement(By.xpath(xPath));
				WebUIAlert webUIAlert = new WebUIAlert(xPath);
				if (webUIAlert.getName().equals(type)) {
					LogUtils.log("found alert : " + webUIAlert);
					alerts.add(webUIAlert);
					selenium.click(xPath + WebConstants.Xpath.pathToAlertExpansionButton);
				}
				Thread.sleep(1000);
				i++;
			}
			catch (Exception e) {
				exception = e;
			}
		}
		return alerts;
	}
	/**
	 * return all alerts appart from the specified type
	 * pass null to retrieve all alerts
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<WebUIAlert> getAlertsAppartFrom(String type) {
		
		Exception exception = null;
		WebElement alert;
		String xPath;
		int i = 1;
		
		List<WebUIAlert> alerts = new ArrayList<WebUIAlert>();
		
		while(exception == null) {
			try {
				xPath = WebConstants.Xpath.getPathToAlertByIndex(i);
				alert = driver.findElement(By.xpath(xPath));
				WebUIAlert webUIAlert = new WebUIAlert(xPath);
				if (!webUIAlert.getName().equals(type)) {
					LogUtils.log("found alert : " + webUIAlert);
					alerts.add(webUIAlert);
					selenium.click(xPath + WebConstants.Xpath.pathToAlertExpansionButton);
				}
				Thread.sleep(1000);
				i++;
			}
			catch (Exception e) {
				exception = e;
			}
		}
		return alerts;
	}
	
	/**
	 * retrieves only the parent alerts. thats is, only the ones shown in the webui without expanding 
	 * the view of an alert
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unused")
	public List<WebUIAlert> getParentAlertsAppartFrom(String type) {
		
		Exception exception = null;
		WebElement alert;
		String xPath;
		int i = 1;
		
		List<WebUIAlert> alerts = new ArrayList<WebUIAlert>();
		
		while(exception == null) {
			try {
				xPath = WebConstants.Xpath.getPathToAlertByIndex(i);
				alert = driver.findElement(By.xpath(xPath));
				WebUIAlert webUIAlert = new WebUIAlert(xPath);
				if (!webUIAlert.getName().equals(type)) {
					LogUtils.log("found alert : " + webUIAlert);
					alerts.add(webUIAlert);
				}
				Thread.sleep(1000);
				i++;
			}
			catch (Exception e) {
				exception = e;
			}
		}
		return alerts;
	}
	
	@SuppressWarnings("unused")
	public List<WebUIAlert> getParentAlertsByType(String type) {
		
		Exception exception = null;
		WebElement alert;
		String xPath;
		int i = 1;
		
		List<WebUIAlert> alerts = new ArrayList<WebUIAlert>();
		
		while(exception == null) {
			try {
				xPath = WebConstants.Xpath.getPathToAlertByIndex(i);
				alert = driver.findElement(By.xpath(xPath));
				WebUIAlert webUIAlert = new WebUIAlert(xPath);
				if (webUIAlert.getName().equals(type)) {
					LogUtils.log("found alert : " + webUIAlert);
					alerts.add(webUIAlert);
				}
				Thread.sleep(1000);
				i++;
			}
			catch (Exception e) {
				exception = e;
			}
		}
		return alerts;
	}
	
	/**
	 * sorts the alert in the ui by their status
	 */
	public void sortAlertsGridByStatus() {
		selenium.click(WebConstants.Xpath.pathToStatusColumnInAlertsGrid);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public class WebUIAlert {
		
		private Logger _logger = Logger.getLogger(WebUIAlert.class.getName());

		private AlertSeverity severity;
		private String name;
		private String description;
		private String location;
		private String xPath;
		private String lastUpdated;
		private AlertStatus status;
		
		public WebUIAlert(String xPath) {
			this.xPath = xPath;
			this.name = getTypeFromUI(xPath);
			this.description = getDescriptionFromUI(xPath);
			this.location = getLocationFromUI(xPath);
			this.severity = getSeverityFromUI(xPath);
			this.lastUpdated = getLastUpdatedFromUI(xPath);
			this.status = getStatusFromUI(xPath);	
		}
		
		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}	
		
		public AlertSeverity getSeverity() {
			return severity;
		}
		
		public String getLocation() {
			return location;
		}
		
		public AlertStatus getStatus() {
			return status;
		}
		
		public void closeParrentAlert() {
			selenium.click(xPath + WebConstants.Xpath.pathToAlertExpansionButton);
		}
		
		public boolean generateDump() {
			boolean succeed = false;
			int seconds = 0;
			while (seconds < AjaxUtils.ajaxWaitingTime) {
				try {
					WebElement alertElement = driver.findElement(By
							.xpath(xPath));
					WebElement actions = alertElement.findElement(
							By.className("gs-actions-button")).findElement(
							By.tagName("button"));
					actions.click();
					WebElement generateDumpItem = driver.findElement(By
							.id(WebConstants.ID.alertsGridDumpItem));
					generateDumpItem.click();
					break;
				} catch (StaleElementReferenceException e) {
					_logger.info("Failed to discover element due to statistics update, retyring...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					seconds++;
				}
			}

			WebElement dumpWindow = helper.waitForElement(
					By.id(WebConstants.ID.servicesDumpWindow),
					AjaxUtils.ajaxWaitingTime);
			if (dumpWindow != null) {
				
				WebElement generateDumpButton = dumpWindow.findElement(By
						.className(WebConstants.ClassNames.buttonGenerate));
				generateDumpButton.click();
				
				int resultSeconds = 0;
				while (resultSeconds < AjaxUtils.ajaxWaitingTime) {
					WebElement progressMessageText = dumpWindow.findElement(By.className("x-progress-text"));
					String resultMessage = progressMessageText.getText();
					
					if (resultMessage.toLowerCase().contains("dump file generated successfully")) {
						succeed = true;
						break;
					} else {
						_logger.info("Failed to retrieve progress bar text, retyring...");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						resultSeconds++;
					}
				}
			}
			
			return succeed;
		}

		@Override
		public String toString() {
			return severity.toString() + " | " + name + " | " + description + " | " + location + " | " + lastUpdated;
			
		}
		
		/**
		 * only fields not containing specific measurements are compared, since reading may differ
		 * slightly from the test and the ui
		 * @param alert - an alert triggered from within the test
		 * @return 
		 */
		public boolean equals(Alert alert) {		
			if (!alert.getComponentDescription().equals(this.getLocation())) return false;
			if (!alert.getName().equals(this.getName())) return false;
			if (!alert.getSeverity().equals(this.getSeverity())) return false;
			if (!alert.getStatus().equals(this.getStatus())) return false;
			return true;		
		}
		
		public int getTimeStampInSecond() {
			String time[] = this.lastUpdated.split(" ")[3].split(":");
			int hours = Integer.parseInt(time[0]) * 3600;
			int minutes = Integer.parseInt(time[1]) * 60;
			int seconds = Integer.parseInt(time[2]);
			return hours + minutes + seconds;
		}
		
		private AlertSeverity getSeverityFromUI(String xpath) {
			String severity = selenium.getText(xpath + WebConstants.Xpath.pathToAlertSeverity);
			if (severity.equals("SEVERE")) return AlertSeverity.SEVERE;
			if (severity.equals("WARNING")) return AlertSeverity.WARNING;
			return AlertSeverity.INFO;
		}
		
		private String getTypeFromUI(String xpath) {
			return selenium.getText(xpath + WebConstants.Xpath.pathToAlertType);
		}
		
		private String getDescriptionFromUI(String xpath) {
			return selenium.getText(xpath + WebConstants.Xpath.pathToAlertDescription);
		}
		
		private String getLocationFromUI(String xpath) {
			return selenium.getText(xpath + WebConstants.Xpath.pathToAlertLocation);
		}
		
		private String getLastUpdatedFromUI(String xpath) {
			return selenium.getText(xpath + WebConstants.Xpath.pathToAlertLastUpdated);
		}
		
		private AlertStatus getStatusFromUI(String xpath) {
			try {
				driver.findElement(By.xpath(xpath)).findElement(By.className(WebConstants.ID.warningIcon));
				return AlertStatus.RAISED;
			}
			catch (NoSuchElementException e) {
				
			}
			try {
				driver.findElement(By.xpath(xpath)).findElement(By.className(WebConstants.ID.okIcon));
				return AlertStatus.RESOLVED;
			}
			catch (NoSuchElementException e) {
				
			}
			try {
				driver.findElement(By.xpath(xpath)).findElement(By.className(WebConstants.ID.criticalIcon));
				return AlertStatus.RAISED;
			}
			catch (NoSuchElementException e) {
				
			}
			try {
				driver.findElement(By.xpath(xpath)).findElement(By.className(WebConstants.ID.naIcon));
				return AlertStatus.NA;
			}
			catch (NoSuchElementException e) {
				
			}
			return null;
		}
	}
}
