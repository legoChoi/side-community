package com.side.community.post.service;

import com.side.community.category.entity.Category;
import com.side.community.category.repository.CategoryJpaRepository;
import com.side.community.common.exception.CustomRuntimeException;
import com.side.community.common.exception.type.CategoryExceptionType;
import com.side.community.common.exception.type.PostExceptionType;
import com.side.community.post.dto.request.PostCreateRequestDto;
import com.side.community.post.dto.request.PostUpdateRequestDto;
import com.side.community.post.dto.response.*;
import com.side.community.post.entity.Post;
import com.side.community.post.repository.PostJpaRepository;
import com.side.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;

    public PostCreateResponseDto createPost(PostCreateRequestDto dto, User user) {
        Category category = findCategoryById(dto.categoryId());

        Post post = Post.builder()
                .user(user)
                .category(category)
                .title(dto.title())
                .content(dto.content())
                .build();

        postJpaRepository.save(post);

        return PostCreateResponseDto.from(post);
    }

    public PostFindResponseDto findPost(Long postId) {
        return postJpaRepository.findPostWithProjection(postId)
                .orElseThrow(() -> new CustomRuntimeException(PostExceptionType.POST_NOT_FOUND));
    }

    public PostFindPageResponseDto findPostPage(Pageable pageable, String title, String content) {
        Page<PostInfoResponseDto> data = postJpaRepository.findPageWithConditions(pageable, title, content);
        return PostFindPageResponseDto.from(data);
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

    private Category findCategoryById(Long categoryId) {
        return categoryJpaRepository.findById(categoryId)
                .orElseThrow(() -> new CustomRuntimeException(CategoryExceptionType.CATEGORY_NOT_FOUND));
    }
}
