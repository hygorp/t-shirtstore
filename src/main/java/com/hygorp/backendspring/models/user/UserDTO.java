package com.hygorp.backendspring.models.user;

import com.hygorp.backendspring.models.user.enums.UserRole;

public record UserDTO(String login, String password, UserRole role) {
}
