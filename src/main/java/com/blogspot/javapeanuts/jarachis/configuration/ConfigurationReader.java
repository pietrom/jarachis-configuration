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
		URL rootUrl = getClass().getResource(prefix);
		this.root = new File(rootUrl.getFile());
	}

	public ConfigurationReader(File baseDir) {
		this.root = baseDir;
	}

	public Configuration readBaseConfiguration() throws IOException {
		Properties base = readBaseProperties();
		return new Configuration(base);
	}
	
	private Properties readBaseProperties() throws IOException {
		Properties result = new Properties();
		loadProperties(root, result);
		return result;
	}
	
	public Properties readProperties() throws IOException {
		return readBaseProperties();
	}

	public Properties readProperties(String key) throws IOException, InvalidPropertiesFormatException {
		Properties result = new Properties(readBaseProperties());
		File keyedRoot = new File(root, key);
		loadProperties(keyedRoot, result);
		return result;
	}
	
	private void loadProperties(File root, Properties properties) throws IOException {
		loadTraditionalPropertiesFiles(root, properties);
		loadXmlPropertiesFiles(root, properties);
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
		Properties config = readProperties(key);
		return new Configuration(config);
	}
}
