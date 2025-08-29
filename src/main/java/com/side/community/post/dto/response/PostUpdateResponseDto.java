package com.side.community.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.side.community.post.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostUpdateResponseDto(
        Long postId,

        String title,

        String content,

        @JsonFormat(pattern = "yyyy년 MM월 dd일")
        LocalDateTime createdAt,

        @JsonFormat(pattern = "yyyy년 MM월 dd일")
        LocalDateTime updatedAt
) {

    public static PostUpdateResponseDto from(Post post) {
        return PostUpdateResponseDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
