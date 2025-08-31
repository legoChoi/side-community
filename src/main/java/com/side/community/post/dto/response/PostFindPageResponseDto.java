package com.side.community.post.dto.response;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record PostFindPageResponseDto(
        int pageNumber,

        int pageSize,

        long totalElements,

        int totalPages,

        boolean first,

        boolean last,

        List<PostInfoResponseDto> data
) {

    public static PostFindPageResponseDto from(Page<PostInfoResponseDto> page) {
        return PostFindPageResponseDto.builder()
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .data(page.getContent())
                .build();
    }
}
