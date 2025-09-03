package com.side.community.user.entity;

import com.side.community.common.exception.CustomRuntimeException;
import com.side.community.common.exception.type.AuthExceptionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMIN("ADMIN"),
    USER("USER"),
    ;

    private final String role;

    public static UserRole of(String role) {
        return Stream.of(UserRole.values())
                .filter(userRole -> userRole.role.equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new CustomRuntimeException(AuthExceptionType.INVALID_USER_ROLE));
    }
}
