package com.gigaspaces.webuitf.datagrid.network.inboundactivity;

import com.gigaspaces.webuitf.WebConstants;

/**
 * 
 * @author evgenyf
 * @since 9.7.0
 */
public interface InboundActivityConstants {
	
	String INBOUND_ACTIVITY_GRID_ID = "gs-inbound-remote-activity-grid";
	
	String INBOUN_ACTIVITY_SPACE_MODE_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "space_mode";
	String INBOUN_ACTIVITY_SPACE_INSTANCE_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "space_instance_name";
	String INBOUN_ACTIVITY_PID_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "pid";
	String INBOUN_ACTIVITY_HOST_IP_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "host_ip";
	String INBOUN_ACTIVITY_RECEIVED_TRAFFIC_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "received_traffic_count";
	String INBOUN_ACTIVITY_GENERATED_TRAFFIC_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "generated_traffic_count";
	String INBOUN_ACTIVITY_TOTAL_TRAFFIC_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "total_traffic_count";
	
//	String LOCAL_CACHES_ID_COLUMN_CLASS_NAME = "x-grid3-col-id";
//	String LOCAL_CACHES_PID_COLUMN_CLASS_NAME = "x-grid3-col-pid";
//	String LOCAL_CACHES_HOST_COLUMN_CLASS_NAME = "x-grid3-col-hostname";
//	String LOCAL_CACHES_VERSION_COLUMN_CLASS_NAME = "x-grid3-col-version";
}
