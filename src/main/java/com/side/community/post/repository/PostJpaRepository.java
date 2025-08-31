package com.side.community.post.repository;

import com.side.community.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long>, PostQuerydslRepository {
}
