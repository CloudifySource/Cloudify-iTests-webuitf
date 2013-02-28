package com.gigaspaces.webuitf.datagrid.eventcontainers.polling;

import com.gigaspaces.webuitf.datagrid.eventcontainers.AbstractEventContainerPropertiesRow;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class PollingEventContainerPropertiesRow extends AbstractEventContainerPropertiesRow {

	private final int _maxConcurrentConsumers;
	private final long _receiveTimeout;
	private final String _receiveOperationsHandler;
	private final boolean _isPassArrayAsIs;
	
	public PollingEventContainerPropertiesRow( int rowIndex, String id, String status, 
						boolean isTransactional, long failedEvents,long processedEvents,
						int maxConcurrentConsumers, long receiveTimeout, String receiveOperationsHandler,
						boolean isPassArrayAsIs ) {
		super( rowIndex, id, status, isTransactional, failedEvents, processedEvents );
		_maxConcurrentConsumers = maxConcurrentConsumers;
		_receiveTimeout = receiveTimeout;
		_receiveOperationsHandler = receiveOperationsHandler;
		_isPassArrayAsIs = isPassArrayAsIs;
	}

	public int getMaxConcurrentConsumers() {
		return _maxConcurrentConsumers;
	}

	public long getReceiveTimeout() {
		return _receiveTimeout;
	}

	public String getReceiveOperationsHandler() {
		return _receiveOperationsHandler;
	}

	public boolean isPassArrayAsIs() {
		return _isPassArrayAsIs;
	}

	@Override
	public String toString() {
		return super.toString() + ", _maxConcurrentConsumers=" + _maxConcurrentConsumers + 
				", _receiveTimeout=" + _receiveTimeout + 
				", _receiveOperationsHandler=" + _receiveOperationsHandler + 
				", _isPassArrayAsIs=" + _isPassArrayAsIs + "]";
	}
}