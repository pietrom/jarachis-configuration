package com.blogspot.javapeanuts.jarachis.configuration;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Enumeration;

import org.junit.Test;
import static org.junit.Assert.*;

public class GetResourcesTest {
	@Test
	public void getResourcesByCompleteName() throws Exception {
		URL rootUrl = getClass().getClassLoader().getResource("simple");
		File root = new File(rootUrl.getFile());
		String[] props = root.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(".*[.]properties");
			}
		});
		assertEquals(2, props.length);
	}
}
