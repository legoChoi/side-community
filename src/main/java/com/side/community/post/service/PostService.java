package com.side.community.post.service;

import com.side.community.common.exception.CustomRuntimeException;
import com.side.community.common.exception.type.PostExceptionType;
import com.side.community.post.dto.request.PostCreateRequestDto;
import com.side.community.post.dto.request.PostUpdateRequestDto;
import com.side.community.post.dto.response.PostCreateResponseDto;
import com.side.community.post.dto.response.PostUpdateResponseDto;
import com.side.community.post.entity.Post;
import com.side.community.post.repository.PostJpaRepository;
import com.side.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;

    public PostCreateResponseDto createPost(PostCreateRequestDto request, User user) {
        Post post = new Post(request.title(), request.content(), user);
        postJpaRepository.save(post);

        return PostCreateResponseDto.from(post);
    }

    @Transactional
    public PostUpdateResponseDto updatePost(Long postId, PostUpdateRequestDto dto, User user) {
        Post post = findPostById(postId);

        if (isPostOwner(user.getId(), post.getUser().getId())) {
            throw new CustomRuntimeException(PostExceptionType.POST_UPDATE_ACCESS_DENIED);
        }

        post.update(dto);

        return PostUpdateResponseDto.from(post);
    }

    @Transactional
    public void deletePost(Long postId, User user) {
        Post post = findPostById(postId);

        if (isPostOwner(user.getId(), post.getUser().getId())) {
            throw new CustomRuntimeException(PostExceptionType.POST_DELETE_ACCESS_DENIED);
        }

        post.delete();
    }

    private boolean isPostOwner(Long requestUserId, Long postOwnerId) {
        return requestUserId.equals(postOwnerId);
    }

    private Post findPostById(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new CustomRuntimeException(PostExceptionType.POST_NOT_FOUND));
    }
}
