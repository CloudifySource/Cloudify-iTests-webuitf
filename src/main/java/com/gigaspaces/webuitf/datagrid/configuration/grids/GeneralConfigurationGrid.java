/*******************************************************************************
 * Copyright (c) 2010 GigaSpaces Technologies Ltd. All rights reserved
 *
 * The software source code is proprietary and confidential information of GigaSpaces. 
 * You may use the software source code solely under the terms and limitations of 
 * The license agreement granted to you by GigaSpaces.
 *******************************************************************************/
package com.gigaspaces.webuitf.datagrid.configuration.grids;

import com.gigaspaces.webuitf.datagrid.configuration.ConfigurationConstants;
import com.gigaspaces.webuitf.util.AjaxUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author Evgeny
 * @since 10.0
 */
public class GeneralConfigurationGrid  implements ConfigurationConstants{

    private final String spaceSchemaName;
    private final boolean spaceSecured;
    private final boolean spacePersistent;
    private final boolean spaceClustered;
    private final String clusterSchemaName;

    public GeneralConfigurationGrid( AjaxUtils helper ){

        WebElement thisGridElement = helper.waitForElement(By.id("gs-config-general-space"), TIMEOUT);

        WebElement spaceSchemaWebElement = thisGridElement.findElement( By.id("gs-config-general-space_spaceSchema") );
        WebElement spaceSecuredWebElement = thisGridElement.findElement( By.id("gs-config-general-space_secured") );
        WebElement spacePersistentWebElement = thisGridElement.findElement( By.id("gs-config-general-space_persistent") );
        WebElement spaceClusteredWebElement = thisGridElement.findElement( By.id("gs-config-general-space_clustered") );
        WebElement clusterSchemaWebElement = thisGridElement.findElement( By.id("gs-config-general-space_clusterSchema") );

        spaceSchemaName = getValue( spaceSchemaWebElement );
        clusterSchemaName = getValue( clusterSchemaWebElement );
        String spaceSecuredVal = getValue( spaceSecuredWebElement );
        String spacePersistentVal = getValue( spacePersistentWebElement );
        String spaceClusteredVal = getValue( spaceClusteredWebElement );

        spaceSecured = spaceSecuredVal.toLowerCase().equals( YES );
        spaceClustered = spaceClusteredVal.toLowerCase().equals( YES );
        spacePersistent = spacePersistentVal.toLowerCase().equals( YES );
    }

    private String getValue( WebElement webElement ){

        WebElement valueColumnElement = webElement.findElement( By.className( "x-grid3-col-configValueCol" ) );
        WebElement spanElement = valueColumnElement.findElement(By.tagName("span"));
        return spanElement.getText();
    }

    public String getSpaceSchemaName() {
        return spaceSchemaName;
    }

    public boolean isSpaceSecured() {
        return spaceSecured;
    }

    public boolean isSpacePersistent() {
        return spacePersistent;
    }

    public boolean isSpaceClustered() {
        return spaceClustered;
    }

    public String getClusterSchemaName() {
        return clusterSchemaName;
    }
}