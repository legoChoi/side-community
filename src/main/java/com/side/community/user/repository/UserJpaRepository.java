package com.side.community.user.repository;

import com.side.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    boolean existsByAccountId(String accountId);

    boolean existsByNickname(String nickname);

    Optional<User> findByAccountId(String accountId);
}
