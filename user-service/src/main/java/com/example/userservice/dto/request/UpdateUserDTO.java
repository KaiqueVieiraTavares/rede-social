package com.example.userservice.dto.request;

public record UpdateUserDTO(
        String username,
        String email,
        String password
) {}