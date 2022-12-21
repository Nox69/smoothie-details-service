package com.nox.exception;

public class SmoothieNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SmoothieNotFoundException(String message) {
        super(message);
    }
}
