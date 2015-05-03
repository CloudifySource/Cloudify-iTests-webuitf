package com.gigaspaces.webuitf.util.components.deployment;

import com.gigaspaces.webuitf.WebConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgenyf on 6/24/2014.
 */
public class AdvancedDeployment {

    private final Selenium _selenium;
    private final WebDriver _driver;
    private final AjaxUtils _helper;

    public AdvancedDeployment( Selenium selenium, WebDriver driver ){
        _selenium = selenium;
        _driver = driver;
        _helper = new AjaxUtils(driver, selenium);
    }

    public void setSlaOverridesFile( File slaFile ){
        if( slaFile.exists() ) {
            WebElement slaOverridestextField = _driver.findElement(By.id(WebConstants.ID.SLA_OVERRIDES_FIELD));
            String filePath = slaFile.getAbsolutePath();
            slaOverridestextField.sendKeys(filePath);
        }
    }

    public void selectAnyZoneRB(){
        WebElement rb = _driver.findElement( By.id( WebConstants.ID.SELECT_ANY_ZONE_RADIO_RADIO_BUTTON ) );
        rb.click();
    }

    public void selectSpecificZoneRB(){
        WebElement rb = _driver.findElement( By.id( WebConstants.ID.SELECT_SPECIFIC_ZONES_RADIO_BUTTON ) );
        rb.click();
    }

    public void addMaxInstancesPerZone( String zone, int maxInstancesPerZone ){
        WebElement addButton = _helper.waitForElement( TimeUnit.SECONDS, 5, By.id( WebConstants.ID.ADD_MAX_INSTANCES_PER_ZONE_BUTTON ) );
        if( addButton != null ) {
            addButton.click();
            setZone(zone);
            setMaxInstances(maxInstancesPerZone);
        }
    }

    private void setZone( String zone ) {
        setMaxInstancesPerZoneCellValue("x-grid3-col-zone_name", zone);
    }

    private void setMaxInstances( int maxInstancesNum ) {
        setMaxInstancesPerZoneCellValue("x-grid3-col-max_instances_per_zone", String.valueOf(maxInstancesNum));
    }

    private void setMaxInstancesPerZoneCellValue( String columnClassName, String cellValue ) {

        _helper.clickWhenPossible( 10, TimeUnit.SECONDS, By.className( columnClassName ) );

        List<WebElement> valueCellEditorElements = _helper.waitForElements( TimeUnit.SECONDS, 10,
                By.cssSelector("#" + WebConstants.ID.MAX_INSTANCES_PER_ZONE_GRID + " input.x-form-text.x-form-field" ) );
        WebElement lastCellEditorElement = null;
        for( WebElement element : valueCellEditorElements ){
            boolean isDisplayed = element.isDisplayed();
            System.out.println( "key Elements, isDisplayed:" + isDisplayed +
                    ", tagName:" + element.getTagName() + ", text:" + element.getText() );
            if( isDisplayed ){
                lastCellEditorElement = element;
            }
        }
        if( lastCellEditorElement != null ) {
            lastCellEditorElement.clear();
            lastCellEditorElement.sendKeys(cellValue);
        }
    }

    public void addProperty( String key, String value ){
        WebElement addContextPropertyButton =
                _driver.findElement(By.id(WebConstants.ID.ADD_CONTEXT_PROPERTY_BUTTON));
        addContextPropertyButton.click();
        setKey( key );
        setValue( value );
    }

    private void setKey( String key ) {
        setCellValue( "x-grid3-col-key", key );
    }

    private void setValue( String value ) {
        setCellValue( "x-grid3-col-value", value );
    }

    private void setCellValue( String columnClassName, String cellValue ) {

        _helper.clickWhenPossible( 10, TimeUnit.SECONDS, By.className( columnClassName ) );
        List<WebElement> columnCellElements = _helper.waitForElements( TimeUnit.SECONDS, 10, By.className( columnClassName ) );
        WebElement lastColumnElement = null;
        for( WebElement element : columnCellElements ){
            boolean isDisplayed = element.isDisplayed();
            System.out.println( ">>> columnClassName Elements" + columnClassName + ", isDisplayed:" + isDisplayed +
                    ", tagName:" + element.getTagName() + ", text:" + element.getText() );
            if( isDisplayed ) {
                lastColumnElement = element;
            }
        }

        if( lastColumnElement != null ){
            _helper.clickWhenPossible( 10, TimeUnit.SECONDS, lastColumnElement );
        }

        List<WebElement> valueCellEditorElements = _helper.waitForElements( TimeUnit.SECONDS, 10,
                By.cssSelector("#" + WebConstants.ID.CONTEXT_PROPERTIES_GRID + " input.x-form-text.x-form-field" ) );
        WebElement lastCellEditorElement = null;
        for( WebElement element : valueCellEditorElements ){
            boolean isDisplayed = element.isDisplayed();
            System.out.println( "key Elements, isDisplayed:" + isDisplayed +
                    ", tagName:" + element.getTagName() + ", text:" + element.getText() );
            if( isDisplayed ){
                lastCellEditorElement = element;
            }
        }
        if( lastCellEditorElement != null ) {
            lastCellEditorElement.sendKeys(cellValue);
        }
    }

    public void selectZones( Set<String> zones ){
        List<WebElement> checkboxElements =
                _helper.waitForElements( TimeUnit.SECONDS, 10, By.className( "x-grid3-col-zoneselected" ) );
/*
        List<WebElement> checkboxElements2 =
                _helper.waitForElements( TimeUnit.SECONDS, 10, By.className( "x-grid3-cc-zoneselected" ) );
        List<WebElement> checkboxElements3 =
                _helper.waitForElements( TimeUnit.SECONDS, 10, By.className( "x-grid3-td-zoneselected" ) );
*/
        List<WebElement> zoneNamesElements =
                _helper.waitForElements( TimeUnit.SECONDS, 10, By.className( "x-grid3-col-zonename" ) );
        int rowsCount = zoneNamesElements.size();
        for( int index = 0; index < rowsCount; index++ ){
            WebElement zoneNameElement = zoneNamesElements.get( index );
            String zoneName = zoneNameElement.getText();
            System.out.println( "Zone name:" + zoneName );
            if( zones.contains( zoneName )){
                WebElement checkBoxWebElement = checkboxElements.get( index );
                boolean displayed = checkBoxWebElement.isDisplayed();
                checkBoxWebElement.click();
            }
        }
    }
}