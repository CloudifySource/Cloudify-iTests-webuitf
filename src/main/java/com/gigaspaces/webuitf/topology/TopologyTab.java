package com.gigaspaces.webuitf.topology;

import com.gigaspaces.webuitf.BaseApplicationContextPanel;
import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.topology.applicationmap.ApplicationMap;
import com.gigaspaces.webuitf.topology.sidepanel.TopologySidePanel;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TopologyTab extends BaseApplicationContextPanel {

	public TopologyTab(Selenium selenium, WebDriver driver) {
		super(selenium, driver);
	}

	public ApplicationMap getApplicationMap() {
		return new ApplicationMap(driver, selenium);
	}
	
	public TopologySubPanel getTopologySubPanel() {
		return new TopologySubPanel(selenium, driver);
	}
	
	public TopologySidePanel getDetailsPanel() {
		return new TopologySidePanel(driver, selenium);
	}
	
	public void selectApplication(String applicationName) {
		super.selectApplication(applicationName, WebConstants.ID.topologyCombobox);
	}
	
	public boolean containsApplication(String applicationName) {
		
		try{
			super.selectApplication(applicationName, WebConstants.ID.topologyCombobox);			
		} 
		catch(TimeoutException toe){
			return false;
		}
		return true;
	}

	public String getSelectedApplication() {
		return super.getSelectedApplication(WebConstants.ID.topologyCombobox);
	}
	
	public boolean isMaskedNoContext() {
		return super.isMaskedNoContext(WebConstants.ID.topologyPanel);
	}
	
	public boolean isMaskedLoading() {
		String maskMessage = "Loading application map...";
		return super.isMaskedLoading(WebConstants.ID.topologyPanel, maskMessage);
	}

    public boolean isUndeployApplicationAvailable(){
        WebElement element = helper.waitForElement( TimeUnit.SECONDS, 20, By.id(WebConstants.ID.undeployApplicationButton) );
        logger.info( "isUndeployApplicationAvailable(), element=" + element );
        if( element != null ){
            logger.info( "isUndeployApplicationAvailable(), is element displayed=" + element.isDisplayed() );
        }
        return element != null && element.isDisplayed();
    }

    public void undeploySelectedApplication(){
        helper.clickWhenPossible( 10, TimeUnit.SECONDS, By.id( WebConstants.ID.undeployApplicationButton ) );

        List<WebElement> buttons = driver.findElements(By.className("x-btn-text").tagName("button"));
        WebElement yesButton = null;
        for( WebElement webElement : buttons ){
            if( webElement.getText().equals( "Yes" ) ){
                yesButton = webElement;
                break;
            }
        }
        if( yesButton != null ) {
            yesButton.click();
        }
    }
}
