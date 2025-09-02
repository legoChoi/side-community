package com.side.community.comment.dto.response;

import com.side.community.comment.entity.Comment;

public record CommentCreateResponseDto(
        Long commentId
) {

    public static CommentCreateResponseDto from(Comment comment) {
        return new CommentCreateResponseDto(comment.getId());
    }
}
