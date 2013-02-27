package com.gigaspaces.webuitf.datagrid.utils;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class ChannelsPropertiesRow {
	
	//can eb null for aggregated view
	private final String _sourceName;
	private final String _targetMemberName;
	private final String _targetHostName;
	private final String _targetVersion;
	private final long _targetPid;
	private final String _channelState;
	
	private long _sendPacketsPerSec;
	private long _redoLogRetainedSize;
	private long _sendBytesPerSec;
	
	public static final String COLUMN_CLASS_PREFIX = "x-grid3-col-";
//	public static final String COLUMN_CLASS_PREFIX = "x-grid3-td-";
	
	private static final String RECEIVE_BYTES_PER_SEC_PROPERTY = "receive_bytes_per_sec"; 
	private static final String SEND_BYTES_PER_SEC_PROPERTY = "send_bytes_per_sec";
	private static final String SEND_PACKETS_PER_SEC_PROPERTY = "send_packets_per_sec";
	private static final String REDOLOG_RETAINED_SIZE_PROPERTY = "redolog_retained_size";	
	private static final String TARGET_HOST_NAME_PROPERTY = "target_host_name";
	private static final String SOURCE_NAME_PROPERTY = "source_name";
	private static final String TARGET_MEMBER_NAME_PROPERTY = "target_member_name";
	private static final String TARGET_PID_PROPERTY = "target_pid";
	private static final String TARGET_VERSION_PROPERTY = "target_version";
	private static final String CHANNEL_STATE_ICON_PROPERTY = "channel_state_icon";
	
	
	public static final String TARGET_HOST_NAME_COLUMN_CLASS = COLUMN_CLASS_PREFIX + TARGET_HOST_NAME_PROPERTY;
	public static final String SOURCE_NAME_COLUMN_CLASS = COLUMN_CLASS_PREFIX + SOURCE_NAME_PROPERTY;
	public static final String TARGET_MEMBER_NAME_COLUMN_CLASS = COLUMN_CLASS_PREFIX + TARGET_MEMBER_NAME_PROPERTY;
	public static final String TARGET_PID_COLUMN_CLASS = COLUMN_CLASS_PREFIX + TARGET_PID_PROPERTY;
	public static final String TARGET_VERSION_COLUMN_CLASS = COLUMN_CLASS_PREFIX + TARGET_VERSION_PROPERTY;
	public static final String CHANNEL_STATE_ICON_COLUMN_CLASS = COLUMN_CLASS_PREFIX + CHANNEL_STATE_ICON_PROPERTY;
	public static final String RECEIVE_BYTES_PER_SEC_COLUMN_CLASS = COLUMN_CLASS_PREFIX + RECEIVE_BYTES_PER_SEC_PROPERTY; 
	public static final String SEND_BYTES_PER_SEC_COLUMN_CLASS = COLUMN_CLASS_PREFIX + SEND_BYTES_PER_SEC_PROPERTY;
	public static final String SEND_PACKETS_PER_SEC_COLUMN_CLASS = COLUMN_CLASS_PREFIX + SEND_PACKETS_PER_SEC_PROPERTY;
	public static final String REDOLOG_RETAINED_SIZE_COLUMN_CLASS = COLUMN_CLASS_PREFIX + REDOLOG_RETAINED_SIZE_PROPERTY;	
	
	public ChannelsPropertiesRow( String sourceName, String targetMemberName, String targetHostName, 
			String targetVersion, long targetPid, String channelState, long sendPacketsPerSec, 
			long redoLogRetainedSize, long sendBytesPerSec ) {

		this._sourceName = sourceName;
		this._targetMemberName = targetMemberName;
		this._targetHostName = targetHostName;
		this._targetVersion = targetVersion;
		this._targetPid = targetPid;
		this._channelState = channelState;
		this._sendPacketsPerSec = sendPacketsPerSec;
		this._redoLogRetainedSize = redoLogRetainedSize;
		this._sendBytesPerSec = sendBytesPerSec;
	}

	public String getSourceName() {
		return _sourceName;
	}	
	
	public String getTargetMemberName() {
		return _targetMemberName;
	}

	public String getTargetHostName() {
		return _targetHostName;
	}

	public String getTargetVersion() {
		return _targetVersion;
	}

	public long getTargetPid() {
		return _targetPid;
	}

	public String getChannelState() {
		return _channelState;
	}

	public long getSendPacketsPerSec() {
		return _sendPacketsPerSec;
	}

	public long getSedoLogRetainedSize() {
		return _redoLogRetainedSize;
	}

	public long getSendBytesPerSec() {
		return _sendBytesPerSec;
	}
	
	@Override
	public String toString() {
		return "ChannelPropertiesRow [targetMemberName=" + _targetMemberName
				+ ", targetHostName=" + _targetHostName + 
				", targetVersion=" + _targetVersion + 
				", targetPid=" + _targetPid +
				", channelState=" + _channelState + 
				", sendPacketsPerSec=" + _sendPacketsPerSec + 
				", redoLogRetainedSize=" + _redoLogRetainedSize + 
				", sendBytesPerSec=" + _sendBytesPerSec + "]";
	}	
}