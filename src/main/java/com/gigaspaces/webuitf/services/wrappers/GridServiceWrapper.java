package com.gigaspaces.webuitf.services.wrappers;

import com.gigaspaces.webuitf.services.NodeType;

public class GridServiceWrapper extends AbstractServiceHostWrapper{

    private final int threadsCount;
    private final String zones;
    private final String processingUnitInstanceCount;
    private final String primaryBackupsCount;

    public GridServiceWrapper( String name, NodeType nodeType, int threadsCount, String zones,
                               String processingUnitInstanceCount, String primaryBackupsCount ){
        super( name, nodeType );
        this.threadsCount = threadsCount;
        this.zones = zones;
        this.processingUnitInstanceCount = processingUnitInstanceCount;
        this.primaryBackupsCount = primaryBackupsCount;
    }

    public int getThreadsCount() {
        return threadsCount;
    }

    public String getZones() {
        return zones;
    }

    public String getProcessingUnitInstanceCount() {
        return processingUnitInstanceCount;
    }

    public String getPrimaryBackupsCount() {
        return primaryBackupsCount;
    }
}