package com.gigaspaces.webuitf.datagrid.eventcontainers.asyncpolling;

import com.gigaspaces.webuitf.datagrid.eventcontainers.AbstractEventContainerPropertiesRow;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class AsyncPollingEventContainerPropertiesRow extends AbstractEventContainerPropertiesRow {

	private final int _concurrentConsumers;
	private final int _receiveTimeout;
	
	public AsyncPollingEventContainerPropertiesRow( int rowIndex, String id,
			String status, boolean isTransactional, long failedEvents,
			long processedEvents, int concurrentConsumers, int receiveTimeout ) {
		super( rowIndex, id, status, isTransactional, failedEvents, processedEvents );
		
		this._concurrentConsumers = concurrentConsumers;
		this._receiveTimeout = receiveTimeout;
	}

	public int getConcurrentConsumers() {
		return _concurrentConsumers;
	}

	public int getReceiveTimeout() {
		return _receiveTimeout;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", _concurrentConsumers=" + _concurrentConsumers + 
				", _receiveTimeout=" + _receiveTimeout + "]";
	}	
}