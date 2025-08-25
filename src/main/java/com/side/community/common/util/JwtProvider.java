package com.side.community.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.access.expiration}")
    private Long accessExpiration;
    private final SecretKey accessSecretKey;

    public JwtProvider(
            @Value("${jwt.access.key}") String accessSecretKey
    ) {
        this.accessSecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecretKey));
    }

    public String generateAccessToken(Long userId) {
        return Jwts.builder()
                .subject(userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(accessSecretKey)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Claims claims = getClaimsFromAccessToken(token);

            return claims.getExpiration().after(new Date());
        } catch (RuntimeException e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromAccessToken(token);

        return Long.valueOf(claims.getSubject());
    }

    private Claims getClaimsFromAccessToken(String token) {
        return Jwts.parser()
                .verifyWith(accessSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
