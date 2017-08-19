package br.com.raminelli.socio.torcedor.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesConfigLoader {

	private Properties props;
	private String file = "config.properties";

	private static PropertiesConfigLoader instance;
	
	public static PropertiesConfigLoader getInstance() {
		if(instance == null) {
			instance = new PropertiesConfigLoader();
		}
		return instance;
	}
	
	private PropertiesConfigLoader() {
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
