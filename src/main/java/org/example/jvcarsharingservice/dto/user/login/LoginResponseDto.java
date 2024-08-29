package org.example.jvcarsharingservice.dto.user.login;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDto(
        @NotBlank
        String token) {
}
