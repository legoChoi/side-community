package com.side.community.common.exception;

import com.side.community.common.exception.type.ExceptionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> handleCustomRuntimeException(CustomRuntimeException e) {
        return buildExceptionResponse(e.getExceptionType());
    }


    ResponseEntity<ExceptionResponseDto> buildExceptionResponse(ExceptionType e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ExceptionResponseDto(e.getHttpStatus(), e.getMessage()));
    }
}
