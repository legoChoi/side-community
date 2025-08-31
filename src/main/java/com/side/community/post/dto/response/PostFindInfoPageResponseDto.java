package com.side.community.post.dto.response;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record PostFindInfoPageResponseDto(
        int pageNumber,

        int pageSize,

        long totalElements,

        int totalPages,

        boolean first,

        boolean last,

        List<PostFindInfoResponseDto> data
) {

    public static PostFindInfoPageResponseDto from(Page<PostFindInfoResponseDto> page) {
        return PostFindInfoPageResponseDto.builder()
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
