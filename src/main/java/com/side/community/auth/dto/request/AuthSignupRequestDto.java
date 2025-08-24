package com.side.community.auth.dto.request;

public record AuthSignupRequestDto(
        String accountId,
        String accountPassword,
        String nickname
) {
}
