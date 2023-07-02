package com.example.jobprocessor.exception;

public class CircularDependencyException extends RuntimeException {
    public CircularDependencyException(final String message) {
        super(message);
    }
}
