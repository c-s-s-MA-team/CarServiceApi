package org.example.jvcarsharingservice.servece.car;

import jakarta.validation.Valid;
import org.example.jvcarsharingservice.dto.car.CarDetailsDto;
import org.example.jvcarsharingservice.dto.car.CarDto;
import org.example.jvcarsharingservice.dto.car.CarRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    CarDto addCar(CarRequestDto createCarRequestDto);

    List<CarDto> getCars(Pageable pageable);

    CarDetailsDto getCarDetails(Long id);

    CarDto updateCar(Long id, @Valid CarRequestDto updateCarRequestDto);

    void delete(Long id);
}
