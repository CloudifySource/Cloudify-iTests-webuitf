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
public class MemoryManagementConfigurationGrid extends ConfigurationGrid{

    private final String cachePolicy;
    private final int lruEvictionBatchSize;
    private final int cacheSize;
    private final boolean memoryManagementState;

    private final int highWatermark;
    private final int lowWatermark;
    private final int writeOperationRejection;
    private final int writeOperationInspection;

    private static final String ID = "gs-config-memory-management";

    public MemoryManagementConfigurationGrid(AjaxUtils helper) {
        super( helper, ID );

        WebElement cachePolicyWebElement = thisGridElement.findElement(By.id(ID + "_cachePolicy"));
        WebElement lruEvictionBatchSizeWebElement = thisGridElement.findElement(By.id(ID + "_lruEvictionBatchSize"));
        WebElement cacheSizeWebElement = thisGridElement.findElement(By.id(ID + "_cacheSize"));
        WebElement memoryManagementStateWebElement = thisGridElement.findElement(By.id(ID + "_memoryManagementState"));
        WebElement highWatermarkWebElement = thisGridElement.findElement(By.id(ID + "_highWatermark"));
        WebElement lowWatermarkWebElement = thisGridElement.findElement(By.id(ID + "_lowWatermark"));
        WebElement writeOperationRejectionWebElement = thisGridElement.findElement(By.id(ID + "_writeOperationRejection"));
        WebElement writeOperationInspectionWebElement = thisGridElement.findElement(By.id(ID + "_writeOperationInspection"));

        cachePolicy = getValue(cachePolicyWebElement);

        String lruEvictionBatchSizeVal = getValue(lruEvictionBatchSizeWebElement);
        lruEvictionBatchSize = lruEvictionBatchSizeVal.toLowerCase().equals( "n/a" ) ? -1 :Integer.parseInt( lruEvictionBatchSizeVal );

        String cacheSizeVal = getValue(cacheSizeWebElement);
        cacheSize = cacheSizeVal.toLowerCase().equals( "n/a" ) ? -1 : Integer.parseInt( cacheSizeVal );

        String memoryManagementStateVal = getValue(memoryManagementStateWebElement);
        memoryManagementState = memoryManagementStateVal.toLowerCase().equals( "enabled" );

        String highWatermarkVal = getValue(highWatermarkWebElement);
        String lowWatermarkVal = getValue(lowWatermarkWebElement);
        String writeOperationRejectionVal = getValue(writeOperationRejectionWebElement);
        String writeOperationInspectionVal = getValue(writeOperationInspectionWebElement);

        //remove % at the end of strings
        highWatermarkVal = highWatermarkVal.substring( 0, highWatermarkVal.length() - 1 );
        lowWatermarkVal = lowWatermarkVal.substring(0, lowWatermarkVal.length() - 1);
        writeOperationRejectionVal = writeOperationRejectionVal.substring(0, writeOperationRejectionVal.length() - 1);
        writeOperationInspectionVal = writeOperationInspectionVal.substring(0, writeOperationInspectionVal.length() - 1);

        highWatermark = Integer.parseInt( highWatermarkVal );
        lowWatermark = Integer.parseInt( lowWatermarkVal );
        writeOperationRejection = Integer.parseInt( writeOperationRejectionVal );
        writeOperationInspection = Integer.parseInt( writeOperationInspectionVal );
    }

    public String getCachePolicy() {
        return cachePolicy;
    }

    public int getLruEvictionBatchSize() {
        return lruEvictionBatchSize;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public boolean isMemoryManagementState() {
        return memoryManagementState;
    }

    public int getHighWatermark() {
        return highWatermark;
    }

    public int getLowWatermark() {
        return lowWatermark;
    }

    public int getWriteOperationRejection() {
        return writeOperationRejection;
    }

    public int getWriteOperationInspection() {
        return writeOperationInspection;
    }
}