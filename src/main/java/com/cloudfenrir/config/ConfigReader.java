package com.cloudfenrir.config;

import com.cloudfenrir.utils.FrameworkException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        // open config.properties using try-with-resources
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new FrameworkException("config.properties not found in classpath");
            }
            props.load(input);
        } catch (IOException e) {
            throw new FrameworkException("Could not load config", e);
        }
    }

    public static String get(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            value = System.getenv(key.toUpperCase());
        }
        if (value == null) {
            value = props.getProperty(key);
        }
        if (value == null) {
            throw new FrameworkException("Missing key: " + key);
        }
        return value;
    }

    public static int getInt(String key) {
        // reuse get() and convert to int
        return Integer.parseInt(get(key));
    }

}