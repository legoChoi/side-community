package com.side.community.post.dto.request;

public record PostCreateRequestDto(
        Long categoryId,

        String title,

        String content
) {
}
