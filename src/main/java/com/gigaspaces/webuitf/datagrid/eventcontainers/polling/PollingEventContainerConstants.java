package com.gigaspaces.webuitf.datagrid.eventcontainers.polling;

import com.gigaspaces.webuitf.datagrid.eventcontainers.EventContainersConstants;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public interface PollingEventContainerConstants extends EventContainersConstants{

	//from service details - static data
	String MAX_CONCURRENT_CONSUMERS = "max-concur-customers";
	String RECEIVE_TIMEOUT = "receive-timeout";
	String RECEIVE_OPERATION_HANDLER = "receive-operation-handler";
	String IS_PASS_ARRAY_AS_IS = "pass-array-as-is";
	
	//from service monitors - dynamic data
	String CONSUMERS = "consumers";
	
	
	String MAX_CONCURRENT_CONSUMERS_COLUMN_CLASS = COLUMN_CLASS_PREFIX + MAX_CONCURRENT_CONSUMERS;
	String RECEIVE_TIMEOUT_COLUMN_CLASS = COLUMN_CLASS_PREFIX + RECEIVE_TIMEOUT;
	String RECEIVE_OPERATION_HANDLER_COLUMN_CLASS = COLUMN_CLASS_PREFIX + RECEIVE_OPERATION_HANDLER;
	String IS_PASS_ARRAY_AS_IS_COLUMN_CLASS = COLUMN_CLASS_PREFIX + IS_PASS_ARRAY_AS_IS;
	
	String CONSUMERS_COLUMN_CLASS = COLUMN_CLASS_PREFIX + CONSUMERS;
}