package com.example.userservice.dto.response.user;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String email
) {}