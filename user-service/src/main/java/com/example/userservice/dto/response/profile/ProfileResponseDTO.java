package com.example.userservice.dto.response.profile;

import java.util.UUID;

public record ProfileResponseDTO(
        UUID id,
        String bio,
        String avatarUrl,
        String location,
        String website,
        UUID userId
) {}