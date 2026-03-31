package com.example.authservice.service;

import com.example.authservice.config.JwtKeyManager;
import com.example.authservice.dto.LoginDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtKeyManager jwtKeyManager;

    public AuthService(JwtKeyManager jwtKeyManager) {
        this.jwtKeyManager = jwtKeyManager;
    }

    public String generateToken(){

    }
}
