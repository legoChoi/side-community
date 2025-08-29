package com.side.community.post.controller;

import com.side.community.common.annotation.UserPrincipal;
import com.side.community.post.dto.request.PostCreateRequestDto;
import com.side.community.post.dto.request.PostUpdateRequestDto;
import com.side.community.post.dto.response.PostCreateResponseDto;
import com.side.community.post.dto.response.PostUpdateResponseDto;
import com.side.community.post.service.PostService;
import com.side.community.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostCreateResponseDto> createPost(
            @RequestBody @Valid PostCreateRequestDto postCreateRequestDto,
            @UserPrincipal User user
    ) {
        PostCreateResponseDto data = postService.createPost(postCreateRequestDto, user);

        return ResponseEntity.created(null)
                .body(data);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostUpdateResponseDto> findPost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequestDto postUpdateRequestDto,
            @UserPrincipal User user
    ) {
        PostUpdateResponseDto data = postService.updatePost(postId, postUpdateRequestDto, user);

        return ResponseEntity.ok()
                .body(data);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @UserPrincipal User user
    ) {
        postService.deletePost(postId, user);

        return ResponseEntity.ok()
                .build();
    }
}
