package org.example.jvcarsharingservice.mapper;

import org.example.jvcarsharingservice.config.MapperConfig;
import org.example.jvcarsharingservice.dto.car.CarDto;
import org.example.jvcarsharingservice.dto.car.CreateCarRequestDto;
import org.example.jvcarsharingservice.model.classes.Car;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CarMapper {

    CarDto toDto(Car car);

    Car toEntity(CreateCarRequestDto carDto);
}

