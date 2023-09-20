package com.hygorp.backendspring.models.user.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN("admin"),
    USER("user");

    @Enumerated(EnumType.STRING)
    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
