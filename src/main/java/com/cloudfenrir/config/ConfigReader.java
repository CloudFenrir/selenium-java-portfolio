package com.cloudfenrir.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        // TODO: open config.properties using try-with-resources
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config", e);
        }
    }

    public static String get(String key) {

        if (props.getProperty(key) != null) {
            // TODO: get the value
            return props.getProperty(key);
        } else {
            // TODO: if it's null → throw RuntimeException("Missing key: " + key)
            throw new RuntimeException("Missing key: " + key);
        }
    }

    public static int getInt(String key) {
        // TODO: reuse get() and convert to int
        return Integer.parseInt(get(key));
    }

    // Temporary, just to test it
    public static void main(String[] args) {
        System.out.println(ConfigReader.get("baseUrl"));
        System.out.println(ConfigReader.getInt("timeout"));
    }
}