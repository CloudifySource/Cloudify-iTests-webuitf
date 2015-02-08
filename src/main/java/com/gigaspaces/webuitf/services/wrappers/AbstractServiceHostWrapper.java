package com.gigaspaces.webuitf.services.wrappers;

import com.gigaspaces.webuitf.services.NodeType;

abstract public class AbstractServiceHostWrapper{

    private final String name;
    private final NodeType nodeType;

    public AbstractServiceHostWrapper( String name, NodeType nodeType){
        this.name = name;
        this.nodeType = nodeType;
    }

    public String getName() {
        return name;
    }

    public NodeType getNodeType() {
        return nodeType;
    }
}