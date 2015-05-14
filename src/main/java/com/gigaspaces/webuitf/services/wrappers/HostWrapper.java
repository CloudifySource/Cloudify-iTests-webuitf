package com.gigaspaces.webuitf.services.wrappers;

import com.gigaspaces.webuitf.services.NodeType;

public class HostWrapper extends AbstractServiceHostWrapper{

    private final String gridServicesCount;
    private final String processingUnitInstanceCount;
    private final String primaryBackupsCount;

    public HostWrapper(String name, NodeType nodeType, String gridServicesCount, String processingUnitInstanceCount, String primaryBackupsCount ){
        super( name, nodeType );
        this.gridServicesCount = gridServicesCount;
        this.processingUnitInstanceCount = processingUnitInstanceCount;
        this.primaryBackupsCount = primaryBackupsCount;
    }

    public String getGridServicesCount() {
        return gridServicesCount;
    }

    public String getProcessingUnitInstanceCount() {
        return processingUnitInstanceCount;
    }

    public String getPrimaryBackupsCount() {
        return primaryBackupsCount;
    }
}