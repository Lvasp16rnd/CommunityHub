package com.communityhub.userservice.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(@Email(message = "O email não foi corretamente preenchido") String email,
        @NotBlank(message = "Campo vazio, a senha é obrigatório") @Size(min = 6, message = "a senha deve ter no minimo 6 caracteres") String password) {
}
