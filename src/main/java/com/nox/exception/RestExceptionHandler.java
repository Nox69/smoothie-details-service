package com.nox.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.SignatureException;

/**
 * This class contains all exception handling logic for the API. It is responsible to translate thrown exceptions to well-structured API responses.
 * Each method annotated with `@ExceptionHandler` is responsible for handling related exceptions.
 **/
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        List<String> errorList = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorDetails errorDetails = new ErrorDetails(errorList);
        return handleExceptionInternal(ex, errorDetails, headers, HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(value = { SignatureException.class })
    protected ResponseEntity<Object> handleInvalidJWT(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    
    @ExceptionHandler(value = { UnAuthorizedException.class })
    protected ResponseEntity<Object> handleUnAuthorizedJWT(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
