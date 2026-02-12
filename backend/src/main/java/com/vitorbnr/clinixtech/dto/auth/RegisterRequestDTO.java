package com.vitorbnr.clinixtech.dto.auth;

import com.vitorbnr.clinixtech.model.enums.Role;

public record RegisterRequestDTO(String fullName, String email, String password, Role role) {
}