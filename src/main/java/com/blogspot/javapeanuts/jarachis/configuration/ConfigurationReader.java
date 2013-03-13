package com.blogspot.javapeanuts.jarachis.configuration;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigurationReader {

	public Configuration readConfiguration(String prefix) throws IOException {
		Properties result = new Properties();
		Enumeration<URL> urls = getClass().getClassLoader().getResources(prefix);
		while(urls.hasMoreElements()) {
			URL url = urls.nextElement();
			result.load(url.openStream());
		}
		return new Configuration(result);
	}
}
