package org.example.jvcarsharingservice.dto.user.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String password) {
}
