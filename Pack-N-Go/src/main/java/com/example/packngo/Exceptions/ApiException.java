package com.example.packngo.Exceptions;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
