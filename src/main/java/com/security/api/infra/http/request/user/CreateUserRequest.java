package com.security.api.infra.http.request.user;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank(message = "Nome do Usuário não pode ser nulo ou em branco!") String username,
        @NotBlank(message = "Login de Usuário não pode ser nulo ou em branco!") String login,
        @NotBlank(message = "Senha do Usuário não pode ser nulo ou em branco!") String password,
        String roleUser) {
}
