package com.side.community.auth.dto.request;

public record AuthSigninRequestDto(
        String accountId,

        String accountPassword
) {
}
