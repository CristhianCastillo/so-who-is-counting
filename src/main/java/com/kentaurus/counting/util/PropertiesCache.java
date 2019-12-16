package com.kentaurus.counting.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesCache {
    private final Properties configProp = new Properties();

    private PropertiesCache() {
        System.out.println(new File(".").getAbsolutePath());
//        InputStream in = PropertiesCache.class.getResourceAsStream("messages.properties");
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("./messages.properties");
        try {
//			FileInputStream in = new FileInputStream("messages.properties");
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class LazyHolder {
        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }

    public static PropertiesCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }

    public void setProperty(String key, String value) {
        configProp.setProperty(key, value);
    }
}
