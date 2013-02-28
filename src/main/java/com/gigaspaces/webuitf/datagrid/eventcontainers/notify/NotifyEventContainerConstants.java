package com.gigaspaces.webuitf.datagrid.eventcontainers.notify;

import com.gigaspaces.webuitf.datagrid.eventcontainers.EventContainersConstants;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public interface NotifyEventContainerConstants extends EventContainersConstants{

	String IS_PASS_ARRAY_AS_IS = "pass-array-as-is";
	String BATCH_SIZE = "batch-size";
	String BATCH_TIME = "batch-time";
	String COMM_TYPE = "comm-type";
	String IS_TAKE_ON_NOTIFY = "is-take-on-notify";
	String IS_FIFO = "is-fifo";

	String NOTIFY_OPERATIONS = "notify-operations";


	String IS_PASS_ARRAY_AS_IS_COLUMN_CLASS = COLUMN_CLASS_PREFIX + IS_PASS_ARRAY_AS_IS;
	String BATCH_SIZE_COLUMN_CLASS = COLUMN_CLASS_PREFIX + BATCH_SIZE;
	String BATCH_TIME_HANDLER_COLUMN_CLASS = COLUMN_CLASS_PREFIX + BATCH_TIME;
	String COMM_TYPE_COLUMN_CLASS = COLUMN_CLASS_PREFIX + COMM_TYPE;
	String IS_TAKE_ON_NOTIFY_COLUMN_CLASS = COLUMN_CLASS_PREFIX + IS_TAKE_ON_NOTIFY;
	String IS_FIFO_COLUMN_CLASS = COLUMN_CLASS_PREFIX + IS_FIFO;

	String NOTIFY_OPERATIONS_COLUMN_CLASS = COLUMN_CLASS_PREFIX + NOTIFY_OPERATIONS;
}