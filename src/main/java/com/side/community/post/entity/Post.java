package com.side.community.post.entity;

import com.side.community.category.entity.Category;
import com.side.community.common.entity.BaseEntity;
import com.side.community.post.dto.request.PostUpdateRequestDto;
import com.side.community.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Builder
    public Post(User user, Category category, String title, String content, PostType postType) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.postType = postType;
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
