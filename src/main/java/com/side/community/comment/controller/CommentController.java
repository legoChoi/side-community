package com.side.community.comment.controller;

import com.side.community.comment.dto.request.CommentCreateRequestDto;
import com.side.community.comment.dto.request.CommentUpdateRequestDto;
import com.side.community.comment.dto.response.CommentCreateResponseDto;
import com.side.community.comment.dto.response.CommentUpdateResponseDto;
import com.side.community.comment.service.CommentService;
import com.side.community.common.annotation.UserPrincipal;
import com.side.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentCreateResponseDto> createComment(
            @RequestBody CommentCreateRequestDto commentCreateRequestDto,
            @UserPrincipal User user
    ) {
        CommentCreateResponseDto data = commentService.createComment(commentCreateRequestDto, user);

        return ResponseEntity.created(null)
                .body(data);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentUpdateResponseDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto commentUpdateRequestDto,
            @UserPrincipal User user
    ) {
        CommentUpdateResponseDto data = commentService.updateComment(commentId, commentUpdateRequestDto, user);

        return ResponseEntity.ok()
                .body(data);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @UserPrincipal User user
    ) {
        commentService.deleteComment(commentId, user);

        return ResponseEntity.ok()
                .build();
    }
}
