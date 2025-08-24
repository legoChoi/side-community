package com.side.community.auth.service;

import com.side.community.auth.dto.request.AuthSignupRequestDto;
import com.side.community.auth.dto.response.AuthSignupResponseDto;
import com.side.community.common.exception.CustomRuntimeException;
import com.side.community.common.exception.ExceptionType;
import com.side.community.common.util.JwtProvider;
import com.side.community.user.entity.User;
import com.side.community.user.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    private final UserJpaRepository userJpaRepository;

    @Transactional
    public AuthSignupResponseDto signup(AuthSignupRequestDto dto) {
        if (!userJpaRepository.existsByAccountId(dto.accountId())) {
            throw new CustomRuntimeException(ExceptionType.DUPLICATED_ACCOUNT_ID);
        }

        if (!userJpaRepository.existsByNickname(dto.nickname())) {
            throw new CustomRuntimeException(ExceptionType.DUPLICATED_NICKNAME);
        }

        String encodedPassword = passwordEncoder.encode(dto.accountPassword());

        User user = User.builder()
                .accountId(dto.accountId())
                .accountPassword(encodedPassword)
                .nickname(dto.nickname())
                .build();

        userJpaRepository.save(user);

        String accessToken = jwtProvider.generateAccessToken(user.getId());

        return new AuthSignupResponseDto(accessToken);
    }
}
