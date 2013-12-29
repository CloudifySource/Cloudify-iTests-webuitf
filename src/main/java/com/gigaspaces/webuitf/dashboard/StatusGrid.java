package com.gigaspaces.webuitf.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.dashboard.ServicesGrid.Icon;
import com.thoughtworks.selenium.Selenium;

public class StatusGrid {

	WebDriver driver;	
	
	Selenium selenium;

	HealthGauge gauge;
	CPUCores cpuCores;
	Memory memory;
	
	private static final String CURRENT_PREFIX = "Current:";

	private final WebElement statusGridElement;

	public StatusGrid(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
		this.gauge = new HealthGauge();
		statusGridElement = driver.findElement( By.id( WebConstants.ID.statusGrid ) );
	}

	public static StatusGrid getInstance(Selenium selenium, WebDriver driver) {
		return new StatusGrid(selenium, driver);
	}
	
	public HealthGauge getHealthGauge() {
		return this.gauge;
	}
	
	public CPUCores getCpuCores() {
		return new CPUCores();
	}
	
	public Memory getMemory() {
		return new Memory();
	}
	
	private static Icon getIcon(String type) {
		if (type.equals(WebConstants.ID.okIcon)) return Icon.OK;
		if (type.equals(WebConstants.ID.criticalIcon)) return Icon.CRITICAL;
		if (type.equals(WebConstants.ID.warningIcon)) return Icon.ALERT;
		if (type.equals(WebConstants.ID.naIcon)) return Icon.NA;
		return null;
	}
	
	public String getGridHealth() {
		return selenium.getText(WebConstants.Xpath.pathToGridHealthInGridStatus);
	}
	
	static double getCountValue( WebElement element ){
		
		String perc = element.getText();
		if( perc.indexOf( CURRENT_PREFIX ) >= 0 ){
			perc = perc.substring( CURRENT_PREFIX.length() ).trim();
		}
		int i = 0;
		while (perc.charAt(i++) != '%');
		return Double.parseDouble(perc.substring(0, i - 1));
	}
	
	static Icon getStatusIcon( WebElement statePanel ){
		WebElement icon = statePanel.findElement( By.className( WebConstants.ClassNames.statePanelHeader ) );
		String style = icon.getAttribute("class");
		return StatusGrid.getIcon(style);
	}
	
	public class HealthGauge {
		
		public static final double POINTER_MIN_ANGLE = -17.4;
		public static final double POINTER_MAX_ANGLE = 17.4;
		
		public double getRotation() {
			WebElement svgGaugePointer = driver.findElement(By.id(WebConstants.ID.healthGaugePointer));
			String transform = svgGaugePointer.getAttribute("transform");
			return Double.parseDouble(transform.substring(7, 13));
		}
	}
	
	public class CPUCores {
		
		public Icon getIcon() {
			WebElement stateMemoryPanel = statusGridElement.findElement( By.id( WebConstants.ID.stateCpuPanel ) );
			return getStatusIcon( stateMemoryPanel );			
		}
		
		public Double getCount() {
			WebElement usedCpuElement = statusGridElement.findElement( By.id( WebConstants.ID.stateCpuUsageValue ) );
			return getCountValue( usedCpuElement );
		}
	}
	
	public class Memory {
		
		public Icon getIcon() {
			WebElement stateMemoryPanel = statusGridElement.findElement( By.id( WebConstants.ID.stateMemoryPanel ) );
			return getStatusIcon( stateMemoryPanel );
		}
		
		public Double getCount() {
			WebElement usedMemoryElement = 
					statusGridElement.findElement( By.id( WebConstants.ID.stateMemoryUsageValue ) );
			return getCountValue( usedMemoryElement );
		}
	}
}