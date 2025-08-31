package com.side.community.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import com.side.community.post.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostFindDetailResponseDto(
        Long postId,

        Long userId,

        Long categoryId,

        String categoryName,

        String userNickname,

        String title,

        String content,

        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", locale = "ko")
        LocalDateTime createdAt,

        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", locale = "ko")
        LocalDateTime updatedAt
) {

    @QueryProjection
    public PostFindDetailResponseDto {
    }

    public static PostFindDetailResponseDto from(Post post) {
        return PostFindDetailResponseDto.builder()
                .postId(post.getId())
                .userId(post.getUser().getId())
                .userNickname(post.getUser().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
