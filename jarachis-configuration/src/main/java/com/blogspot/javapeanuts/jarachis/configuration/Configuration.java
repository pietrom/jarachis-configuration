package com.blogspot.javapeanuts.jarachis.configuration;

import java.util.Properties;

public class Configuration {
	private final Properties properties;

	public Configuration(Properties properties) {
		this.properties = properties;
	}

	private String get(String key) {
		return properties.getProperty(key);
	}
	
	public String getString(String key) {
		return get(key);
	}

	public Integer getInteger(String key) {
		final String prop = get(key);
		Integer val = null;
		if(prop != null) {
			val = Integer.valueOf(prop);
		}
		return val;
	}

	public Double getDouble(String key) {
		final String prop = get(key);
		Double val = null;
		if(prop != null) {
			val = Double.valueOf(get(key));
		}
		return val;
	}
}
