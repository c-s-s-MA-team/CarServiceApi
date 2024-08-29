package org.example.jvcarsharingservice.dto.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CreatePaymentRequestDto(
        @PositiveOrZero
        @NotBlank
        Long rentalId
) {
}
