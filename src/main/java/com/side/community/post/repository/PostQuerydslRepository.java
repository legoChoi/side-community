package com.side.community.post.repository;

import com.side.community.post.dto.response.PostInfoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostQuerydslRepository {

    Page<PostInfoResponseDto> findPageWithConditions(Pageable pageable, String title, String content);
}
