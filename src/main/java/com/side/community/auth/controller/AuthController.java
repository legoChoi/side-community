package com.side.community.auth.controller;

import com.side.community.auth.dto.request.AuthSigninRequestDto;
import com.side.community.auth.dto.request.AuthSignupRequestDto;
import com.side.community.auth.dto.response.AuthSigninResponseDto;
import com.side.community.auth.dto.response.AuthSignupResponseDto;
import com.side.community.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthSignupResponseDto> signup(
            @RequestBody @Valid AuthSignupRequestDto authSignupRequestDto
    ) {
        AuthSignupResponseDto data = authService.signup(authSignupRequestDto);

        return ResponseEntity.created(null)
                .body(data);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthSigninResponseDto> signin(
            @RequestBody @Valid AuthSigninRequestDto authSigninRequestDto
    ) {
        AuthSigninResponseDto data = authService.signin(authSigninRequestDto);

        return ResponseEntity.ok()
                .body(data);
    }
}
