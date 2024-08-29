package org.example.jvcarsharingservice.dto.rental;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public record CreateRentalRequestDto(
        @NotBlank
        LocalDate rentalDate,
        @NotBlank
        LocalDate returnDate,
        @NotBlank
        @PositiveOrZero
        Long carId
) {
}
