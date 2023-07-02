package com.example.jobprocessor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CircularDependencyException.class)
    public ResponseEntity<String> handleCircularDependencyException(final CircularDependencyException ex) {
        LOGGER.error(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BusinessLogicValidationException.class)
    public ResponseEntity<String> handleBusinessLogicValidationException(final BusinessLogicValidationException ex) {
        LOGGER.error(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}