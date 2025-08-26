package com.side.community.common.exception;

import com.side.community.common.exception.type.ExceptionType;
import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

    private final ExceptionType exceptionType;

    public CustomRuntimeException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
