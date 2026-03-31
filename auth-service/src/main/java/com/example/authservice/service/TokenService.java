package com.example.authservice.service;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String generateAccessToken(Authentication authentication);
    String generateRefreshToken(Authentication authentication);
    String refreshToken(String refreshToken);
}
