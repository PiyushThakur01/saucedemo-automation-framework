package com.qa.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Reads values from config.properties.
 * Any value can be overridden from the command line, e.g. mvn test -Dbrowser=firefox -Dheadless=true
 */
public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        return System.getProperty(key, props.getProperty(key));
    }
}
