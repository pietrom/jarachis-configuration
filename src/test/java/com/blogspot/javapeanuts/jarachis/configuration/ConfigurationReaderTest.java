package com.blogspot.javapeanuts.jarachis.configuration;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;

public class ConfigurationReaderTest {
	@Test
	public void readConfigurationByClassLoaderGivenPrefix() throws Exception {
		ConfigurationReader reader = new ConfigurationReader("/simple");
		Configuration configuration = reader.readBaseConfiguration();
		assertEquals(Integer.valueOf(1), configuration.getInteger("a"));
		assertEquals("2", configuration.getString("b"));
		assertEquals(Integer.valueOf(3), configuration.getInteger("z"));
	}
	
	@Test
	public void readConfigurationByClassLoaderGivenPrefixAndPropertiesFileInBothTraditionalAndXmlFormat() throws Exception {
		ConfigurationReader reader = new ConfigurationReader("/misc");
		Configuration configuration = reader.readBaseConfiguration();
		assertEquals(Integer.valueOf(1), configuration.getInteger("a"));
		assertEquals("2", configuration.getString("b"));
		assertEquals(Integer.valueOf(33), configuration.getInteger("z"));
	}
	
	@Test
	public void readPropertiesByClassLoaderGivenPrefixAndPropertiesFileInBothTraditionalAndXmlFormat() throws Exception {
		ConfigurationReader reader = new ConfigurationReader("/misc");
		Properties configuration = reader.readProperties();
		assertEquals("1", configuration.getProperty("a"));
		assertEquals("2", configuration.getProperty("b"));
		assertEquals("33", configuration.getProperty("z"));
	}
	
	@Test
	public void readConfigurationByKey() throws Exception {
		ConfigurationReader reader = new ConfigurationReader("/config");
		Configuration configuration = reader.readConfiguration("sub01");
		assertEquals("aa", configuration.getString("b1"));
		assertEquals(Integer.valueOf(22), configuration.getInteger("s1_1_2"));
	}
	
	@Test
	public void readPropertiesByKey() throws Exception {
		ConfigurationReader reader = new ConfigurationReader("/config");
		Properties properties = reader.readProperties("sub01");
		assertEquals("aa", properties.getProperty("b1"));
		assertEquals("22", properties.getProperty("s1_1_2"));
	}
	
	@Test
	public void canOverrideBasePropertiesInKeyedConfiguration() throws Exception {
		ConfigurationReader reader = new ConfigurationReader("/config");
		Configuration configuration = reader.readConfiguration("sub01");
		assertEquals("overriden", configuration.getString("b2"));
	}
	
	@Test
	public void canOverrideBasePropertiesInKeyedProperties() throws Exception {
		ConfigurationReader reader = new ConfigurationReader("/config");
		Properties configuration = reader.readProperties("sub01");
		assertEquals("overriden", configuration.getProperty("b2"));
	}
}
