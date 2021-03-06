package com.gigaspaces.webuitf.datagrid.configuration;

import com.gigaspaces.webuitf.datagrid.configuration.grids.BlobStoreConfigurationGrid;
import com.gigaspaces.webuitf.datagrid.configuration.grids.GeneralConfigurationGrid;
import com.gigaspaces.webuitf.datagrid.configuration.grids.MemoryManagementConfigurationGrid;
import com.gigaspaces.webuitf.datagrid.configuration.grids.NetworkAndEnvironmentConfigurationGrid;
import com.gigaspaces.webuitf.util.AjaxUtils;

/**
 * Created by evgenyf on 5/14/2014.
 */
public class ConfigurationPanel {

    private BlobStoreConfigurationGrid blobStoreConfigurationGrid;
    private final GeneralConfigurationGrid generalConfigurationGrid;
    private final MemoryManagementConfigurationGrid memoryManagementConfigurationGrid;
    private final NetworkAndEnvironmentConfigurationGrid networkAndEnvironmentConfigurationGrid;

    private final AjaxUtils helper;

    public ConfigurationPanel(AjaxUtils helper, boolean loadBlobStoreGrid ) {

        this.helper = helper;

        generalConfigurationGrid = new GeneralConfigurationGrid(helper);
        memoryManagementConfigurationGrid = new MemoryManagementConfigurationGrid(helper);
        networkAndEnvironmentConfigurationGrid = new NetworkAndEnvironmentConfigurationGrid(helper);
        if( loadBlobStoreGrid ) {
            blobStoreConfigurationGrid = new BlobStoreConfigurationGrid(helper);
        }
    }

    public BlobStoreConfigurationGrid getBlobStoreConfigurationGrid() {
        return blobStoreConfigurationGrid;
    }

    public GeneralConfigurationGrid getGeneralConfigurationGrid() {
        return generalConfigurationGrid;
    }

    public MemoryManagementConfigurationGrid getMemoryManagementConfigurationGrid() {
        return memoryManagementConfigurationGrid;
    }

    public NetworkAndEnvironmentConfigurationGrid getNetworkAndEnvironmentConfigurationGrid() {
        return networkAndEnvironmentConfigurationGrid;
    }
}