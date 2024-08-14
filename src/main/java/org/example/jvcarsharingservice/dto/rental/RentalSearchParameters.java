package org.example.jvcarsharingservice.dto.rental;

public record RentalSearchParameters(
        String[] userId,
        String[] returnDate
) {
}
