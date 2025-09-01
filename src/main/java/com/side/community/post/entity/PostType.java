package com.side.community.post.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostType {

    NORMAL("일반"),
    NOTICE("공지"),
    ;

    private final String type;
}
