package com.side.community.auth.dto.response;

public record AuthSignupResponseDto(
        String accessToken
) {

    public static AuthSignupResponseDto from(String accessToken) {
        return new AuthSignupResponseDto(accessToken);
    }
}
