package com.security.api.domain.exception.custom;

public class UserFoundException extends RuntimeException {
    public UserFoundException(String message) {
        super(message);
    }

    public UserFoundException() {
        super("Usuário já existe!");
    }
}
