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
		return Integer.valueOf(get(key));
	}
}
