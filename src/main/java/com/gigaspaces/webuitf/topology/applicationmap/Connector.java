package com.gigaspaces.webuitf.topology.applicationmap;

public class Connector {
	
	private final String source;
	private final String target;
	private final String status;
	
	public Connector( String source, String target, String status ) {
		this.source = source;
		this.target = target;
		this.status = status;
	}
	
	public String getSource() {
		return source;
	}
	public String getTarget() {
		return target;
	}
	
	public String getStatus() {
		return status;
	}
}