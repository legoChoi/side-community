package com.side.community.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.side.community.post.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostFindResponseDto(
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

    public static PostFindResponseDto from(Post post) {
        return PostFindResponseDto.builder()
                .postId(post.getId())
                .userId(post.getUser().getId())
                .userNickname(post.getUser().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
