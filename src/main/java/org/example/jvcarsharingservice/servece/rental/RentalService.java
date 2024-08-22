package org.example.jvcarsharingservice.servece.rental;

import java.util.List;
import org.example.jvcarsharingservice.dto.rental.CreateRentalRequestDto;
import org.example.jvcarsharingservice.dto.rental.RentalDto;
import org.example.jvcarsharingservice.dto.rental.RentalSearchParameters;
import org.example.jvcarsharingservice.model.classes.User;

public interface RentalService {
    RentalDto addRental(User user, CreateRentalRequestDto createRentalRequestDto);

    List<RentalDto> getRentals(RentalSearchParameters rentalSearchParameters);

    RentalDto getRental(Long id);

    void returnRental(User user, Long id);
}
