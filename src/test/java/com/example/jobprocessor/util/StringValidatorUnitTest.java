package com.example.jobprocessor.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringValidatorUnitTest {

    @Test
    public void givenNonEmptyStringThenReturnIsValid() {
        assertTrue(StringValidator.isNotNullNorEmpty("test"));
    }

    @Test
    public void givenTrimmedToEmptyStringThenReturnIsNotValid() {
        assertFalse(StringValidator.isNotNullNorEmpty("  "));
    }

    @Test
    public void givenEmptyStringThenReturnIsNotValid() {
        assertFalse(StringValidator.isNotNullNorEmpty(""));
    }

    @Test
    public void givenNullStringThenReturnIsNotValid() {
        assertFalse(StringValidator.isNotNullNorEmpty(null));
    }
}
