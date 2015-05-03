package com.gigaspaces.webuitf.processingunits.details;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * This class is a mapping of the Physical Tabular tab in the topology tab
 * It offers methods for all data retrieval possible in this panel
 * @author elip
 *
 */
public class ConfigPanel {

    private final Selenium selenium;
    private final WebDriver driver;
    private final AjaxUtils helper;

	public ConfigPanel(Selenium selenium, WebDriver driver) {
        this.selenium = selenium;
        this.driver = driver;
        this.helper = new AjaxUtils(driver);
	}

    public int getActualInstancesNumber(){
        Map<String, String> values = getValues();
        return Integer.parseInt( values.get( "Running instances" ) );
    }

    private Map<String, String> getValues(){
        WebElement gridElement = helper.waitForElement(TimeUnit.SECONDS, 5, By.className(WebConstants.ClassNames.processingUnitsViewConfig));
        Map<String, String> retMap = new HashMap<String, String>();
        List<WebElement> keyElements =
                gridElement.findElements( By.className( WebConstants.ClassNames.dictionaryKey ) );
        List<WebElement> valueElements =
                gridElement.findElements( By.className( WebConstants.ClassNames.dictionaryValue ) );

        int size = keyElements.size();

        for( int index = 0; index < size; index++ ){
            WebElement keyElement = keyElements.get(index);
            WebElement valueElement = valueElements.get(index);
            retMap.put( keyElement.getText(), valueElement.getText() );
        }

        return retMap;
    }


}
