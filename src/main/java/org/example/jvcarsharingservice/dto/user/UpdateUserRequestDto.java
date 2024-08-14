package org.example.jvcarsharingservice.dto.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserRequestDto(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        String password
) {
}
