package com.side.community.comment.service;

import com.side.community.comment.dto.request.CommentCreateRequestDto;
import com.side.community.comment.dto.request.CommentUpdateRequestDto;
import com.side.community.comment.dto.response.CommentCreateResponseDto;
import com.side.community.comment.dto.response.CommentUpdateResponseDto;
import com.side.community.comment.entity.Comment;
import com.side.community.comment.repository.CommentJpaRepository;
import com.side.community.common.exception.CustomRuntimeException;
import com.side.community.common.exception.type.CommentExceptionType;
import com.side.community.common.exception.type.PostExceptionType;
import com.side.community.post.entity.Post;
import com.side.community.post.repository.PostJpaRepository;
import com.side.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostJpaRepository postJpaRepository;
    private final CommentJpaRepository commentJpaRepository;

    public CommentCreateResponseDto createComment(CommentCreateRequestDto dto, User user) {
        Post post = findPostById(dto.postId());

        Comment comment = new Comment(user, post, dto.content());

        commentJpaRepository.save(comment);

        return CommentCreateResponseDto.from(comment);
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto dto, User user) {
        Comment comment = findCommentById(commentId);

        if (isNotCommentOwner(user.getId(), comment.getUser().getId())) {
            throw new CustomRuntimeException(CommentExceptionType.COMMENT_UPDATE_ACCESS_DENIED);
        }

        comment.update(dto.content());

        return CommentUpdateResponseDto.from(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, User user) {
        Comment comment = findCommentById(commentId);

        if (isNotCommentOwner(user.getId(), comment.getUser().getId())) {
            throw new CustomRuntimeException(CommentExceptionType.COMMENT_DELETE_ACCESS_DENIED);
        }

        comment.delete();
    }

    private boolean isNotCommentOwner(Long requestUserId, Long ownerId) {
        return !requestUserId.equals(ownerId);
    }

    private Post findPostById(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new CustomRuntimeException(PostExceptionType.POST_NOT_FOUND));
    }

    private Comment findCommentById(Long commentId) {
        return commentJpaRepository.findById(commentId)
                .orElseThrow(() -> new CustomRuntimeException(CommentExceptionType.COMMENT_NOT_FOUND));
    }
}
