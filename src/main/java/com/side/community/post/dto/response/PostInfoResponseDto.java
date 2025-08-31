package com.side.community.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record PostInfoResponseDto(
        Long postId,

        Long userId,

        Long categoryId,

        String categoryName,

        String userNickname,

        String title,

        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", locale = "ko")
        LocalDateTime createdAt
) {

    @QueryProjection
    public PostInfoResponseDto {
    }
}
