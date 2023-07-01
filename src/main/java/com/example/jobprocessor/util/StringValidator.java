package com.example.jobprocessor.util;

public class StringValidator {
    public static boolean isNotNullNorEmpty(final String string) {
        return string != null && !string.trim().equals("");
    }
}
