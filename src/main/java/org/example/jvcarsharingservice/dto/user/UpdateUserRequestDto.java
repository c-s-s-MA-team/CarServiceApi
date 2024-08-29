package org.example.jvcarsharingservice.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequestDto(
        @NotNull
        @NotBlank
        String firstName,
        @NotNull
        @NotBlank
        String lastName
) {
}
