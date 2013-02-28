package com.gigaspaces.webuitf.datagrid.eventcontainers.asyncpolling;

import com.gigaspaces.webuitf.datagrid.eventcontainers.EventContainersConstants;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public interface AsyncPollingEventContainerConstants extends EventContainersConstants{

	String CONCURRENT_CONSUMERS = "max-conc-consumers";
	String RECEIVE_TIMEOUT = "receive-timeout";
	
	String CONCURRENT_CONSUMERS_COLUMN_CLASS = COLUMN_CLASS_PREFIX + CONCURRENT_CONSUMERS;
	String RECEIVE_TIMEOUT_COLUMN_CLASS = COLUMN_CLASS_PREFIX + RECEIVE_TIMEOUT;
}
