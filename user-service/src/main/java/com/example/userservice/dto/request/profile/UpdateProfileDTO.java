package com.example.userservice.dto.request.profile;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateProfileDTO(

        @Size(max = 300, message = "Bio must not exceed 300 characters")
        String bio,

        @Pattern(
                regexp = "^(https?://).+",
                message = "Avatar URL must be a valid URL starting with http:// or https://"
        )
        String avatarUrl,

        @Size(max = 100, message = "Location must not exceed 100 characters")
        String location,

        @Pattern(
                regexp = "^(https?://).+",
                message = "Website must be a valid URL starting with http:// or https://"
        )
        String website
) {}