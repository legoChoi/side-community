package com.side.community.common.filter;

import com.side.community.common.exception.CustomRuntimeException;
import com.side.community.common.exception.type.AuthExceptionType;
import com.side.community.common.util.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.PathContainer;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String BEARER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    private final List<PathPattern> whiteList = List.of(
            new PathPatternParser().parse("/auth/**")
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();

        // /auth 요청 호출 시 필터 통과
        if (isInWhiteList(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractToken(request);

        // token 유효성 검사 실패
        if (!StringUtils.hasText(token) || !jwtProvider.validateAccessToken(token)) {
            log.error("[{}][{}]", this.getClass().getName(), AuthExceptionType.INVALID_TOKEN.getMessage());
            throw new CustomRuntimeException(AuthExceptionType.INVALID_TOKEN);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isInWhiteList(String path) {
        PathContainer pathContainer = PathContainer.parsePath(path);

        return whiteList.stream()
                .anyMatch(p -> p.matches(pathContainer));
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(header) && header.startsWith(BEARER_PREFIX)) {
            return header.substring(BEARER_PREFIX.length());
        }

        return null;

    }
}
