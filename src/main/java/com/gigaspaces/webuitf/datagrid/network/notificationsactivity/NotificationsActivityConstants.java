package com.gigaspaces.webuitf.datagrid.network.notificationsactivity;

import com.gigaspaces.webuitf.WebConstants;

/**
 * 
 * @author evgenyf
 * @since 9.7.0
 */
public interface NotificationsActivityConstants {
	
	String NOTIFICATION_ACTIVITY_GRID_ID = "gs-outbound-remote-activity-grid";
	
	String NOTIFICATION_ACTIVITY_GSC_NAME_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "gsc_name";
	String NOTIFICATION_ACTIVITY_PID_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "pid";
	String NOTIFICATION_ACTIVITY_HOST_IP_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "host_ip";

	String NOTIFICATION_ACTIVITY_RECEIVED_TRAFFIC_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "received_traffic_count";
	String NOTIFICATION_ACTIVITY_GENERATED_TRAFFIC_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "generated_traffic_count";
	String NOTIFICATION_ACTIVITY_TOTAL_TRAFFIC_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "total_traffic_count";
	
	String NOTIFICATION_ACTIVITY_INVOCATION_COUNT_COLUMN_CLASS_NAME = WebConstants.COLUMN_CLASS_PREFIX + "total_invocation_count";
	
	

	
//	String LOCAL_CACHES_ID_COLUMN_CLASS_NAME = "x-grid3-col-id";
//	String LOCAL_CACHES_PID_COLUMN_CLASS_NAME = "x-grid3-col-pid";
//	String LOCAL_CACHES_HOST_COLUMN_CLASS_NAME = "x-grid3-col-hostname";
//	String LOCAL_CACHES_VERSION_COLUMN_CLASS_NAME = "x-grid3-col-version";
}
