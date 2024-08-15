package org.example.jvcarsharingservice.servece.rental;

import java.util.List;
import org.example.jvcarsharingservice.dto.rental.CreateRentalRequestDto;
import org.example.jvcarsharingservice.dto.rental.RentalDto;
import org.example.jvcarsharingservice.dto.rental.RentalSearchParameters;

public interface RentalService {
    RentalDto addRental(CreateRentalRequestDto createRentalRequestDto);

    List<RentalDto> getRentals(RentalSearchParameters rentalSearchParameters);

    RentalDto getRental(Long id);

    void returnRental(Long id);
}
