package com.gigaspaces.webuitf.processingunits.details;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.gigaspaces.webuitf.util.RepetitiveConditionProvider;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Map<String, String> values;
    static public Logger logger = Logger.getLogger(ConfigPanel.class.getName());

	public ConfigPanel(Selenium selenium, WebDriver driver) {
        this.selenium = selenium;
        this.driver = driver;
        this.helper = new AjaxUtils(driver);
	}

    public ProcessingUnitConfiguration getProcessingUnitConfiguration(){

        RepetitiveConditionProvider condition = new RepetitiveConditionProvider() {
            public boolean getCondition() {

                try {
                    values = getValues();
                }
                catch( Exception exc ){
                    logger.log(Level.WARNING, exc.toString(), exc);
                    return false;
                }

                return true;
            }
        };
        AjaxUtils.repetitiveAssertTrue( "Failed to retrieve values for ProcessingUnitConfiguration", condition, 15*1000 );

        return new ProcessingUnitConfiguration( values );
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
