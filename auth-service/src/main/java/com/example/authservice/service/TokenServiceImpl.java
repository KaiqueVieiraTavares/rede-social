package com.example.authservice.service;

import com.example.authservice.config.JwtKeyManager;
import com.example.authservice.exceptions.InvalidTokenException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final JwtKeyManager jwtKeyManager;
    private static final String ACCESS_TYPE="access";
    private static final String REFRESH_TYPE="refresh";
    private static final String ROLES_CLAIM = "roles";
    private static final String TYPE_CLAIM = "type";

    @Override
    public String generateAccessToken(Authentication authentication){
        String roles = extractRoles(authentication);
        return buildToken(authentication.getName(), roles, ACCESS_TYPE, 15, ChronoUnit.MINUTES);
    }
    @Override
    public String generateRefreshToken(Authentication authentication){
        String roles = extractRoles(authentication);
        return buildToken(authentication.getName(), roles, REFRESH_TYPE, 14, ChronoUnit.DAYS);
    }
    @Override
    public String refreshToken(String refreshToken){
        var claims = Jwts.parser().verifyWith(jwtKeyManager.getSecret())
                .build().parseSignedClaims(refreshToken)
                .getPayload();
        if(!REFRESH_TYPE.equals(claims.get(TYPE_CLAIM))){
            throw new InvalidTokenException("Tipo de token incorreto!");
        }
        return buildToken(claims.getSubject(), claims.get(ROLES_CLAIM, String.class), ACCESS_TYPE, 15, ChronoUnit.MINUTES);

    }
    private String extractRoles(Authentication authentication){
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
    private String buildToken(String subject, String roles, String type, long amount, ChronoUnit chronoUnit){
        return Jwts.builder().subject(subject).issuedAt(new Date()).expiration(Date.from(Instant.now().plus(amount, chronoUnit)))
                .claim(ROLES_CLAIM, roles)
                .claim(TYPE_CLAIM, type)
                .signWith(jwtKeyManager.getSecret())
                .compact();
    }
}
