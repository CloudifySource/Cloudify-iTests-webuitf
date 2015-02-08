package com.gigaspaces.webuitf.services.wrappers;

import com.gigaspaces.webuitf.services.NodeType;

public class ProcessingUnitInstanceWrapper extends AbstractServiceHostWrapper{

    private String puType;
    private String zones;
    private String applicationName;

    public ProcessingUnitInstanceWrapper(String name, NodeType nodeType, String zones, String puType, String applicationName){
        super( name, nodeType );
        this.puType = puType;
        this.zones = zones;
        this.applicationName = applicationName;
    }

    public String getZones() {
        return zones;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getPuType(){
        return puType;
    }
}