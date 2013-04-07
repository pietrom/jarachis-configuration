package com.blogspot.javapeanuts.jarachis.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
		InputStream[] props = loadResourceStreams(root, ".*[.]properties");
		for (int i = 0; i < props.length; i++) {
			result.load(props[i]);
		}
	}

	private InputStream[] loadResourceStreams(File root, final String pattern) throws IOException {
		final String path = root.getPath();
		if(path.contains(".jar!")) {
			final String jarName = path.substring(0, path.indexOf('!'));
			final JarFile jarFile = new JarFile(new URL(jarName).getFile());
			ZipInputStream zip = new ZipInputStream(new URL(jarName).openStream());
			final String rootName = path.substring(path.indexOf('!') + 2);
			ZipEntry entry = null;
			List<InputStream> resources = new LinkedList<InputStream>();
			while((entry = zip.getNextEntry()) != null) {
				String entryName = entry.getName();
				if(entryName.matches(rootName + "/" + pattern)) {
					resources.add(jarFile.getInputStream(entry));
				}
			}
			return resources.toArray(new InputStream[resources.size()]);
		} else {
			File[] files = root.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.matches(pattern);
				}
			});
			InputStream[] resources = new InputStream[files.length];
			for (int i = 0; i < files.length; i++) {
				resources[i] = new FileInputStream(files[i]);
			}
			return resources;
		}
	}

	private void loadXmlPropertiesFiles(File root, Properties result) throws InvalidPropertiesFormatException, IOException {
		InputStream[] xmls = loadResourceStreams(root, ".*[.]xml");
		for (int i = 0; i < xmls.length; i++) {
			result.loadFromXML(xmls[i]);
		}
	}

	public Configuration readConfiguration(String key) throws InvalidPropertiesFormatException, IOException {
		Properties config = readProperties(key);
		return new Configuration(config);
	}
}
