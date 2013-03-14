package com.blogspot.javapeanuts.jarachis.configuration;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationReaderTest {
	@Test
	public void readConfigurationByClassLoaderGivenPrefix() throws Exception {
		ConfigurationReader reader = new ConfigurationReader("simple");
		Configuration configuration = reader.readBaseConfiguration();
		assertEquals(Integer.valueOf(1), configuration.getInteger("a"));
		assertEquals("2", configuration.getString("b"));
		assertEquals(Integer.valueOf(3), configuration.getInteger("z"));
	}
	
	@Test
	public void readConfigurationByClassLoaderGivenPrefixAndPropertiesFileInBothTraditionalAndXmlFormat() throws Exception {
		ConfigurationReader reader = new ConfigurationReader("misc");
		Configuration configuration = reader.readBaseConfiguration();
		assertEquals(Integer.valueOf(1), configuration.getInteger("a"));
		assertEquals("2", configuration.getString("b"));
		assertEquals(Integer.valueOf(33), configuration.getInteger("z"));
	}
	
	@Test
	public void readConfigurationByKey() throws Exception {
		ConfigurationReader reader = new ConfigurationReader("config");
		Configuration configuration = reader.readConfiguration("sub01");
		assertEquals("aa", configuration.getString("b1"));
		assertEquals(Integer.valueOf(22), configuration.getInteger("s1_1_2"));
	}
}
