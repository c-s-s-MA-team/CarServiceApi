package org.example.jvcarsharingservice.dto.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CarRequestDto(
        @NotBlank
        String model,
        @NotBlank
        String brand,
        @NotBlank
        String type,
        @NotBlank
        @PositiveOrZero
        int inventory,
        @NotBlank
        @PositiveOrZero
        BigDecimal dailyFee
) {
}
