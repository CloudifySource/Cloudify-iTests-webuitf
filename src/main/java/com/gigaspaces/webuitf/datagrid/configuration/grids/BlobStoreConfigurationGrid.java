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
public class BlobStoreConfigurationGrid extends ConfigurationGrid {

    private final String devices;
    private final String volumeDir;
    private final String durabilityLevel;
    private final int capacity;
    private final int cacheCapacity;
    private final int cacheSize;
    private final boolean reformat;

    private final static String ID = "gs-config-blob-store";

    public BlobStoreConfigurationGrid( AjaxUtils helper ){
        super(helper,ID);

        WebElement devicesWebElement = thisGridElement.findElement( By.id(ID + "_devices") );
        WebElement capacityWebElement = thisGridElement.findElement( By.id(ID + "_capacity") );
        WebElement cacheCapacityWebElement = thisGridElement.findElement( By.id(ID + "_cacheCapacity") );
        WebElement cacheSizeWebElement = thisGridElement.findElement( By.id(ID + "_cacheSize") );
        WebElement volumeDirWebElement = thisGridElement.findElement( By.id(ID + "_volumeDir") );
        WebElement durabilityLevelWebElement = thisGridElement.findElement( By.id(ID + "_durabilityLevel") );
        WebElement reformatWebElement = thisGridElement.findElement( By.id(ID + "_reformat") );

        devices = getValue(devicesWebElement);
        String capacityVal = getValue(capacityWebElement);
        capacity = Integer.parseInt( capacityVal );
        String cacheCapacityVal = getValue(cacheCapacityWebElement);
        cacheCapacity = Integer.parseInt( cacheCapacityVal );
        String cacheSizeVal = getValue(cacheSizeWebElement);
        cacheSize = Integer.parseInt( cacheSizeVal );
        volumeDir = getValue(volumeDirWebElement);
        durabilityLevel = getValue(durabilityLevelWebElement);
        String reformatVal = getValue(reformatWebElement);
        reformat = reformatVal.toLowerCase().equals( YES );
    }

    public String getDevices() {
        return devices;
    }

    public String getVolumeDir() {
        return volumeDir;
    }

    public String getDurabilityLevel() {
        return durabilityLevel;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCacheCapacity() {
        return cacheCapacity;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public boolean isReformat() {
        return reformat;
    }
}