package com.gigaspaces.webuitf.datagrid.queriespanel;

import java.util.Map;

public class ResultRow {
	
	private Map<String, String> values;
	
	public ResultRow(Map<String, String> values) {
		this.values = values;
	}

	public String getValue(String field) {
		return values.get(field.toLowerCase());
	}
}
