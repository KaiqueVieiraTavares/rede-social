package com.example.userservice.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(

        @NotBlank(message = "Username não pode ser vazio")
        @Size(min = 3, max = 50, message = "Username deve ter entre 3 e 50 caracteres")
        String username,

        @NotBlank(message = "Email não pode ser vazio")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Senha não pode ser vazia")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password

) {}