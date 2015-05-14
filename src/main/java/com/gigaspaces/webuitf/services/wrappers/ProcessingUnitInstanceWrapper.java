package com.gigaspaces.webuitf.services.wrappers;

import com.gigaspaces.webuitf.services.NodeType;

public class ProcessingUnitInstanceWrapper extends AbstractServiceHostWrapper{

    private String puType;
    private String zones;

    public ProcessingUnitInstanceWrapper(String name, NodeType nodeType, String zones, String puType){
        super( name, nodeType );
        this.puType = puType;
        this.zones = zones;
    }

    public String getZones() {
        return zones;
    }

    public String getPuType(){
        return puType;
    }
}