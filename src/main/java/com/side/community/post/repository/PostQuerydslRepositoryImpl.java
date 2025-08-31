package com.side.community.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.side.community.post.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.side.community.category.entity.QCategory.category;
import static com.side.community.post.entity.QPost.post;
import static com.side.community.user.entity.QUser.user;

@RequiredArgsConstructor
public class PostQuerydslRepositoryImpl implements PostQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PostFindDetailResponseDto> findPostDetailById(Long postId) {
        PostFindDetailResponseDto data = queryFactory.select(new QPostFindDetailResponseDto(
                        post.id,
                        user.id,
                        category.id,
                        category.name,
                        user.nickname,
                        post.title,
                        post.content,
                        post.createdAt,
                        post.updatedAt
                ))
                .from(post)
                .where(
                        post.id.eq(postId),
                        post.deletedAt.isNull()
                )
                .fetchOne();

        return Optional.ofNullable(data);
    }

    @Override
    public Page<PostFindInfoResponseDto> findPostInfoPageByConditions(Pageable pageable, String title, String content) {
        List<PostFindInfoResponseDto> data = queryFactory.select(new QPostFindInfoResponseDto(
                        post.id,
                        user.id,
                        category.id,
                        category.name,
                        user.nickname,
                        post.title,
                        post.createdAt
                ))
                .from(post)
                .join(post.user, user)
                .join(post.category, category)
                .where(
                        post.deletedAt.isNull(),
                        eqTitle(title),
                        eqContent(content)
                )
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(post.count())
                .from(post)
                .where(
                        post.deletedAt.isNull(),
                        eqTitle(title),
                        eqContent(content)
                )
                .fetchOne();

        return new PageImpl<>(data, pageable, count);
    }

    private BooleanExpression eqTitle(String title) {
        return StringUtils.hasText(title) ? post.title.eq(title) : null;
    }

    private BooleanExpression eqContent(String content) {
        return StringUtils.hasText(content) ? post.content.eq(content) : null;
    }
}
