package com.blogspot.javapeanuts.jarachis.configuration;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigurationReader {

	public Configuration readConfiguration(String prefix) throws IOException {
		Properties result = new Properties();
		URL rootUrl = getClass().getClassLoader().getResource(prefix);
		File root = new File(rootUrl.getFile());
		String[] props = root.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(".*[.]properties");
			}
		});
		for (int i = 0; i < props.length; i++) {
			InputStream input = getClass().getResourceAsStream("/" + prefix + "/" + props[i]);
			result.load(input);
		}
		String[] xmls = root.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(".*[.]xml");
			}
		});
		for (int i = 0; i < xmls.length; i++) {
			InputStream input = getClass().getResourceAsStream("/" + prefix + "/" + xmls[i]);
			result.loadFromXML(input);
		}
		return new Configuration(result);
	}
}
