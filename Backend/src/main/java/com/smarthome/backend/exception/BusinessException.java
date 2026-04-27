package com.smarthome.backend.exception;

public class BusinessException extends AppException {
    public BusinessException(String code, String message) {
        super(code, message);
    }
}