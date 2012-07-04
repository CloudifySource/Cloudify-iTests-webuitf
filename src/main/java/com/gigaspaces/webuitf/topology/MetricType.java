package com.gigaspaces.webuitf.topology;

public class MetricType {

	private String type;
	private String name;
	private String distrib;

	public MetricType(String type, String name, String dist) {
		this.name = name;
		this.type = type;
		this.distrib = dist;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public String getDistrib() {
		return distrib;
	}

}
