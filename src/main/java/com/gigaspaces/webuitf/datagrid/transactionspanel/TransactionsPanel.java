package com.gigaspaces.webuitf.datagrid.transactionspanel;

import com.gigaspaces.webuitf.datagrid.DataGridSubPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;

public class TransactionsPanel extends DataGridSubPanel {

	private static final String TYPE_ID_PREFIX = "gs-slider-grid-TRANSACTIONS_LIST_";
	private static final String TYPES_GRID_ID = "gs-slider-grid-TRANSACTION_LOCKED_OBJECTS_LIST";

	private static final int WAIT_TIMEOUT_IN_MS = 5000;

	private final static String CLASS_NAME_PREFIX = "x-grid3-td-";

	private static final String TRANSACTION_ID = "transaction_id";
	private static final String TRANSACTION_TYPE = "transaction_type";
	private static final String TRANSACTION_START_TIME = "transaction_start_time";
	private static final String TRANSACTION_END_TIME = "transaction_end_time";
	private static final String TRANSACTION_STATUS = "transaction_status";
	private static final String TRANSACTION_NUM_OF_LOCKED_OBJECTS = "transaction_num_of_locked_objects";

	private static final String TRANSACTION_ID_CLASS = CLASS_NAME_PREFIX + TRANSACTION_ID;
	private static final String TRANSACTION_TYPE_CLASS = CLASS_NAME_PREFIX + TRANSACTION_TYPE;
	private static final String TRANSACTION_START_TIME_CLASS = CLASS_NAME_PREFIX + TRANSACTION_START_TIME;
	private static final String TRANSACTION_END_TIME_CLASS = CLASS_NAME_PREFIX + TRANSACTION_END_TIME;
	private static final String TRANSACTION_STATUS_CLASS = CLASS_NAME_PREFIX + TRANSACTION_STATUS;
	private static final String TRANSACTION_NUM_OF_LOCKED_OBJECTS_CLASS = CLASS_NAME_PREFIX + TRANSACTION_NUM_OF_LOCKED_OBJECTS;

	public TransactionsPanel(AjaxUtils helper) {
		super(helper);
	}

}