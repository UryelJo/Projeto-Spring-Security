package com.security.api.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN") ,
    USER("USER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
