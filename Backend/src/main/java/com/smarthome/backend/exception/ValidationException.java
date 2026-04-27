package com.smarthome.backend.exception;

public class ValidationException extends AppException {
    public ValidationException(String code, String message) {
        super(code, message);
    }
}