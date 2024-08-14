package org.example.jvcarsharingservice.servece.rental;

import org.example.jvcarsharingservice.dto.rental.CreateRentalRequestDto;
import org.example.jvcarsharingservice.dto.rental.RentalDto;

import java.util.List;

public interface RentalService {
    RentalDto addRental(CreateRentalRequestDto createRentalRequestDto);

    List<RentalDto> getRentals(Long userId, boolean isActive);

    RentalDto getRental(Long id);

    RentalDto returnRental(Long id);
}
