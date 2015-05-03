package com.gigaspaces.webuitf.util.components.logspanel;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class LogsPanel {

    private final Selenium selenium;
    private final WebDriver driver;
    private final AjaxUtils helper;
    private final String className;

	public LogsPanel(WebDriver driver, Selenium selenium, String className ) {

        this.selenium = selenium;
        this.driver = driver;
        this.helper = new AjaxUtils(driver);
        this.className = className;
	}

    public boolean isDisplayed(){
        WebElement webElement = null;
        try {
            webElement = helper.waitForElement(TimeUnit.SECONDS, 5, By.className(className));
        }
        catch( Throwable t ){
            t.printStackTrace();
        }
        return webElement != null;
    }
	
	public void toggleSampling() {
		selenium.click(WebConstants.Xpath.pathToLogsSamplingButton);
	}
	
	public String getCurrentLog() {
        WebElement webElement = null;
        try {
            webElement = helper.waitForElement( TimeUnit.SECONDS, 5, By.className( WebConstants.ClassNames.runmodePanel ));
        }
        catch( Throwable t ){
            t.printStackTrace();
        }
        return webElement != null ? webElement.getText() : null;
	}
	


}