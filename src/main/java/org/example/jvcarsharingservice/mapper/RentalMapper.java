package org.example.jvcarsharingservice.mapper;

import org.example.jvcarsharingservice.config.MapperConfig;
import org.example.jvcarsharingservice.dto.rental.CreateRentalRequestDto;
import org.example.jvcarsharingservice.dto.rental.RentalDto;
import org.example.jvcarsharingservice.model.classes.Rental;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RentalMapper {

    RentalDto toDto(Rental rental);

    Rental toEntity(CreateRentalRequestDto rentalDto);
}

