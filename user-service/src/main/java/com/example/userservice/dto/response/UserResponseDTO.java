package com.example.userservice.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}