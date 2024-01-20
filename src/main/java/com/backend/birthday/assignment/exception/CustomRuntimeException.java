package com.backend.birthday.assignment.exception;

public class CustomRuntimeException extends RuntimeException {

    private final String message;
    private final String code;

    public CustomRuntimeException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
