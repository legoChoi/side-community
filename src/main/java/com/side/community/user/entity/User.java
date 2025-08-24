package com.side.community.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountId;

    @Column(nullable = false)
    private String accountPassword;

    private String nickname;

    @Builder
    public User(String accountId, String accountPassword, String nickname) {
        this.accountId = accountId;
        this.accountPassword = accountPassword;
        this.nickname = nickname;
    }
}
