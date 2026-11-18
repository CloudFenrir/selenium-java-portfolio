package com.cloudfenrir.driver;

import com.cloudfenrir.utils.FrameworkException;

import java.util.Arrays;

public enum Browser {

    CHROME, FIREFOX, EDGE;

    public static Browser fromConfig(String value) {
        try {
            return valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException  e) {
            throw new FrameworkException("Unsupported option: " + value + "\n Valid options: " + Arrays.toString(values()), e);
        }
    }

}
