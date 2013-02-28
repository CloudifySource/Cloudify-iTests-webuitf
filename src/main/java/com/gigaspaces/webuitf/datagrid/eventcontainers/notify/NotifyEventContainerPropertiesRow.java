package com.gigaspaces.webuitf.datagrid.eventcontainers.notify;

import com.gigaspaces.webuitf.datagrid.eventcontainers.AbstractEventContainerPropertiesRow;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class NotifyEventContainerPropertiesRow extends AbstractEventContainerPropertiesRow {

	private final String _notifyOperations;
	private final boolean _isPerformTakeOnNotify;
	private final boolean _isFifo;
	private final String _commType;
	private final int _batchTime;
	private final int _batchSize;
	private final boolean _isPassArrayAsIs;
	
	public NotifyEventContainerPropertiesRow( int rowIndex, String id,
			String status, boolean isTransactional, long failedEvents,
			long processedEvents, String notifyOperations, boolean isPerformTakeOnNotify,
			boolean isFifo, String commType, int batchTime, int batchSize, boolean isPassArrayAsIs ) {
		
		super(rowIndex, id, status, isTransactional, failedEvents, processedEvents);
		_notifyOperations = notifyOperations;
		_isPerformTakeOnNotify = isPerformTakeOnNotify;
		_isFifo = isFifo;
		_commType = commType;
		_batchSize = batchSize;
		_batchTime = batchTime;
		_isPassArrayAsIs = isPassArrayAsIs;
	}

	public String getNotifyOperations() {
		return _notifyOperations;
	}

	public boolean isPerformTakeOnNotify() {
		return _isPerformTakeOnNotify;
	}

	public boolean isFifo() {
		return _isFifo;
	}

	public String getCommType() {
		return _commType;
	}

	public int getBatchTime() {
		return _batchTime;
	}

	public int getBatchSize() {
		return _batchSize;
	}

	public boolean isPassArrayAsIs() {
		return _isPassArrayAsIs;
	}

	@Override
	public String toString() {
		return super.toString() + ", _notifyOperations=" + _notifyOperations + 
				", _isPerformTakeOnNotify=" + _isPerformTakeOnNotify + ", _isFifo=" + _isFifo + 
				", _commType=" + _commType + ", _batchTime=" + _batchTime + 
				", _batchSize=" + _batchSize + ", _isPassArrayAsIs=" + _isPassArrayAsIs + "]";
	}
}