package com.gigaspaces.webuitf.datagrid.notificationsactivity;

/**
 * 
 * @author evgenyf
 * @since 9.7.0
 * 
 */
public class NotificationsActivityRow {

	private final String 	_gscName; 
	private final long 		_pid;
	private final String 	_hostIp;
	
	private final long _invocationCount;
	
	private final double 	_receivedTraffic;
	private final double 	_generatedTraffic; 
	private final double 	_totalTraffic;
	
	private final double 	_receivedTrafficMB;
	private final double 	_generatedTrafficMB; 
	private final double 	_totalTrafficMB;
	
	public NotificationsActivityRow( String gscName, long pid, String hostIp,
							long invocationCount,
							double receivedTraffic, double generatedTraffic, double totalTraffic,
							double receivedTrafficMB, double generatedTrafficMB, double totalTrafficMB ){

		_gscName = gscName; 
		_pid = pid;
		_hostIp = hostIp;
		
		_invocationCount = invocationCount;
		
		_receivedTraffic = receivedTraffic;
		_generatedTraffic = generatedTraffic; 
		_totalTraffic = totalTraffic;		
		
		_receivedTrafficMB = receivedTrafficMB;
		_generatedTrafficMB = generatedTrafficMB; 
		_totalTrafficMB = totalTrafficMB;		
	}

	public String getGscName() {
		return _gscName;
	}

	public long getInvocationCount() {
		return _invocationCount;
	}

	public long getPid() {
		return _pid;
	}

	public String getHostIp() {
		return _hostIp;
	}

	public double getReceivedTraffic() {
		return _receivedTraffic;
	}

	public double getGeneratedTraffic() {
		return _generatedTraffic;
	}

	public double getTotalTraffic() {
		return _totalTraffic;
	}
	
	public double getReceivedTrafficMB() {
		return _receivedTrafficMB;
	}

	public double getGeneratedTrafficMB() {
		return _generatedTrafficMB;
	}

	public double getTotalTrafficMB() {
		return _totalTrafficMB;
	}

	@Override
	public String toString() {
		return "NotificationActivityRow [_gscName=" + _gscName + ", _pid="
				+ _pid + ", _hostIp=" + _hostIp + ", _invocationCount="
				+ _invocationCount + ", _receivedTraffic=" + _receivedTraffic
				+ ", _generatedTraffic=" + _generatedTraffic
				+ ", _totalTraffic=" + _totalTraffic + ", _receivedTrafficMB="
				+ _receivedTrafficMB + ", _generatedTrafficMB="
				+ _generatedTrafficMB + ", _totalTrafficMB=" + _totalTrafficMB + "]";
	}	
}