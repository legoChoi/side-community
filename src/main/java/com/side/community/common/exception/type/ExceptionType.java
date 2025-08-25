package com.side.community.common.exception.type;


import org.springframework.http.HttpStatus;

public interface ExceptionType {

    HttpStatus getHttpStatus();
    String getMessage();
}
