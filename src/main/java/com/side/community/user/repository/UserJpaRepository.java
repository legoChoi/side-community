package com.side.community.user.repository;

import com.side.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    boolean existsByAccountId(String accountId);
}
