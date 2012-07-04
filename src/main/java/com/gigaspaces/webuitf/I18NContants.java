package com.gigaspaces.webuitf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class I18NContants {
	
	private static Properties props = new Properties();
	
	public static String get(String propName) {
		
		if (props.isEmpty()) {
			String propsFilePath = "I18NConstants.properties";
			try {
				props.load(new FileInputStream(propsFilePath));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props.getProperty(propName);
	}
	
	

}
