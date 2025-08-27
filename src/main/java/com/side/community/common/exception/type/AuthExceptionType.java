package com.side.community.common.exception.type;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthExceptionType implements ExceptionType {

    DUPLICATED_ACCOUNT_ID(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT,"이미 존재하는 닉네임입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    INVALID_CREDENTIALS(HttpStatus.CONFLICT, "아이디나 비밀번호가 잘못되었습니다.")
    ;

    private final HttpStatus status;
    private final String message;


    @Override
    public HttpStatus getHttpStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
