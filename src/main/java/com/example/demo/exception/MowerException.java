package com.example.demo.exception;

public class MowerException
        extends RuntimeException {
    public MowerException(String errorMessage) {
        super(errorMessage);
    }
}
