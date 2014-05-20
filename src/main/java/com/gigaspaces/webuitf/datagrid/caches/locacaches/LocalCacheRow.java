package com.gigaspaces.webuitf.datagrid.caches.locacaches;

/**
 * 
 * @author evgenyf
 * @since 9.5.0
 * 
 */
public class LocalCacheRow {

	private final String _id;
	private final String _version;
	private final String _hostName;
	private final long _pid;
	
	public LocalCacheRow( String id, String version, String hostName, long pid ){
		_id = id;
		_version = version;
		_hostName = hostName;
		_pid = pid;
	}

	public String getId(){
		return _id;
	}
	
	public String getVersion() {
		return _version;
	}

	public String getHostName() {
		return _hostName;
	}

	public long getPid() {
		return _pid;
	}

	@Override
	public String toString() {
		return "LocalCacheRow [version=" + _version + ", hostName="
				+ _hostName + ", pid=" + _pid + "]";
	}
}