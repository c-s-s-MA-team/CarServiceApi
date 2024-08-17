package org.example.jvcarsharingservice.dto.rental;

public record RentalSearchParameters(
        Long[] userId,
        Boolean isActive
) {
}
