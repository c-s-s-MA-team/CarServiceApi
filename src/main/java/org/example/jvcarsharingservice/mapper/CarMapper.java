package org.example.jvcarsharingservice.mapper;

import org.example.jvcarsharingservice.config.MapperConfig;
import org.example.jvcarsharingservice.dto.car.CarDetailsDto;
import org.example.jvcarsharingservice.dto.car.CarDto;
import org.example.jvcarsharingservice.dto.car.CarRequestDto;
import org.example.jvcarsharingservice.model.classes.Car;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CarMapper {

    CarDto toDto(Car car);

    CarDetailsDto toDetailsDto(Car car);

    Car toEntity(CarRequestDto carDto);
}

