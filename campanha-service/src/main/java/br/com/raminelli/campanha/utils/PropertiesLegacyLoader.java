package br.com.raminelli.campanha.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesLegacyLoader {

	private Properties props;
	private String file = "legagy.properties";

	private static PropertiesLegacyLoader instance;
	
	public static PropertiesLegacyLoader getInstance() {
		if(instance == null) {
			instance = new PropertiesLegacyLoader();
		}
		return instance;
	}
	
	private PropertiesLegacyLoader() {
		props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Set<Object> getListKeys() {
		return props.keySet();
	}
	
	public String getValue(String key) {
		return (String) props.getProperty(key);
	}

}
