package com.gigaspaces.webuitf.services.wrappers;

import com.gigaspaces.webuitf.services.NodeType;

public class HostWrapper extends AbstractServiceHostWrapper{

    private final String gridServicesCount;
    private final String processingUnitInstanceCount;

    public HostWrapper(String name, NodeType nodeType, String gridServicesCount, String processingUnitInstanceCount ){
        super( name, nodeType );
        this.gridServicesCount = gridServicesCount;
        this.processingUnitInstanceCount = processingUnitInstanceCount;
    }

    public String getGridServicesCount() {
        return gridServicesCount;
    }

    public String getProcessingUnitInstanceCount() {
        return processingUnitInstanceCount;
    }
}