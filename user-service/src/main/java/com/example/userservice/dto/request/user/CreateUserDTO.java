package com.example.userservice.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 30)
        String username,
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, max = 50)
        String password
) {}