package com.side.community.common.exception;

import org.springframework.http.HttpStatus;

public record ExceptionResponseDto(
        HttpStatus httpStatus,
        String message
) {
}
