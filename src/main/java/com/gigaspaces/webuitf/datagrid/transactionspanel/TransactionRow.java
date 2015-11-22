package com.gigaspaces.webuitf.datagrid.transactionspanel;

/**
 * 
 * @author evgenyf
 * @since 11.0
 * 
 */
public class TransactionRow {

	private final String _id;
	private final String _status;
	private final String _type;
	private final int _numberOfLockedObjects;

	public TransactionRow(String id, String status, String type, int numberOfLockedObjects ){
		_id = id;
		_status = status;
		_type = type;
		_numberOfLockedObjects = numberOfLockedObjects;
	}

	public String getId(){
		return _id;
	}
	
	public String getStatus() {
		return _status;
	}

	public String getType() {
		return _type;
	}

	public int getNumberOfLockedObjects() {
		return _numberOfLockedObjects;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TransactionRow that = (TransactionRow) o;

		if (_numberOfLockedObjects != that._numberOfLockedObjects) return false;
		if (!_id.equals(that._id)) return false;
		if (!_status.equals(that._status)) return false;
		return _type.equals(that._type);

	}
}