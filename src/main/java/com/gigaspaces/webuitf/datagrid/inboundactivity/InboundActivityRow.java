package com.gigaspaces.webuitf.datagrid.inboundactivity;

/**
 * 
 * @author evgenyf
 * @since 9.7.0
 * 
 */
public class InboundActivityRow {

	private final String 	_spaceInstanceName; 
	private final String 	_spaceMode; 
	private final long 		_pid;
	private final String 	_hostIp;
	
	private final double 	_receivedTraffic;
	private final double 	_generatedTraffic; 
	private final double 	_totalTraffic;
	
	private final double 	_receivedTrafficMB;
	private final double 	_generatedTrafficMB; 
	private final double 	_totalTrafficMB;
	
	public InboundActivityRow( String spaceInstanceName, String spaceMode, long pid, String hostIp, 
							double receivedTraffic, double generatedTraffic, double totalTraffic,
							double receivedTrafficMB, double generatedTrafficMB, double totalTrafficMB ){

		_spaceInstanceName = spaceInstanceName; 
		_spaceMode = spaceMode; 
		_pid = pid;
		_hostIp = hostIp;
		_receivedTraffic = receivedTraffic;
		_generatedTraffic = generatedTraffic; 
		_totalTraffic = totalTraffic;		
		_receivedTrafficMB = receivedTrafficMB;
		_generatedTrafficMB = generatedTrafficMB; 
		_totalTrafficMB = totalTrafficMB;		
	}

	public String getSpaceInstanceName() {
		return _spaceInstanceName;
	}

	public String getSpaceMode() {
		return _spaceMode;
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
		return "InboundActivityRow [_spaceInstanceName=" + _spaceInstanceName
				+ ", _spaceMode=" + _spaceMode + ", _pid=" + _pid
				+ ", _hostIp=" + _hostIp + ", _receivedTraffic="
				+ _receivedTraffic + ", _generatedTraffic=" + _generatedTraffic
				+ ", _totalTraffic=" + _totalTraffic + "]";
	}
}