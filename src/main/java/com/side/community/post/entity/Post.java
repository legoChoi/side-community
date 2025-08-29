package com.side.community.post.entity;

import com.side.community.common.entity.BaseEntity;
import com.side.community.post.dto.request.PostUpdateRequestDto;
import com.side.community.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "posts")

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(PostUpdateRequestDto dto) {
        updateTitle(dto.title());
        updateContent(dto.content());
    }

    public void updateTitle(String title) {
        if (StringUtils.hasText(title)) {
            this.title = title;
        }
    }

    public void updateContent(String content) {
        if (StringUtils.hasText(content)) {
            this.content = content;
        }
    }
}
