package com.side.community.auth.dto.response;

public record AuthSigninResponseDto(
        String accessToken
) {

    public static AuthSigninResponseDto from(String accessToken) {
        return new AuthSigninResponseDto(accessToken);
    }
}
