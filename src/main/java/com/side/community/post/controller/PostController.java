package com.side.community.post.controller;

import com.side.community.common.annotation.UserPrincipal;
import com.side.community.post.dto.request.PostCreateRequestDto;
import com.side.community.post.dto.request.PostUpdateRequestDto;
import com.side.community.post.dto.response.PostCreateResponseDto;
import com.side.community.post.dto.response.PostFindPageResponseDto;
import com.side.community.post.dto.response.PostFindResponseDto;
import com.side.community.post.dto.response.PostUpdateResponseDto;
import com.side.community.post.service.PostService;
import com.side.community.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/{postId}")
    public ResponseEntity<PostFindResponseDto> findPost(
            @PathVariable Long postId
    ) {
        PostFindResponseDto data = postService.findPost(postId);

        return ResponseEntity.ok()
                .body(data);
    }

    @GetMapping
    public ResponseEntity<PostFindPageResponseDto> findPostPage(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content
    ) {
        PostFindPageResponseDto data = postService.findPostPage(pageable, title, content);

        return ResponseEntity.ok()
                .body(data);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostUpdateResponseDto> updatePost(
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
