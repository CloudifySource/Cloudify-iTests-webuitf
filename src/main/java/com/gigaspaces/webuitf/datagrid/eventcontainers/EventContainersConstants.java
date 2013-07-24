package com.gigaspaces.webuitf.datagrid.eventcontainers;

import com.gigaspaces.webuitf.WebConstants;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public interface EventContainersConstants {

    String ASYNC_POLLING_EVENT_CONTAINERS_GRID = "gs-async-polling-event-containers-grid";
    String POLLING_EVENT_CONTAINERS_GRID = "gs-polling-event-containers-grid";
    String NOTIFY_EVENT_CONTAINERS_GRID = "gs-notify-event-containers-grid";
	
	String COLUMN_CLASS_PREFIX = WebConstants.COLUMN_CLASS_PREFIX;
	
	
	
	//from service details - static data
	String ID_PROPERTY = "event-container-id";
	String TRANSACTIONAL_PROPERTY = "event-container-is-transactional";
	
	//from service monitors - dynamic data
	String STATUS_PROPERTY = "event-container-status";	
	String FAILED_EVENTS_PROPERTY = "failed-events";
	String PROCESSED_EVENTS_PROPERTY = "processed-events";
	
	String ID_COLUMN_CLASS = COLUMN_CLASS_PREFIX + ID_PROPERTY;
	String TRANSACTIONAL_COLUMN_CLASS = COLUMN_CLASS_PREFIX + TRANSACTIONAL_PROPERTY;
	
	String STATUS_COLUMN_CLASS = COLUMN_CLASS_PREFIX + STATUS_PROPERTY;
	String FAILED_EVENTS_COLUMN_CLASS = COLUMN_CLASS_PREFIX + FAILED_EVENTS_PROPERTY;
	String PROCESSED_EVENTS_COLUMN_CLASS = COLUMN_CLASS_PREFIX + PROCESSED_EVENTS_PROPERTY;
	
	
	
	
/*	String RECEIVE_BYTES_PER_SEC_PROPERTY = "receive_bytes_per_sec"; 
	String SEND_BYTES_PER_SEC_PROPERTY = "send_bytes_per_sec";
	String SEND_PACKETS_PER_SEC_PROPERTY = "send_packets_per_sec";
	String REDOLOG_RETAINED_SIZE_PROPERTY = "redolog_retained_size";	
	String TARGET_HOST_NAME_PROPERTY = "target_host_name";
	String SOURCE_NAME_PROPERTY = "source_name";
	String TARGET_MEMBER_NAME_PROPERTY = "target_member_name";
	String TARGET_PID_PROPERTY = "target_pid";
	String TARGET_VERSION_PROPERTY = "target_version";
	String CHANNEL_STATE_ICON_PROPERTY = "channel_state_icon";
	
	
	String TARGET_HOST_NAME_COLUMN_CLASS = COLUMN_CLASS_PREFIX + TARGET_HOST_NAME_PROPERTY;
	String SOURCE_NAME_COLUMN_CLASS = COLUMN_CLASS_PREFIX + SOURCE_NAME_PROPERTY;
	String TARGET_MEMBER_NAME_COLUMN_CLASS = COLUMN_CLASS_PREFIX + TARGET_MEMBER_NAME_PROPERTY;
	String TARGET_PID_COLUMN_CLASS = COLUMN_CLASS_PREFIX + TARGET_PID_PROPERTY;
	String TARGET_VERSION_COLUMN_CLASS = COLUMN_CLASS_PREFIX + TARGET_VERSION_PROPERTY;
	String CHANNEL_STATE_ICON_COLUMN_CLASS = COLUMN_CLASS_PREFIX + CHANNEL_STATE_ICON_PROPERTY;
	String RECEIVE_BYTES_PER_SEC_COLUMN_CLASS = COLUMN_CLASS_PREFIX + RECEIVE_BYTES_PER_SEC_PROPERTY; 
	String SEND_BYTES_PER_SEC_COLUMN_CLASS = COLUMN_CLASS_PREFIX + SEND_BYTES_PER_SEC_PROPERTY;
	String SEND_PACKETS_PER_SEC_COLUMN_CLASS = COLUMN_CLASS_PREFIX + SEND_PACKETS_PER_SEC_PROPERTY;
	String REDOLOG_RETAINED_SIZE_COLUMN_CLASS = COLUMN_CLASS_PREFIX + REDOLOG_RETAINED_SIZE_PROPERTY;*/		
}