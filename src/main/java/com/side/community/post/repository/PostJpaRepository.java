package com.side.community.post.repository;

import com.side.community.post.dto.response.PostFindResponseDto;
import com.side.community.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    @Query("SELECT new com.side.community.post.dto.response.PostFindResponseDto(" +
            "p.id, u.id, c.id, c.name, u.nickname, p.title, p.content, p.createdAt, p.updatedAt) " +
            "FROM Post p INNER JOIN p.user u INNER JOIN p.category c WHERE p.id = :postId")
    Optional<PostFindResponseDto> findPostWithProjection(Long postId);
}
