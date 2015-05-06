package com.gigaspaces.webuitf.processingunits.details;

import java.util.Map;

/**
 * Created by evgenyf on 5/5/2015.
 */
public class ProcessingUnitConfiguration {

    private final int plannedInstances;
    private final int actualInstances;

    public ProcessingUnitConfiguration( Map<String, String> values ){
        this( Integer.parseInt( values.get( "Planned instances" ) ), Integer.parseInt( values.get( "Running instances" ) ) );
    }

    public ProcessingUnitConfiguration( int plannedInstances, int actualInstances ){
        this.actualInstances = actualInstances;
        this.plannedInstances = plannedInstances;
    }


    public int getPlannedInstancesNumber() {
        return plannedInstances;
    }

    public int getActualInstancesNumber() {
        return actualInstances;
    }
}
