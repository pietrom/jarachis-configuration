package com.blogspot.javapeanuts.jarachis.configuration;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationReaderTest {
	@Test
	public void readConfigurationByClassLoaderGivenPrefix() throws Exception {
		ConfigurationReader reader = new ConfigurationReader();
		assertNotNull(getClass().getResourceAsStream("/simple/simple01.properties"));
		Configuration configuration = reader.readConfiguration("simple");
		assertEquals(Integer.valueOf(1), configuration.getInteger("a"));
		assertEquals("2", configuration.getString("b"));
	}
}
