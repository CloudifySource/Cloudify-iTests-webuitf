/*******************************************************************************
 * Copyright (c) 2010 GigaSpaces Technologies Ltd. All rights reserved
 *
 * The software source code is proprietary and confidential information of GigaSpaces. 
 * You may use the software source code solely under the terms and limitations of 
 * The license agreement granted to you by GigaSpaces.
 *******************************************************************************/
package com.gigaspaces.webuitf.datagrid.configuration.grids;

import com.gigaspaces.webuitf.datagrid.configuration.ConfigurationGrid;
import com.gigaspaces.webuitf.util.AjaxUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Evgeny
 * @since 10.0
 */
public class GeneralConfigurationGrid extends ConfigurationGrid{

    private final String spaceSchemaName;
    private final boolean spaceSecured;
    private final boolean spacePersistent;
    private final boolean spaceClustered;
    private final String clusterSchemaName;

    private static final String ID = "gs-config-general-space";

    public GeneralConfigurationGrid( AjaxUtils helper ){

        super(helper,ID);

        WebElement spaceSchemaWebElement = thisGridElement.findElement( By.id(ID + "_spaceSchema") );
        WebElement spaceSecuredWebElement = thisGridElement.findElement( By.id(ID + "_secured") );
        WebElement spacePersistentWebElement = thisGridElement.findElement( By.id(ID + "_persistent") );
        WebElement spaceClusteredWebElement = thisGridElement.findElement( By.id(ID + "_clustered") );
        WebElement clusterSchemaWebElement = thisGridElement.findElement( By.id(ID + "_clusterSchema") );

        spaceSchemaName = getValue( spaceSchemaWebElement );
        clusterSchemaName = getValue( clusterSchemaWebElement );
        String spaceSecuredVal = getValue( spaceSecuredWebElement );
        String spacePersistentVal = getValue( spacePersistentWebElement );
        String spaceClusteredVal = getValue( spaceClusteredWebElement );

        spaceSecured = spaceSecuredVal.toLowerCase().equals( YES );
        spaceClustered = spaceClusteredVal.toLowerCase().equals( YES );
        spacePersistent = spacePersistentVal.toLowerCase().equals( YES );
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