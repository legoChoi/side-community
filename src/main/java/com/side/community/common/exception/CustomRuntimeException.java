package com.side.community.common.exception;

public class CustomRuntimeException extends RuntimeException {

    private final ExceptionType exceptionType;

    public CustomRuntimeException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
