package com.gigaspaces.webuitf.datagrid;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaceInstanceContext {
	
	private static final String PU_INSTANCES_NAME_CLASS = "x-grid3-col-pu_instance_name";
	private static final String SPACE_INSTANCE_NAME_CLASS = "x-grid3-td-slider_model_name";
    private static final String PID_CLASS = "x-grid3-col-pid";
	
	private static final String MEMORY_CLASS = "x-grid3-td-total_memory";
	
	private String spaceInstanceName;
	private String puInstanceName;
    private String uuid;
    private long pid;
	
	private String id;
	private WebDriver driver;
	
	private AjaxUtils helper;
    private final static String CLASS_PREFIX = "gs-slider-grid-SPACE_INSTANCES_";
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	public SpaceInstanceContext(String id, WebDriver driver) {
		this.id = id;
		this.driver = driver;
		this.helper = new AjaxUtils(driver);
		this.helper.setDriver(driver);
		this.puInstanceName = helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.id(id),By.className(PU_INSTANCES_NAME_CLASS));
		this.spaceInstanceName = helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.id(id),By.className(SPACE_INSTANCE_NAME_CLASS));
        this.pid = Long.parseLong(helper.waitForTextToBeExctractable(5, TimeUnit.SECONDS, By.id(id), By.className(PID_CLASS)));

		RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {
			public boolean getCondition() {

				try {
					WebElement instancesGridElement = helper.waitForElement(TimeUnit.SECONDS, 5, By.id("gs-slider-grid-SPACE_INSTANCES"));
					WebElement selectedRow = instancesGridElement.findElement(By.className("x-grid3-row-selected"));
					String spaceInstanceElementId = selectedRow.getAttribute("id");
					if( spaceInstanceElementId.startsWith( CLASS_PREFIX ) ) {
						uuid = spaceInstanceElementId.substring(CLASS_PREFIX.length());
					}
				}
				catch( Exception exc ){
					logger.log(Level.WARNING, exc.toString(), exc );
					return false;
				}

				return true;
			}
		};
		AjaxUtils.repetitiveAssertTrue( "Unable to initialize all elements while creating SpaceInstanceContext instance " +
				"for space instance [" + id + "]", condition, 10*1000 );
	}

	public String getSpaceInstanceName() {
		return spaceInstanceName;
	}

	public String getPuName() {
		return puInstanceName;
	}

    public long getPid(){
        return pid;
    }

    public String getUuid() {
        return uuid;
    }

    public void select() {
		for (int i = 0 ; i < 3 ; i++) {
			helper.clickWhenPossible(5, TimeUnit.SECONDS, By.id(id),By.className(SPACE_INSTANCE_NAME_CLASS));
		}
	}
	
	public int getRowUpdateCount(long pollingIntervalInMillis, int timeRangeInSeconds) {
		
		int numberOfUpdates = 0;
		int numberOfSuccesfullClicks = 0;
		int numberOfClickAttempts = 0;
		double seconds = 0;
		while (seconds < timeRangeInSeconds) {	
			try {
				logger.info("attempting to click the element");
				numberOfClickAttempts++;
				driver.findElement(By.id(id)).findElement(By.className(MEMORY_CLASS)).click();
				logger.info("click was succesfull");
				numberOfSuccesfullClicks++;
			}
			catch (NoSuchElementException e) {
				try {
					logger.severe("caugh exception due to row update");
					Thread.sleep(pollingIntervalInMillis);
				} catch (InterruptedException e1) {
				}
				numberOfUpdates++;
				
			}
			catch (StaleElementReferenceException e) {
			}
			catch (WebDriverException e){
			}
			seconds += pollingIntervalInMillis / 1000.0;
			
		}
		logger.info("Number of click attempts : " + numberOfClickAttempts);
		logger.info("Number of succesfull clicks : " + numberOfSuccesfullClicks);
		logger.info("Number of failed clicks due to row updates : " + numberOfUpdates);
		return numberOfUpdates;
		
	}
	
	public boolean isSelected() {
		String className = helper.retrieveAttribute(By.id(id), "class", driver);
		if (className.contains("selected")) {
			return true;
		}
		return false;
	}
	
	public boolean isPrimary(){
		
		WebElement spaceModeElement = helper.waitForElement(TimeUnit.SECONDS, 5, By.id(id) , 
				By.className(WebConstants.ClassNames.dataGridSpaceMode), By.xpath(WebConstants.Xpath.relativePathToSpaceModeImage));
		
		String spaceMode = helper.retrieveAttribute(spaceModeElement, "qtip");
		
		if(spaceMode.equalsIgnoreCase("primary"))
			return true;
		
		return false;
		
	}

}
