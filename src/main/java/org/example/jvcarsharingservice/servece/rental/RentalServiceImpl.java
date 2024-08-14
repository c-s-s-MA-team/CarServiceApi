package org.example.jvcarsharingservice.servece.rental;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.rental.CreateRentalRequestDto;
import org.example.jvcarsharingservice.dto.rental.RentalDto;
import org.example.jvcarsharingservice.dto.rental.RentalSearchParameters;
import org.example.jvcarsharingservice.mapper.RentalMapper;
import org.example.jvcarsharingservice.repository.rental.RentalRepository;
import org.example.jvcarsharingservice.repository.rental.provider.RentalSpecificationBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final RentalSpecificationBuilder rentalSpecificationBuilder;
    
    @Override
    public RentalDto addRental(CreateRentalRequestDto createRentalRequestDto) {
        return null;
    }

    @Override
    public List<RentalDto> getRentals(RentalSearchParameters rentalSearchParameters) {
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
