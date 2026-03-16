package com.example.userservice.dto.request;

public record CreateUserDTO(
        String username,
        String email,
        String password
) {}