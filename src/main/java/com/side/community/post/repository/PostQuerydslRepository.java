package com.side.community.post.repository;

import com.side.community.post.dto.response.PostFindDetailResponseDto;
import com.side.community.post.dto.response.PostFindInfoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostQuerydslRepository {

    Optional<PostFindDetailResponseDto> findPostDetailById(Long postId);

    Page<PostFindInfoResponseDto> findPostInfoPageByConditions(Pageable pageable, String title, String content);
}
