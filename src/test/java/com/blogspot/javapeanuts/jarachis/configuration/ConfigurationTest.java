package com.blogspot.javapeanuts.jarachis.configuration;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

public class ConfigurationTest {
	private static final String KEY = "a-key";
	private static final String VALUE = "aValue";
	private static final String INT_VALUE = "19";

	@Test
	public void getPropertyAsString() throws Exception {
		Properties props = new Properties();
		props.setProperty(KEY, VALUE);
		Configuration configuration = new Configuration(props);
		assertEquals(VALUE, configuration.getString(KEY));
	}
	
	@Test
	public void getPropertyAsInt() throws Exception {
		Properties props = new Properties();
		props.setProperty(KEY, INT_VALUE);
		Configuration configuration = new Configuration(props);
		assertEquals(Integer.valueOf(INT_VALUE), configuration.getInteger(KEY));
	}
}
