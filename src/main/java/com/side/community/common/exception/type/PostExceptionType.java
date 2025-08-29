package com.side.community.common.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostExceptionType implements ExceptionType {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글 정보를 찾을 수 없습니다."),
    POST_UPDATE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "게시글 수정 권한이 없습니다."),
    POST_DELETE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "게시글 삭제 권한이 없습니다."),
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
