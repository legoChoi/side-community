package com.side.community.post.dto.response;

import com.side.community.post.entity.Post;

public record PostCreateResponseDto(
        Long postId
) {

    public static PostCreateResponseDto from(Post post) {
        return new PostCreateResponseDto(post.getId());
    }
}
