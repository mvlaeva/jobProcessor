package com.example.jobprocessor.exception;

public class BusinessLogicValidationException extends RuntimeException {
    public BusinessLogicValidationException(final String message) {
        super(message);
    }
}
