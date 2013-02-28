package com.gigaspaces.webuitf.datagrid.eventcontainers;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
abstract public class AbstractEventContainerPropertiesRow {

	private final int _rowIndex;
	private final String _id;
	private final String _status;
	private final boolean _isTransactional;
	private final long _failedEvents;
	private final long _processedEvents;
	
	public AbstractEventContainerPropertiesRow( int rowIndex, String id, String status, 
					boolean isTransactional, long failedEvents, long processedEvents ){
		
		_rowIndex = rowIndex;
		_id = id;
		_status = status;
		_isTransactional = isTransactional;
		_failedEvents = failedEvents;
		_processedEvents = processedEvents;
	}

	public int getRowIndex() {
		return _rowIndex;
	}

	public String getId() {
		return _id;
	}

	public String getStatus() {
		return _status;
	}

	public boolean isTransactional() {
		return _isTransactional;
	}

	public long getFailedEvents() {
		return _failedEvents;
	}

	public long getProcessedEvents() {
		return _processedEvents;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [_rowIndex=" + _rowIndex + ", _id=" + _id + 
				", _status=" + _status + ", _isTransactional=" + _isTransactional + 
				", _failedEvents="+ _failedEvents + ", _processedEvents=" + _processedEvents;
	}
}