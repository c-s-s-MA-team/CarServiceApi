package org.example.jvcarsharingservice.servece.rental;

import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.rental.CreateRentalRequestDto;
import org.example.jvcarsharingservice.dto.rental.RentalDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    @Override
    public RentalDto addRental(CreateRentalRequestDto createRentalRequestDto) {
        return null;
    }

    @Override
    public List<RentalDto> getRentals(Long userId, boolean isActive) {
        return List.of();
    }

    @Override
    public RentalDto getRental(Long id) {
        return null;
    }

    @Override
    public RentalDto returnRental(Long id) {
        return null;
    }
}
