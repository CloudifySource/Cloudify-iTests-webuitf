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
public class NetworkAndEnvironmentConfigurationGrid extends ConfigurationGrid{

    private final static String ID = "gs-config-network-and-environment";

    private final String homeDirectory;
    private String hostName;
    private final int rmiRegistryPort;

    public NetworkAndEnvironmentConfigurationGrid( AjaxUtils helper ){
        super( helper, ID );

        WebElement homeDirectoryWebElement = thisGridElement.findElement(By.id(ID + "_homeDirectory"));
        WebElement hostNameWebElement = thisGridElement.findElement(By.id(ID + "_hostName"));
        WebElement rmiRegistryPortWebElement = thisGridElement.findElement(By.id(ID + "_rmiRegistryPort"));

        homeDirectory = getValue(homeDirectoryWebElement);
        hostName = getValue(hostNameWebElement);
        hostName = hostName.toLowerCase().equals( "n/a" ) ? null : hostName;
        String rmiRegistryPortStr = getValue(rmiRegistryPortWebElement);
        rmiRegistryPort = rmiRegistryPortStr.toLowerCase().equals( "n/a" ) ? -1 : Integer.parseInt( rmiRegistryPortStr );
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public String getHostName() {
        return hostName;
    }

    public int getRmiRegistryPort() {
        return rmiRegistryPort;
    }
}