/*******************************************************************************
 * Copyright (c) 2010 GigaSpaces Technologies Ltd. All rights reserved
 *
 * The software source code is proprietary and confidential information of GigaSpaces. 
 * You may use the software source code solely under the terms and limitations of 
 * The license agreement granted to you by GigaSpaces.
 *******************************************************************************/
package com.gigaspaces.webuitf.datagrid.configuration;

import com.gigaspaces.webuitf.util.AjaxUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Evgeny
 * @since 10.0
 */
public class ConfigurationGrid implements ConfigurationConstants{

    protected final WebElement thisGridElement;

    public ConfigurationGrid(AjaxUtils helper, String configurationGridId){
        thisGridElement = helper.waitForElement( By.id( configurationGridId ), 7 );
    }

    protected String getValue( WebElement webElement ){

        WebElement valueColumnElement = webElement.findElement( By.className( "x-grid3-col-configValueCol" ) );
        WebElement spanElement = valueColumnElement.findElement(By.tagName("span"));
        return spanElement.getText();
    }
}