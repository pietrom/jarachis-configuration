package com.blogspot.javapeanuts.jarachis.configuration;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationReaderTest {
	@Test
	public void readConfigurationByClassLoaderGivenPrefix() throws Exception {
		ConfigurationReader reader = new ConfigurationReader();
		Configuration configuration = reader.readConfiguration("simple");
		assertEquals(Integer.valueOf(1), configuration.getInteger("a"));
		assertEquals("2", configuration.getString("b"));
		assertEquals(Integer.valueOf(3), configuration.getInteger("z"));
	}
	
	@Test
	public void readConfigurationByClassLoaderGivenPrefixAndPropertiesFileInBothTraditionalAndXmlFormat() throws Exception {
		ConfigurationReader reader = new ConfigurationReader();
		Configuration configuration = reader.readConfiguration("misc");
		assertEquals(Integer.valueOf(1), configuration.getInteger("a"));
		assertEquals("2", configuration.getString("b"));
		assertEquals(Integer.valueOf(33), configuration.getInteger("z"));
	}
}
