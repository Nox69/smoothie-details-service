package com.nox.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class ErrorDetails {

    private List<String> errors;

    public ErrorDetails(List<String> errors) {
        super();
        this.errors = errors;
    }

    public ErrorDetails(String error) {
        super();
        errors = Arrays.asList(error);
    }
}
