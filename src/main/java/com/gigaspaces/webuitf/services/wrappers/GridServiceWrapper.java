package com.gigaspaces.webuitf.services.wrappers;

import com.gigaspaces.webuitf.services.NodeType;

public class GridServiceWrapper extends AbstractServiceHostWrapper{

    private int threadsCount;
    private String zones;

    public GridServiceWrapper( String name, NodeType nodeType, int threadsCount, String zones ){
        super( name, nodeType );
        this.threadsCount = threadsCount;
        this.zones = zones;
    }

    public int getThreadsCount() {
        return threadsCount;
    }

    public String getZones() {
        return zones;
    }
}