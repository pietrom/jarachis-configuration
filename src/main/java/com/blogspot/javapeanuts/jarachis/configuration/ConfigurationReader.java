package com.blogspot.javapeanuts.jarachis.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class ConfigurationReader {
	private final File root;

	public ConfigurationReader(String prefix) {
		URL rootUrl = getClass().getClassLoader().getResource(prefix);
		this.root = new File(rootUrl.getFile());
	}

	public Configuration readBaseConfiguration() throws IOException {
		Properties result = loadProperties("");
		return new Configuration(result);
	}

	private Properties loadProperties(String key) throws IOException, InvalidPropertiesFormatException {
		Properties result = null;
		if ("".equals(key)) {
			result = new Properties();
		} else {
			result = new Properties(loadProperties(""));
		}
		File keyedRoot = new File(root, key);
		loadTraditionalPropertiesFiles(keyedRoot, result);
		loadXmlPropertiesFiles(keyedRoot, result);
		return result;
	}

	private void loadTraditionalPropertiesFiles(File root, Properties result) throws IOException {
		String[] props = root.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(".*[.]properties");
			}
		});
		for (int i = 0; i < props.length; i++) {
			InputStream input = new FileInputStream(new File(root, props[i]));
			result.load(input);
		}
	}

	private void loadXmlPropertiesFiles(File root, Properties result) throws InvalidPropertiesFormatException, IOException {
		String[] xmls = root.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(".*[.]xml");
			}
		});
		for (int i = 0; i < xmls.length; i++) {
			InputStream input = new FileInputStream(new File(root, xmls[i]));
			result.loadFromXML(input);
		}
	}

	public Configuration readConfiguration(String key) throws InvalidPropertiesFormatException, IOException {
		Properties config = loadProperties(key);
		return new Configuration(config);
	}
}
