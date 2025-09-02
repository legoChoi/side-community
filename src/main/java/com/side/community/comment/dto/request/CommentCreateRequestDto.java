package com.side.community.comment.dto.request;

public record CommentCreateRequestDto(
        Long postId,

        String content
) {
}
